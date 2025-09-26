package com.orbitel.jobportal.service;

import com.orbitel.jobportal.dto.AdminDashboardDTO;
import com.orbitel.jobportal.entity.ApplicationStatus; 
import com.orbitel.jobportal.repository.ApplicationRepository;
import com.orbitel.jobportal.repository.JobRepository;
import com.orbitel.jobportal.repository.MessageRepository;
import com.orbitel.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

  private final UserRepository        userRepo;
  private final JobRepository         jobRepo;
  private final ApplicationRepository appRepo;
  private final MessageRepository     msgRepo;

  public AdminServiceImpl(UserRepository userRepo,
                          JobRepository jobRepo,
                          ApplicationRepository appRepo,
                          MessageRepository msgRepo) {
    this.userRepo = userRepo;
    this.jobRepo  = jobRepo;
    this.appRepo  = appRepo;
    this.msgRepo  = msgRepo;
  }

  @Override
  public AdminDashboardDTO getDashboardMetrics() {
    AdminDashboardDTO d = new AdminDashboardDTO();
    d.setTotalUsers(userRepo.count());
    d.setTotalJobPosts(jobRepo.count());
    d.setTotalApplications(appRepo.count());
    d.setPendingApplications(appRepo.countByStatus(ApplicationStatus.PENDING));
    d.setHiredApplications(appRepo.countByStatus(ApplicationStatus.HIRED));
    d.setTotalMessages(msgRepo.count());
    d.setUnreadMessages(msgRepo.countByIsReadFalse());
    return d;
  }
}
