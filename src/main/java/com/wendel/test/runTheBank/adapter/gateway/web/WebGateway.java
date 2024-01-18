package com.wendel.test.runTheBank.adapter.gateway.web;

import com.wendel.test.runTheBank.adapter.controller.response.NotificationResponse;
import com.wendel.test.runTheBank.domain.Transaction;

public interface WebGateway {
    NotificationResponse sendNotification(Transaction transaction);
}
