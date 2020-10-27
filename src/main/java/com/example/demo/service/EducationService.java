package com.example.demo.service;

import com.example.demo.controller.dto.EducationRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.model.User;
import com.example.demo.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserService userService;
    private final AtomicLong educationIdSeq = new AtomicLong();

    public EducationService(EducationRepository educationRepository, UserService userService) {
        this.educationRepository = educationRepository;
        this.userService = userService;
    }

    public List<Education> findAllByUserId(Long userId) {
        userService.findUserById(userId);
        return educationRepository.findAllByUserId(userId);
    }

    public Education createEducation(Long userId, EducationRequestDTO educationRequestDTO) {
        User user = userService.findUserById(userId);

        Education education = new Education(
                educationIdSeq.incrementAndGet(),
                educationRequestDTO.getYear(),
                educationRequestDTO.getTitle(),
                educationRequestDTO.getDescription(),
                user
        );
        return educationRepository.save(education);
    }
}
