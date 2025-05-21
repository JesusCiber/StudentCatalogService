package com.example.demo.Services;

import com.example.demo.DTO.CourseGradeDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Models.Catalog;
import com.example.demo.Models.StudentGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CatalogService {

    private final RestTemplate restTemplate;

    @Autowired
    public CatalogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Catalog getCatalogByCourseCode(Long courseCode) {
        String gradesUrl = "http://grades-data-service/api/course/" + courseCode + "/grade";
        CourseGradeDTO courseGradeDTO = restTemplate.getForObject(gradesUrl, CourseGradeDTO.class);

        if (courseGradeDTO == null) {
            return null;
        }

        List<StudentGrade> studentGrades = courseGradeDTO.getGrades().stream()
                .map(grade -> {
                    String studentUrl = "http://student-info-service/api/students/" + grade.getStudentId();
                    StudentDTO student = restTemplate.getForObject(studentUrl, StudentDTO.class);

                    if (student == null) {
                        return null;
                    }

                    return new StudentGrade(student.getStudentName(), student.getStudentAge(),grade.getGrade());
                })
                .collect(Collectors.toList());

        return new Catalog(courseGradeDTO.getCourseName(), studentGrades);
    }
}