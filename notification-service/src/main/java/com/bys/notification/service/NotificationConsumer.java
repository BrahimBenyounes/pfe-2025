package com.bys.notification.service;

import com.bys.notification.model.Notification;
import com.bys.notification.dto.NotificationSetExpiredRequest;

import java.util.List;
import java.util.Optional;

public interface NotificationConsumer {

    public List<Notification> getAll();

    public Boolean setExpiration(NotificationSetExpiredRequest notificationSetExpiredRequest);

    public Optional<Notification> getById(Long id);
}
