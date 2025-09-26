package com.orbitel.jobportal.service;

import com.orbitel.jobportal.dto.UserRegistrationDTO;
import com.orbitel.jobportal.entity.Role;
import com.orbitel.jobportal.entity.User;
import com.orbitel.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRegistrationDTO dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        u.setRole(Role.valueOf(dto.getRole()));
        return userRepo.save(u);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public  List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) { 
        return userRepo.findById(id);
    }
}
