package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.Models.StudentGrade;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDTO {
    private String courseName;
    private List<StudentGrade> studentGrades;
}