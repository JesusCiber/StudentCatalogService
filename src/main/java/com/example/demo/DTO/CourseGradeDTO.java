package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseGradeDTO {
    private Long courseCode;
    private String courseName;
    private List<GradeDTO> grades;
}
