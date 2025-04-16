package com.bys.notification;

import com.bys.notification.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.bys.notification.constant.AppConstants;
import com.bys.notification.dto.NotificationRequest;
import com.bys.notification.model.Notification;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class NotificationServerApplication {
    @Autowired
    NotificationRepository notificationRepository;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServerApplication.class, args);
    }


    public void init(NotificationRequest notificationRequest) {
        log.info("Get new incoming messages from producer with topic {}", AppConstants.TOPIC_NAME);
        Notification notification = Notification.builder()
                .message(notificationRequest.getMessage())
                .serviceName(notificationRequest.getServiceName())
                .createdAt(notificationRequest.getCreatedAt())
                .isExpired(0)
                .build();
        notificationRepository.save(notification);
    }
}
