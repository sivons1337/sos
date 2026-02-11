package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import com.laa66.dao.NotificationDao;
import com.laa66.model.Notification;
import com.laa66.model.Student;

@Transactional
public class NotificationDaoImpl implements NotificationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Notification createNotification(String content, LocalDateTime sentAt, String type, String status, Student student) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setSentAt(sentAt);
        notification.setType(type);
        notification.setStatus(status);
        notification.setStudent(student);

        entityManager.persist(notification);
        return notification;
    }

    @Override
    public Notification getNotification(Integer notificationId) {
        return entityManager.find(Notification.class, notificationId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        TypedQuery<Notification> query = entityManager.createQuery("SELECT n FROM Notification n", Notification.class);
        return query.getResultList();
    }

    @Override
    public Notification updateNotification(Integer notificationId, String content, LocalDateTime sentAt, String type, String status) {
        Notification notification = entityManager.find(Notification.class, notificationId);
        if (notification != null) {
            notification.setContent(content);
            notification.setSentAt(sentAt);
            notification.setType(type);
            notification.setStatus(status);
            entityManager.merge(notification);
        }
        return notification;
    }

    @Override
    public void deleteNotification(Integer notificationId) {
        Notification notification = entityManager.find(Notification.class, notificationId);
        if (notification != null) {
            entityManager.remove(notification);
        }
    }

    @Override
    public List<Notification> getNotificationsByStudent(Integer studentId) {
        TypedQuery<Notification> query = entityManager.createQuery(
                "SELECT n FROM Notification n WHERE n.student.studentId = :studentId", Notification.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    public List<Notification> getNotificationsByType(String type) {
        TypedQuery<Notification> query = entityManager.createQuery(
                "SELECT n FROM Notification n WHERE n.type = :type", Notification.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<Notification> getNotificationsByStatus(String status) {
        TypedQuery<Notification> query = entityManager.createQuery(
                "SELECT n FROM Notification n WHERE n.status = :status", Notification.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<Notification> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        TypedQuery<Notification> query = entityManager.createQuery(
                "SELECT n FROM Notification n WHERE n.sentAt BETWEEN :startDate AND :endDate", Notification.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}