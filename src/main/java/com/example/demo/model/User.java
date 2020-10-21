package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private long age;
    @NotNull
    private String avatar;
    private String description;
}
