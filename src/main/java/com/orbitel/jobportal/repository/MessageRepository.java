package com.orbitel.jobportal.repository;

import com.orbitel.jobportal.entity.Message;
import com.orbitel.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
  
  List<Message> findByRecipientOrderBySentAtDesc(User recipient);
  
  
  List<Message> findBySenderOrderBySentAtDesc(User sender);

  long countByIsReadFalse();
}
