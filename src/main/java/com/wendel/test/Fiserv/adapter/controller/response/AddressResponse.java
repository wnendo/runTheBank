package com.wendel.test.Fiserv.adapter.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
    private String id;
    private String address;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String additionalAddress;
    private String clientId;
    private String message;
}
