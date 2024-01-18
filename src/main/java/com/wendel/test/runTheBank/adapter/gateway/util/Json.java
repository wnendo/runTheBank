package com.wendel.test.runTheBank.adapter.gateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.wendel.test.runTheBank.domain.validator.ExceptionMessage.INVALID_JSON;

@Log4j2
@RequiredArgsConstructor
@Service
public class Json {

    private final ObjectMapper mapper;

    public <T> T fromJson(String json, Class<T> classType) {
        if (json == null || json.equals("[]")) return null;
        try {
            return mapper.readValue(json, classType);
        } catch (JsonMappingException e) {
            log.error(e.getMessage());
            if (e.getCause() instanceof ApiException) {
                throw ((ApiException) e.getCause());
            }
            log.error("Unable to deserialize JSON | {} | to class |{}|", json, classType, e);
            throw new ApiException(INVALID_JSON);
        } catch (IOException e) {
            log.error("Unable to deserialize JSON | {} | to class |{}|", json, classType, e);
            if(e.getMessage().contains("Unexpected character")){
                throw new ApiException(INVALID_JSON);
            }
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        if (json == null || json.equals("[]")) return null;

        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (JsonMappingException e) {
            log.error("Unable to deserialize JSON | {} ", json,  e);
            log.error(e.getMessage());
            if (e.getCause() instanceof ApiException) {
                throw ((ApiException) e.getCause());
            }
            throw new ApiException(INVALID_JSON);
        } catch (IOException e) {
            log.error("Unable to deserialize JSON | {} | to class |{}|", json, valueTypeRef, e);
            if(e.getMessage().contains("Unexpected character")){
                throw new ApiException(INVALID_JSON);
            }
            throw new RuntimeException(e);
        }
    }

    public <T> T fromResponse(Response response, Class<T> classType) {
        log.info("Parsing json to [{}]", classType.getName());
        String json = null;
        try {
            json = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (json == null) return null;
        return fromJson(json, classType);
    }

    public <T> T fromResponse(Response response, TypeReference<T> valueTypeRef) {
        log.info("Parsing json to [{}]", valueTypeRef.getType().getTypeName());
        String json = null;
        try {
            json = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (json == null) return null;
        return fromJson(json, valueTypeRef);
    }

    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize object [{}] to JSON,", object, e);
            throw new RuntimeException(e);
        }
    }
}