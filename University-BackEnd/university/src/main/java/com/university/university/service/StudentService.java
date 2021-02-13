package com.university.university.service;

import com.university.university.model.StudentModel;
import com.university.university.model.UniversityModel;
import com.university.university.repo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public List<StudentModel> findAll() {
        var it = studentRepo.findAll();

        var universities = new ArrayList<StudentModel>();
        it.forEach(e -> universities.add(e));

        return universities;
    }

    public Optional<StudentModel> find(long id) {
        return studentRepo.findById(id);
    }

    public void insert(StudentModel university) {
        studentRepo.save(university);
    }

    public Long count() {
        return studentRepo.count();
    }

    public void deleteById(Long universityId) {
        studentRepo.deleteById(universityId);
    }

    public List<StudentModel> findByUniversity(UniversityModel uni) {
        List<StudentModel> it = (List<StudentModel>) studentRepo.findAll();

        List<StudentModel> studentsAttending = new ArrayList<StudentModel>();

        it.forEach(e -> {
            if (!studentsAttending.contains(studentRepo.findByUniversity(uni))) {
                studentsAttending.add(studentRepo.findByUniversity(uni));
            }
        });

        return studentsAttending;
    }
}
