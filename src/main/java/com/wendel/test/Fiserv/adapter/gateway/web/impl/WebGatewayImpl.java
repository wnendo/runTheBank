package com.wendel.test.Fiserv.adapter.gateway.web.impl;

import com.wendel.test.Fiserv.adapter.controller.response.AddressFromViaCep;
import com.wendel.test.Fiserv.adapter.gateway.util.Json;
import com.wendel.test.Fiserv.adapter.gateway.web.WebGateway;
import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class WebGatewayImpl implements WebGateway {
    private final OkHttpClient okHttpClient;
    private final Json json;

    public WebGatewayImpl(OkHttpClient okHttpClient, Json json) {
        this.okHttpClient = okHttpClient;
        this.json = json;
    }

    @Override
    public AddressFromViaCep getAddressByZipcode(String zipcode) {
        try {
            Request request = new Request.Builder()
                    .url("https://viacep.com.br/ws/:zipcode/json".replace(":zipcode", zipcode))
                    .get()
                    .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if(response.code() != 200 || response.body()==null){
                return AddressFromViaCep.builder()
                        .message("Error while getting address with zipcode: " + zipcode)
                        .build();
            }
            return json.fromJson(response.body().string(), AddressFromViaCep.class);
        } catch (IOException e) {
            log.error("Error while getting address {}", e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while getting address"));
        }
    }
}
