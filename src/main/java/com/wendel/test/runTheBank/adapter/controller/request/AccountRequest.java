package com.wendel.test.runTheBank.adapter.controller.request;

import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.domain.enuns.RegisterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequest {
    private String registerId;
    private String agency;
    private BigDecimal balance;
    private AccountStatus accountStatus;
}
