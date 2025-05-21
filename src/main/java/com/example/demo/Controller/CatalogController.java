package com.example.demo.Controller;


import com.example.demo.Models.Catalog;

import com.example.demo.Services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/{courseCode}")
    public ResponseEntity<Catalog> getCatalog(@PathVariable Long courseCode) {
        Catalog catalog = catalogService.getCatalogByCourseCode(courseCode);
        if (catalog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(catalog);
    }
}