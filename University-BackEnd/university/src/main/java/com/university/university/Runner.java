package com.university.university;

import javax.transaction.Transactional;

import com.university.university.model.StudentModel;
import com.university.university.model.UniversityModel;
import com.university.university.repo.StudentRepo;
import com.university.university.repo.UniversityRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    @Autowired
    private UniversityRepo universityRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        logger.info("initializing universities");

        var u1 = new UniversityModel("YALE");
        universityRepo.save(u1);

        var u2 = new UniversityModel("USC");
        universityRepo.save(u2);

        var u3 = new UniversityModel("STANFORD");
        universityRepo.save(u3);

        StudentModel model = new StudentModel("name", "deg");
        model.setUniversity(u1);
        studentRepo.save(model);
        model = new StudentModel("Zaid", "computer science");
        model.setUniversity(u2);
        studentRepo.save(model);
        model = new StudentModel("name3", "deg");
        model.setUniversity(u3);
        studentRepo.save(model);
    }
}
