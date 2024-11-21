package com.wendel.test.runTheBank.adapter.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wendel.test.runTheBank.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse {
    private String id;
    private String addressId;
    private String name;
    private String cpf;
    private String age;
    private String message;
}
