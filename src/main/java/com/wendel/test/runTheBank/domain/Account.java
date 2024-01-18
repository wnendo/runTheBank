package com.wendel.test.runTheBank.domain;

import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Account {
    private String id;
    private String registerId;
    private String agency;
    private BigDecimal balance;
    private AccountStatus accountStatus;
}
