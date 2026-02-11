package com.laa66.dao;

import com.laa66.model.Notification;
import com.laa66.model.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationDao {
    Notification createNotification(String content, LocalDateTime sentAt, String type, String status, Student student);

    Notification getNotification(Integer notificationId);

    List<Notification> getAllNotifications();

    Notification updateNotification(Integer notificationId, String content, LocalDateTime sentAt, String type, String status);

    void deleteNotification(Integer notificationId);

    List<Notification> getNotificationsByStudent(Integer studentId);

    List<Notification> getNotificationsByType(String type);

    List<Notification> getNotificationsByStatus(String status);

    List<Notification> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}