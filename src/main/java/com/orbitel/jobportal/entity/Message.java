package com.orbitel.jobportal.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sender_id", nullable = false)
  private User sender;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipient_id", nullable = false)
  private User recipient;

  private String subject;

  @Lob
  private String body;

  @Column(name = "sent_at", nullable = false, updatable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime sentAt;

  @Column(name = "is_read", nullable = false)
  private Boolean isRead = false;

  @PrePersist
  protected void onCreate() {
    sentAt = LocalDateTime.now();
  }

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public User getSender() {
    return sender;
}

public void setSender(User sender) {
    this.sender = sender;
}

public User getRecipient() {
    return recipient;
}

public void setRecipient(User recipient) {
    this.recipient = recipient;
}

public String getSubject() {
    return subject;
}

public void setSubject(String subject) {
    this.subject = subject;
}

public String getBody() {
    return body;
}

public void setBody(String body) {
    this.body = body;
}

public LocalDateTime getSentAt() {
    return sentAt;
}

public void setSentAt(LocalDateTime sentAt) {
    this.sentAt = sentAt;
}

public Boolean getIsRead() {
    return isRead;
}

public void setIsRead(Boolean isRead) {
    this.isRead = isRead;
}
}
