package com.wendel.test.runTheBank.usecase.notification.impl;

import com.wendel.test.runTheBank.adapter.controller.response.NotificationResponse;
import com.wendel.test.runTheBank.adapter.gateway.web.WebGateway;
import com.wendel.test.runTheBank.domain.Transaction;
import com.wendel.test.runTheBank.usecase.notification.SendNotification;

public class SendNotificationImpl implements SendNotification {
    private final WebGateway webGateway;

    public SendNotificationImpl(WebGateway webGateway) {
        this.webGateway = webGateway;
    }

    @Override
    public NotificationResponse execute(Transaction transaction) {
        return webGateway.sendNotification(transaction);
    }
}
