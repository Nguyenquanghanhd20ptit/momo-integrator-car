package com.example.momointerator.config.exception;

import com.example.momointerator.commons.data.response.DataResponse;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.momointerator.commons.data.constant.ErrorCodeConstant.ERROR_CODE_DURING_PROCESS;
import static com.example.momointerator.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_DURING_PROCESS;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Gson gson = new Gson();

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlingValidation(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        message = StringUtils.isEmpty(message) ? ERROR_MESSAGE_DURING_PROCESS : message;
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_DURING_PROCESS)
                .setErrorMessage(message);
        return new ResponseEntity<>(gson.toJson(dataResponse), HttpStatus.OK);
    }
}
