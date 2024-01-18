package com.wendel.test.runTheBank.adapter.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionRequest {
    private String id;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private LocalDateTime date;
}
