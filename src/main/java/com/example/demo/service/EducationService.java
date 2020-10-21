package com.example.demo.service;

import com.example.demo.model.Education;
import com.example.demo.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> findAllByUserId(int userId) {
        return educationRepository.findAllByUserId(userId);
    }

    public void createEducation(Education education) {
        educationRepository.save(education);
    }
}
