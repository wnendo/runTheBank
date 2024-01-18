package com.wendel.test.runTheBank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Register {
    private String id;
    private String name;
    private String cpfOrCnpj;
    private String address;
    private String password;
    private List<Account> account;
}
