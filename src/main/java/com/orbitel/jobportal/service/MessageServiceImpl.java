package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.Message;
import com.orbitel.jobportal.entity.User;
import com.orbitel.jobportal.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

  private final MessageRepository repo;

  public MessageServiceImpl(MessageRepository repo) {
    this.repo = repo;
  }

  @Override
  public List<Message> inbox(User me) {
    return repo.findByRecipientOrderBySentAtDesc(me);
  }

  @Override
  public List<Message> outbox(User me) {
    return repo.findBySenderOrderBySentAtDesc(me);
  }

  @Override
  public Message findById(Long id) {
    return repo.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Message not found"));
  }

  @Override
  public void markRead(Message msg) {
    msg.setIsRead(true);
    repo.save(msg);
  }

  @Override
  public void send(Message msg) {
    
    repo.save(msg);
  }
}
