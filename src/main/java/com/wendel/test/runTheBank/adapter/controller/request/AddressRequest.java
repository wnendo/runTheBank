package com.wendel.test.runTheBank.adapter.controller.request;

import com.wendel.test.runTheBank.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {
    private String zipCode;
}
