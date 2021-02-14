package com.university.university.service;

import com.university.university.model.UniversityModel;
import com.university.university.repo.UniversityRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepo universityRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public List<UniversityModel> findAll() {
        var it = universityRepo.findAll();

        var universities = new ArrayList<UniversityModel>();
        it.forEach(e -> universities.add(e));

        return universities;
    }

    //find a university by id
    public Optional<UniversityModel> find(long id) {
        return universityRepo.findById(id);
    }

    public void insert(UniversityModel university) {
        universityRepo.save(university);
    }

    public Long count() {
        return universityRepo.count();
    }

    public void deleteById(Long universityId) {
        universityRepo.deleteById(universityId);
    }

    public UniversityModel postUniversity(UniversityModel universityModel) { return universityRepo.save(universityModel);}

}
