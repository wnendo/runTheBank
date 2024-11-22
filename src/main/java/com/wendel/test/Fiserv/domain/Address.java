package com.wendel.test.Fiserv.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
