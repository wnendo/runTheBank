package com.wendel.test.runTheBank.adapter.controller.database.entity;

import com.wendel.test.runTheBank.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.List;
import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tb_register")
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEntity {
    @GeneratedValue(strategy = SEQUENCE)
    private Long pk;
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cpf_cnpj", unique=true, nullable = false)
    private String cpfOrCnpj;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "registerEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AccountEntity> accountEntity;
}
