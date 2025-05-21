package com.example.demo.Services;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.DTO.CourseGradeDTO;

import com.example.demo.DTO.GradeDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Models.Catalog;
import com.example.demo.Models.StudentGrade;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



@Service
public class CatalogService {

    private final RestTemplate restTemplate;

    public CatalogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Catalog getCatalogByCourseCode(Long courseCode) {
        String gradesUrl = "http://grades-data-service/api/course/" + courseCode;
        CourseDTO courseDTO = restTemplate.getForObject(gradesUrl, CourseDTO.class);

        if (courseDTO == null) {
            return null;
        }

        CourseGradeDTO courseGradeDTO = restTemplate.getForObject(
                "http://grades-data-service/api/course/" + courseCode + "/grade",
                CourseGradeDTO.class
        );



        List<StudentGrade> studentGrades = new ArrayList<>();

        if (courseGradeDTO != null && courseGradeDTO.getGrades() != null) {
            for (GradeDTO grade : courseGradeDTO.getGrades()) {
                StudentDTO student = restTemplate.getForObject(
                        "http://student-info-service/api/students/" + grade.getStudentId(),
                        StudentDTO.class
                );

                if (student != null) {
                    studentGrades.add(new StudentGrade(
                            student.getName(),
                            student.getAge(),
                            grade.getGrade()
                    ));
                }
            }
        }

        return new Catalog(courseDTO.getCourseName(), studentGrades);
    }
}