package com.example.demo.repository;

import com.example.demo.dataprovider.DataProvider;
import com.example.demo.model.Education;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EducationRepository {

    private final List<Education> educationList = DataProvider.educationList;

    public List<Education> findAllByUserId(int userId) {
        return educationList.stream()
                //TODO GTB-工程实践: - 尽量使用 equals()
                .filter(education -> education.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public void save(Education education) {
        educationList.add(education);
    }
}
