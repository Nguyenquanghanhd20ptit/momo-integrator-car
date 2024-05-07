package com.example.momointerator.utils;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;

@Component
public class OkhttpUtils {
    @Autowired
    private OkHttpClient okHttpClient;
    public ResponseEntity<String> restGet(String url, Map<String,String> mapHeader){
        try{
            Headers headers = Headers.of(mapHeader);
            Request request = new Request.Builder().url(url)
                    .headers(headers)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            return createResponse(response);
        }catch (SocketTimeoutException e){
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(e.getCause().getMessage());
        }
        catch (ConnectException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("connect time out");
        }
        catch (Exception e){
            return null;
        }
    }

    public ResponseEntity<String> restGetBearerToken(String url, Map<String,String> mapHeaders, String token){
        try{
            mapHeaders.put("Authorization","Bearer " + token);
            Headers headers = Headers.of(mapHeaders);
            Request request = new Request.Builder()
                    .headers(headers)
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            return createResponse(response);
        }catch (SocketTimeoutException e){
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(e.getCause().getMessage());
        }
        catch (ConnectException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("connect time out");
        }
        catch (Exception e){
            return null;
        }
    }

    public ResponseEntity<String> restPostJsonBody(String url,Map<String,String> mapHeader,
                                                   String jsonBody){
        try{
            Headers headers = Headers.of(mapHeader);
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = jsonBody == null ? RequestBody.create(mediaType,"") : RequestBody.create(mediaType,jsonBody);
            Request request = new Request.Builder().url(url)
                    .headers(headers)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            return createResponse(response);
        }catch (SocketTimeoutException e){
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(e.getCause().getMessage());
        }
        catch (ConnectException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("connect time out");
        }
        catch (Exception e){
            return null;
        }
    }
    public ResponseEntity<String> restPostJsonBodyBearerToken(String url, Map<String,String> mapHeader,
                                                              String jsonBody, String token){
        try {
            mapHeader.put("Authorization","Bearer " + token);
            Headers headers = Headers.of(mapHeader);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = jsonBody == null ? RequestBody.create(mediaType,"") : RequestBody.create(mediaType,jsonBody);
            Request request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            return createResponse(response);
        }catch (SocketTimeoutException e){
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(e.getCause().getMessage());
        }
        catch (ConnectException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("connect time out");
        }
        catch (Exception e){
            return null;
        }
    }
    private ResponseEntity<String> createResponse(Response response){
        try{
            if(response == null){
                return null;
            }
            else if(response.body() == null){
                return new ResponseEntity<>(null, HttpStatus.valueOf(response.code()));
            }
            else {
                return new ResponseEntity<>(response.body().string(),HttpStatus.valueOf(response.code()));
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
