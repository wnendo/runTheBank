package com.wendel.test.runTheBank.adapter.controller.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tb_account")
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @GeneratedValue(strategy = SEQUENCE)
    private Long pk;
    @Id
    @Column(name = "id", unique=true, nullable = false)
    private String id;
    @Column(name = "agency", unique=true, nullable = false)
    private String agency;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id")
    private RegisterEntity registerEntity;
}
