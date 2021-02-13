package com.university.university.controller;

import com.university.university.service.StudentService;
import com.university.university.service.UniversityService;
import com.university.university.model.StudentModel;
import com.university.university.model.UniversityModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@RestController
public class university_controller {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private StudentService studentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/university")
    public ResponseEntity<List<UniversityModel>> allUniversities() {
        return ResponseEntity.ok(universityService.findAll());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/students")
    public ResponseEntity<List<StudentModel>> allStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/university/count")
    public ResponseEntity<Long> count() {

        return ResponseEntity.ok(universityService.count());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/university/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable String id) {

        Long universityId = Long.parseLong(id);
        universityService.deleteById(universityId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/university/{id}")
    public ResponseEntity<List<StudentModel>> allStudentsAttendingUniversityX(@PathVariable String id) {
        Optional<UniversityModel> uni = universityService.find(Long.parseLong(id));
        return ResponseEntity.ok(studentService.findByUniversity(uni.get()));
    }
}
