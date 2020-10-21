package com.example.demo.controller;

import com.example.demo.controller.dto.EducationRequestDTO;
import com.example.demo.model.Education;
import com.example.demo.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@CrossOrigin("http://localhost:1234")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/users/{userId}/educations")
    public List<Education> findAllByUserId(@PathVariable int userId) {
        return educationService.findAllByUserId(userId);
    }

    @PostMapping("/users/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEducation(@PathVariable int userId,
                                @RequestBody @Valid EducationRequestDTO educationRequestDTO) {
        educationService.createEducation(new Education(userId, educationRequestDTO.getYear(),
                educationRequestDTO.getTitle(), educationRequestDTO.getDescription()));
    }
}
