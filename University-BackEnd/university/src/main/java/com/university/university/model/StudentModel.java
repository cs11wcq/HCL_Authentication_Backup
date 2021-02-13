package com.university.university.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long student_id;
    private String name;
    private String degree;

    @ManyToOne
    private UniversityModel university;

    public StudentModel() {
    }

    public StudentModel(String name, String deg) {
        this.name = name;
        this.degree = deg;
    }

    public Long getId() {
        return student_id;
    }

    public void setId(Long id) {
        this.student_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public UniversityModel getUniversity() {
        return university;
    }

    public void setUniversity(UniversityModel university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        StudentModel studentModel = (StudentModel) o;
        return Objects.equals(student_id, studentModel.student_id) && Objects.equals(name, studentModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, name, university, degree);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("University{");
        sb.append("id=").append(student_id);
        sb.append(", name= '").append(name).append('\'');
        sb.append(", university= '").append(university.getName()).append('\'');
        sb.append(", degree= '").append(degree).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

/*
 * 
 * 
 * 123 university
 * 
 * university [students]
 * 
 * student.university = 123
 * 
 * USC = 1001 student_university_id = 1001
 * 
 * SELECT * FROM Students WHERE student_university_id = 1001
 * 
 */