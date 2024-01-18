package com.wendel.test.runTheBank.usecase.notification;

import com.wendel.test.runTheBank.adapter.controller.response.NotificationResponse;
import com.wendel.test.runTheBank.domain.Transaction;

public interface SendNotification {
    NotificationResponse execute(Transaction transaction);
}
