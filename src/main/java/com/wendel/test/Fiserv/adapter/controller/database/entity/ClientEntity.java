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
@Table(name = "tb_client")
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    @Column(name = "deleted")
    private boolean delete = false;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
}
