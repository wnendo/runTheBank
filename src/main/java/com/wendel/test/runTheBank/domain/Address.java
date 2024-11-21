package com.wendel.test.runTheBank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Address {
    private String id;
    private String address;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String additionalAddress;
    private List<Client> client;
}
