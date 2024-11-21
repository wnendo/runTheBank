package com.wendel.test.runTheBank.adapter.controller.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tb_client")
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @GeneratedValue(strategy = SEQUENCE)
    private Long pk;
    @Id
    @Column(name = "id", unique=true, nullable = false)
    private String id;
    @Column(name = "cpf", unique=true, nullable = false)
    private String cpf;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private String age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
}
