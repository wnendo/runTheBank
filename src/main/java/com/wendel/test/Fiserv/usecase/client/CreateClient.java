package com.wendel.test.Fiserv.usecase.client;

import com.wendel.test.Fiserv.adapter.controller.request.ClientRequest;
import com.wendel.test.Fiserv.adapter.controller.response.ClientResponse;

public interface CreateClient {
    ClientResponse execute(ClientRequest clientRequest);
}
