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
@RequestMapping("/users/{userId}/educations")
@Validated
@CrossOrigin("http://localhost:1234")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> findAllByUserId(@PathVariable Long userId) {
        return educationService.findAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Education createEducation(@PathVariable Long userId,
                                @RequestBody @Valid EducationRequestDTO educationRequestDTO) {
        return educationService.createEducation(userId, educationRequestDTO);
    }
}
