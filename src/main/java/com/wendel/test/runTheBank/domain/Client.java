package com.wendel.test.runTheBank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Client {
    private String id;
    private String cpf;
    private String name;
    private String age;
    private String addressId;
}
