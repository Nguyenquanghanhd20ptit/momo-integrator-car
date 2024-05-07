package com.example.momointerator.service.orderMomo;

import com.example.momointerator.commons.data.dto.momo.MomoAfterCheckTransaction;
import com.example.momointerator.commons.data.dto.momo.MomoCheckTransaction;
import com.example.momointerator.commons.data.mapper.OrderMomoMapper;
import com.example.momointerator.config.ResourceProperties;
import com.example.momointerator.db.mongo.model.OrderMomoModel;
import com.example.momointerator.db.mongo.repository.IOrderMomoRepository;
import com.example.momointerator.service.BaseService;
import com.example.momointerator.utils.OkhttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import static com.example.momointerator.commons.data.constant.ErrorCodeConstant.ERROR_CODE_DURING_PROCESS;

@Service
public class CheckStatusOrderService extends BaseService {
    @Autowired
    private IOrderMomoRepository orderMomoRepository;
    @Autowired
    private OrderMomoMapper orderMomoMapper;
    @Autowired
    private OkhttpUtils okhttpUtils;
    @Autowired
    private ResourceProperties resourceProperties;
    @Autowired
    private OrderMomoComponent orderMomoComponent;
    public ResponseEntity<String> checkOrderMomo(String orderId){
        try {
            Optional<OrderMomoModel> opt = orderMomoRepository.findByOrderId(orderId);
            if(opt.isEmpty()){
                return createResponseError(ERROR_CODE_DURING_PROCESS,"Đơn hàng chưa thực hiện thanh toán");
            }
            OrderMomoModel momoModel = opt.get();
            MomoCheckTransaction momoCheckTransaction = orderMomoMapper.toMomoCheckTransaction(momoModel);
            String jsonBody = gson.toJson(momoCheckTransaction);

            ResponseEntity<String> response = okhttpUtils.restPostJsonBody(resourceProperties.getMomoCheckTransactionUrl(),
                    new HashMap<>(), jsonBody);

            if (response == null || response.getBody() == null || response.getBody().isEmpty()) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi kết nối đến momo");
            }
            MomoAfterCheckTransaction momoAfterCheckTransaction = convertToObject(response, MomoAfterCheckTransaction.class);
            if (momoAfterCheckTransaction == null) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi momo phản hồi");
            } else if (momoAfterCheckTransaction.getResultCode() == null) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi xử lý bên phía momo");
            }else {
                Boolean aBoolean = orderMomoComponent.updateResult(orderId, momoAfterCheckTransaction.getResultCode());
                if(aBoolean){
                    return createResponseSuccess("update success");
                }else {
                    return createResponseError(ERROR_CODE_DURING_PROCESS, "update fail");
                }
            }

        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
