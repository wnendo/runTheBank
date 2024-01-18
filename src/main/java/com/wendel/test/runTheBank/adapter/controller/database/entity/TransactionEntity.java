package com.wendel.test.runTheBank.adapter.controller.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tb_transaction")
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @GeneratedValue(strategy = SEQUENCE)
    private Long pk;
    @Id
    @Column(name = "id", unique=true, nullable = false)
    private String id;
    @Column(name = "from_account", nullable = false)
    private String fromAccount;
    @Column(name = "to_account", nullable = false)
    private String toAccount;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}
