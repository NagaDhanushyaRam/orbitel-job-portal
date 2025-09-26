package com.orbitel.jobportal.controller;

import com.orbitel.jobportal.entity.Message;
import com.orbitel.jobportal.entity.User;
import com.orbitel.jobportal.service.MessageService;
import com.orbitel.jobportal.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {
  private final MessageService msgSvc;
  private final UserService userSvc;

  public MessageController(MessageService msgSvc, UserService userSvc) {
    this.msgSvc = msgSvc;
    this.userSvc = userSvc;
  }

  @GetMapping
  public String inbox(Authentication auth, Model m) {
    User me = userSvc.findByEmail(auth.getName()).orElseThrow();
    m.addAttribute("messages", msgSvc.inbox(me));
    return "messages-inbox";
  }

  @GetMapping("/sent")
  public String sent(Authentication auth, Model m) {
    User me = userSvc.findByEmail(auth.getName()).orElseThrow();
    m.addAttribute("messages", msgSvc.outbox(me));
    return "messages-sent";
  }

  @GetMapping("/compose")
  public String composeForm(Model m) {
    m.addAttribute("message", new Message());
    m.addAttribute("users", userSvc.findAll());
    return "message-form";
  }

  @PostMapping("/send")
  public String send(@ModelAttribute Message message, Authentication auth) {
    User me = userSvc.findByEmail(auth.getName()).orElseThrow();
    message.setSender(me);
    User recip = userSvc.findById(message.getRecipient().getId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid recipient"));
    message.setRecipient(recip);
    msgSvc.send(message);
    return "redirect:/messages";
  }

  @GetMapping("/{id:\\d+}")
  public String view(@PathVariable Long id, Authentication auth, Model m) {
    Message msg = msgSvc.findById(id);
    User me = userSvc.findByEmail(auth.getName()).orElseThrow();
    if (!msg.getRecipient().equals(me) && !msg.getSender().equals(me)) {
      return "redirect:/messages";
    }
    if (msg.getRecipient().equals(me) && !msg.getIsRead()) {
      msgSvc.markRead(msg);
    }
    m.addAttribute("message", msg);
    return "message-view";
  }

  @GetMapping("/{id}/reply")
  public String replyForm(
      @PathVariable Long id,
      Authentication auth,
      Model m) {

    Message orig = msgSvc.findById(id);
    User me = userSvc.findByEmail(auth.getName()).orElseThrow();

    if (!orig.getRecipient().equals(me)) {
      return "redirect:/messages";
    }

    Message reply = new Message();
    reply.setRecipient(orig.getSender());
    reply.setSubject("Re: " + orig.getSubject());

    m.addAttribute("message", reply);
    m.addAttribute("replyToId", id); 
    m.addAttribute("users", userSvc.findAll());
    return "message-form";
  }

  @PostMapping("/{id}/reply")
  public String sendReply(
      @PathVariable Long id,
      @ModelAttribute Message message,
      Authentication auth) {
    User me = userSvc.findByEmail(auth.getName()).orElseThrow();

    
    message.setSender(me);

   
    msgSvc.send(message);

    
    return "redirect:/messages";
  }
}
