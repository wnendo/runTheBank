package com.wendel.test.runTheBank.usecase.notification.impl;

import com.wendel.test.runTheBank.adapter.controller.response.NotificationResponse;
import com.wendel.test.runTheBank.adapter.gateway.web.WebGateway;
import com.wendel.test.runTheBank.domain.Transaction;
import com.wendel.test.runTheBank.usecase.notification.SendNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendNotificationImpl implements SendNotification {
    private final WebGateway webGateway;

    public SendNotificationImpl(WebGateway webGateway) {
        this.webGateway = webGateway;
    }

    @Override
    public NotificationResponse execute(Transaction transaction) {
        log.info("Sending notification from transaction {}", transaction.getId());
        return webGateway.sendNotification(transaction);
    }
}
