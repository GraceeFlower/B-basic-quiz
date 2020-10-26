package com.example.demo.controller.dto;

import com.example.demo.model.Education;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class EducationsResponseDTO {

    public List<EducationResponseDTO> toList(List<Education> educations) {
        return educations.stream()
                .map(EducationResponseDTO::new)
                .collect(Collectors.toList());
    }
}
