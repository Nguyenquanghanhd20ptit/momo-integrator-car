package com.example.momointerator.service.orderMomo;

import com.example.momointerator.commons.data.dto.momo.OrderSendToMomo;
import com.example.momointerator.commons.data.dto.momo.ReceiveInfoMomo;
import com.example.momointerator.commons.data.mapper.OrderMomoMapper;
import com.example.momointerator.commons.data.request.orderMomo.OrderMomoRequest;
import com.example.momointerator.config.ResourceProperties;
import com.example.momointerator.db.mongo.model.OrderMomoModel;
import com.example.momointerator.db.mongo.repository.IOrderMomoRepository;
import com.example.momointerator.service.BaseService;
import com.example.momointerator.utils.OkhttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.example.momointerator.commons.data.constant.ErrorCodeConstant.ERROR_CODE_DURING_PROCESS;

@Service
public class CreateTransactionMomoService extends BaseService {

    @Autowired
    private ResourceProperties resourceProperties;
    @Autowired
    private OkhttpUtils okhttpUtils;
    @Autowired
    private IOrderMomoRepository orderMomoRepository;

    @Autowired
    private OrderMomoMapper orderMomoMapper;
    public ResponseEntity<String> createTransaction(OrderMomoRequest request) {
        try {
            OrderSendToMomo orderSendToMomo = orderMomoMapper.addValueOrderMomo(request);
            String jsonBody = gson.toJson(orderSendToMomo);
            ResponseEntity<String> response = okhttpUtils.restPostJsonBody(resourceProperties.getMomoUrl(),
                    new HashMap<>(), jsonBody);

            if (response == null || response.getBody() == null || response.getBody().isEmpty()) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi kết nối đến momo");
            }
            ReceiveInfoMomo receiveInfoMomo = convertToObject(response, ReceiveInfoMomo.class);
            if (receiveInfoMomo == null) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi sảy ra khi momo phản hồi");
            } else if (receiveInfoMomo.getPartnerCode() == null) {
                return createResponseError(ERROR_CODE_DURING_PROCESS, "Đã có lỗi xử lý bên phía momo");
            } else {
                OrderMomoModel orderMomoModel = orderMomoMapper.toModel(orderSendToMomo, receiveInfoMomo);
                OrderMomoModel save = orderMomoRepository.save(orderMomoModel);
                receiveInfoMomo.setOrderMomoId(save.getId());
                return createResponseSuccess(gsonSnakeCaseBuilder.toJson(receiveInfoMomo));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return createResponseException(e);
        }
    }
}
