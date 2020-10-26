package com.example.demo.controller.dto;

import com.example.demo.model.Education;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseDTO {

    private Long educationId;
    private long year;
    private String title;
    private String description;
}
