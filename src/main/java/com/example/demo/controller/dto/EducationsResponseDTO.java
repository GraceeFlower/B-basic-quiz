package com.example.demo.controller.dto;

import com.example.demo.model.Education;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationsResponseDTO {

    private List<EducationResponseDTO> educationResponseList;

}
