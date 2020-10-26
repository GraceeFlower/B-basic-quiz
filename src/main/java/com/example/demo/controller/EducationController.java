package com.example.demo.controller;

import com.example.demo.controller.dto.EducationRequestDTO;
import com.example.demo.controller.dto.EducationResponseDTO;
import com.example.demo.controller.dto.EducationsResponseDTO;
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
    public List<EducationResponseDTO> findAllByUserId(@PathVariable Long userId) {
        List<Education> educations = educationService.findAllByUserId(userId);
        return new EducationsResponseDTO().toList(educations);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationResponseDTO createEducation(@PathVariable Long userId,
                                @RequestBody @Valid EducationRequestDTO educationRequestDTO) {
        Education education = educationService.createEducation(userId, educationRequestDTO);
        return new EducationResponseDTO(education);

    }
}
