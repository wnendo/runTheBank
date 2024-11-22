package com.wendel.test.runTheBank.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String id;
    private String address;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String additionalAddress;
    private String client;
}
