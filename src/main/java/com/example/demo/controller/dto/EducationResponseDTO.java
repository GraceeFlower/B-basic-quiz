package com.example.demo.controller.dto;

import com.example.demo.model.Education;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseDTO {

    private Long educationId;
    private Long year;
    private String title;
    private String description;

    public EducationResponseDTO(Education education) {
        this.educationId = education.getEducationId();
        this.year = education.getYear();
        this.title = education.getTitle();
        this.description = education.getDescription();
    }
}
