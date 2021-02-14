package com.university.university.controller;

import com.university.university.service.StudentService;
import com.university.university.service.UniversityService;
import com.university.university.model.StudentModel;
import com.university.university.model.UniversityModel;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * delete a university from list of universities
     * @param id
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/university/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUniversity(@PathVariable String id) {

        Long universityId = Long.parseLong(id);
        universityService.deleteById(universityId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    /**
     * get a list of all students attending a particular university with id: id
     * @param id
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/university/{id}")
    public ResponseEntity<List<StudentModel>> allStudentsAttendingUniversityX(@PathVariable String id) {
        Optional<UniversityModel> uni = universityService.find(Long.parseLong(id));
        return ResponseEntity.ok(studentService.findByUniversity(uni.get()));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/university")
    public UniversityModel postUniversity(@RequestBody UniversityModel universityModel) {
        return universityService.postUniversity(universityModel);
    }

    /**
     * note: spring boot automatically converts passed in json to a StudentModel object using @RequestBody
     * post a student to a specific university
     * @param studentModel
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/university/{id}")
    public ResponseEntity<StudentModel> postStudent(@PathVariable String id, @RequestBody StudentModel studentModel) {
        //find the university with id: id
        Optional<UniversityModel> uni = universityService.find(Long.parseLong(id));

        if (uni.isPresent()) {
            studentModel.setUniversity(uni.get()); //set student's university field
            studentService.insert(studentModel);
            return ResponseEntity.ok(studentModel);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //https://stackoverflow.com/questions/22536059/how-to-return-not-found-status-from-spring-controller
    }

    /**
     * delete a student
     * @param id
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudentFromUniversity(@PathVariable String id) {

        Long studentID = Long.parseLong(id);
        studentService.deleteById(studentID);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    /**
     * Update a university
     *
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/university/{id}")
    public ResponseEntity<UniversityModel> updateUniversity(@PathVariable String id, @RequestBody UniversityModel universityModel) {
        Optional<UniversityModel> uni = universityService.find(Long.parseLong(id));

        if (uni.isPresent()) {
            UniversityModel university = uni.get();
            university.setName(universityModel.getName());
            universityService.insert(university);
            return ResponseEntity.ok(universityModel);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //https://stackoverflow.com/questions/22536059/how-to-return-not-found-status-from-spring-controller
    }

    /**
     * Update a student
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentModel> updateStudent(@PathVariable String id, @RequestBody StudentModel studentModel) {
        Optional<StudentModel> stu = studentService.find(Long.parseLong(id));

        if (stu.isPresent()) {
            StudentModel student = stu.get();
            //if statements to retain the other non updated info
            if (studentModel.getName() != null)
                student.setName(studentModel.getName());
            if (studentModel.getDegree() != null)
                student.setDegree(studentModel.getDegree());
            if (studentModel.getUniversity() != null)
                student.setUniversity(studentModel.getUniversity());
            studentService.insert(student);
            return ResponseEntity.ok(studentModel);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //https://stackoverflow.com/questions/22536059/how-to-return-not-found-status-from-spring-controller
    }

    /**
     * get a student
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentModel> getStudentByID(@PathVariable String id) {
        return ResponseEntity.ok(studentService.find(Long.parseLong(id)).get());
    }
}
