package com.wendel.test.runTheBank.usecase.client;

import com.wendel.test.runTheBank.adapter.controller.request.ClientRequest;
import com.wendel.test.runTheBank.adapter.controller.response.ClientResponse;

public interface CreateClient {
    ClientResponse execute(ClientRequest clientRequest);
}
