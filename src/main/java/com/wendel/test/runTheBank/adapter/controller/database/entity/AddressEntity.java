package com.wendel.test.runTheBank.adapter.controller.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tb_register")
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @GeneratedValue(strategy = SEQUENCE)
    private Long pk;
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "zipcode", nullable = false)
    private String zipcode;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "additionalAddress")
    private String additionalAddress;
    @OneToMany(mappedBy = "addressEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ClientEntity> clientEntity;
}
