package com.wendel.test.Fiserv.adapter.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientRequest {
    private String zipcode;
    private String name;
    private String cpf;
    private String age;
}
