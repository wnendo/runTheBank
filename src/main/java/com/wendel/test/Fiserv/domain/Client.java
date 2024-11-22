package com.wendel.test.Fiserv.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {
    private String id;
    private String cpf;
    private String name;
    private String age;
    private Address address;
}
