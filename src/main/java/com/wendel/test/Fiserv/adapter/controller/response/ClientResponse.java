package com.wendel.test.Fiserv.adapter.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wendel.test.Fiserv.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse {
    private String id;
    private String name;
    private String cpf;
    private String age;
    private Address address;
    private String message;
}
