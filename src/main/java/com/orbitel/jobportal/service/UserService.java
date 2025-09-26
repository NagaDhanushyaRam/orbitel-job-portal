package com.orbitel.jobportal.service;

import com.orbitel.jobportal.dto.UserRegistrationDTO;
import com.orbitel.jobportal.entity.User;
import java.util.Optional;
import java.util.List;

public interface UserService {
    User register(UserRegistrationDTO dto);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    Optional<User> findById(Long id);
}
