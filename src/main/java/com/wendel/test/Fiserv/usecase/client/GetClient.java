package com.wendel.test.Fiserv.usecase.client;

import com.wendel.test.Fiserv.adapter.controller.response.ClientResponse;

public interface GetClient {
    ClientResponse execute(String cpf);
}
