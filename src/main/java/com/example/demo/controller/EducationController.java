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

    //TODO GTB-知识点: - 如果多个方法的 path 一样，可以提到 class level 去统一设置
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
