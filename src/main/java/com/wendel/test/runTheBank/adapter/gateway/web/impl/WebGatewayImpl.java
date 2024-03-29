package com.wendel.test.runTheBank.adapter.gateway.web.impl;

import com.wendel.test.runTheBank.adapter.controller.response.NotificationResponse;
import com.wendel.test.runTheBank.adapter.gateway.util.Json;
import com.wendel.test.runTheBank.adapter.gateway.web.WebGateway;
import com.wendel.test.runTheBank.domain.Transaction;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class WebGatewayImpl implements WebGateway {
    private final OkHttpClient okHttpClient;
    private final Json json;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public WebGatewayImpl(OkHttpClient okHttpClient, Json json) {
        this.okHttpClient = okHttpClient;
        this.json = json;
    }

    @Override
    public NotificationResponse sendNotification(Transaction transaction) {
        try {
            log.info("Making request to external api for notification");
            var transactionAsJson = json.toJson(transaction);
            RequestBody requestBody = RequestBody.create(JSON, transactionAsJson);
            Request request = new Request.Builder()
                    .url("https://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3")
                    .post(requestBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if(response.code() != 200){
                return NotificationResponse.builder()
                        .message("Error while sending notification to client")
                        .build();
            }
            return json.fromJson(response.body().string(), NotificationResponse.class);
        } catch (IOException e) {
            log.error("Error while sending notification to external API {}", e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while sending notification"));
        }
    }
}
