package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotNull
    @Length(min=1, max=128, message = "用户名不合法")
    private String name;

    @NotNull
    @Min(value = 17, message = "年龄不合法")
    private long age;

    @NotNull
    @Length(min=8 ,max = 512, message = "头像链接不合法")
    private String avatar;

    @Length(min=0, max = 1024, message = "描述过长")
    private String description;
}
