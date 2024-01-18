package com.wendel.test.runTheBank.domain;

import com.wendel.test.runTheBank.domain.enuns.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Transaction {
    private String id;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private LocalDateTime date;
    private TransactionStatus status;
}
