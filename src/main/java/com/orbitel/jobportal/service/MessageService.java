package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.Message;
import com.orbitel.jobportal.entity.User;

import java.util.List;

public interface MessageService {
  List<Message> inbox(User me);
  List<Message> outbox(User me);
  Message findById(Long id);
  void markRead(Message msg);
  void send(Message msg);
}
