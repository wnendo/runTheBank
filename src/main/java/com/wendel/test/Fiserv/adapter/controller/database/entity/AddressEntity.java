package com.wendel.test.Fiserv.adapter.controller.database.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_address")
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "neighbor", nullable = false)
    private String neighborhood;
    @Column(name = "zipcode", nullable = false)
    private String zipcode;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "additionalAddress")
    private String additionalAddress;
    @Column(name = "deleted")
    private boolean delete;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;
}
