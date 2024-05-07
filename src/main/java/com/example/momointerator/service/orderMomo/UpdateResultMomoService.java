package com.example.momointerator.service.orderMomo;

import com.example.momointerator.commons.data.dto.momo.MomoAfterPayment;
import com.example.momointerator.config.ResourceProperties;
import com.example.momointerator.db.mongo.model.OrderMomoModel;
import com.example.momointerator.db.mongo.repository.IOrderMomoRepository;
import com.example.momointerator.service.BaseService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.example.momointerator.commons.data.constant.ErrorCodeConstant.ERROR_CODE_DURING_PROCESS;

@Service
public class UpdateResultMomoService  extends BaseService {
    @Autowired
    private IOrderMomoRepository orderMomoRepository;
    @Autowired
    private ResourceProperties resourceProperties;
    @Autowired
    private OrderMomoComponent orderMomoComponent;
    public ResponseEntity<String> updateResult(MomoAfterPayment momoAfterPayment){
        try {
            Optional<OrderMomoModel> opt = orderMomoRepository.findByOrderId(momoAfterPayment.getOrderId());
            if(opt.isEmpty()){
                return createResponseError(ERROR_CODE_DURING_PROCESS,"không tìm thấy đơn hàng momo");
            }
            if(!checkSignature(momoAfterPayment)){
                return createResponseError(ERROR_CODE_DURING_PROCESS,"signature không hợp lệ");
            }
            Boolean aBoolean = orderMomoComponent.updateResult(momoAfterPayment.getOrderId(),
                    momoAfterPayment.getResultCode());
            if(aBoolean){
                return createResponseSuccess("update success");
            }else {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "update fail");
            }
        }catch (Exception e){
            return createResponseException(e);
        }
    }

    private Boolean checkSignature(MomoAfterPayment momoAfterPayment) {
        String extraData = momoAfterPayment.getExtraData() != null ? momoAfterPayment.getExtraData() : "";
        String key = "accessKey=" + resourceProperties.getMomoAccessKey() +
                "&amount=" + momoAfterPayment.getAmount() +
                "&extraData=" + extraData +
                "&message=" + momoAfterPayment.getMessage() +
                "&orderId=" + momoAfterPayment.getOrderId() +
                "&orderInfo=" + momoAfterPayment.getOrderInfo() +
                "&orderType=" + momoAfterPayment.getOrderType() +
                "&partnerCode=" + momoAfterPayment.getPartnerCode() +
                "&payType=" + momoAfterPayment.getPayType() +
                "&requestId=" + momoAfterPayment.getRequestId() +
                "&responseTime=" + momoAfterPayment.getResponseTime() +
                "&resultCode=" + momoAfterPayment.getResultCode() +
                "&transId=" + momoAfterPayment.getTransId();
        String secret = resourceProperties.getMomoSecretKey();
        String signature = Hashing.hmacSha256(secret.getBytes(StandardCharsets.UTF_8)).hashString(key, StandardCharsets.UTF_8).toString();
        return momoAfterPayment.getSignature().equals(signature);
    }
}
