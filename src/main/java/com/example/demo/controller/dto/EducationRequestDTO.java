package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequestDTO {

    @NotNull
    @Pattern(regexp = "^[0-9]{4}$", message = "年份不合法")
    private long year;

    @NotNull
    @Length(min = 1, max = 256, message = "标题不合法")
    private String title;

    @NotNull
    @Length(min=1 ,max = 4096, message = "描述不合法")
    private String description;
}
