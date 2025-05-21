package com.example.demo.Models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {
    private String cousreName;
    private List<StudentGrade> studentGrades;
}
