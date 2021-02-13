import { Injectable } from '@angular/core';
import { Student } from './model/Student';
import { University } from './model/University';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private baseURL = "http://localhost:8080/university";
  private baseURL_Students = "http://localhost:8080/students";

  constructor(private httpClient: HttpClient) {

    // this.students = new Array<Student>();
    // const student1 = new Student();
    // student1.id = 1;
    // student1.unid = 1;
    // student1.name = "Weihong";
    // student1.gradYear = 2021;

    // const student2 = new Student();
    // student2.id = 2;
    // student2.unid = 1;
    // student2.name = "Seth";
    // student2.gradYear = 2021;

    // const student3 = new Student();
    // student3.id = 3;
    // student3.unid = 2;
    // student3.name = "Caleb";
    // student3.gradYear = 2022;

    // const student4 = new Student();
    // student4.id = 4;
    // student4.unid = 2;
    // student4.name = "Karina";
    // student4.gradYear = 2021;

    // const student5 = new Student();
    // student5.id = 5;
    // student5.unid = 3;
    // student5.name = "Brandon";
    // student5.gradYear = 2023;

    // const student6 = new Student();
    // student6.id = 6;
    // student6.unid = 3;
    // student6.name = "Zaid";
    // student6.gradYear = 2022;

    // this.students.push(student1);
    // this.students.push(student2);
    // this.students.push(student3);
    // this.students.push(student4);
    // this.students.push(student5);
    // this.students.push(student6);


    // //university llist
    // this.universities = new Array<University>();

    // const university1 = new University();
    // university1.id = 1;
    // university1.name = 'UCSD';

    // const currentStudent1 = new UniStudent();
    // currentStudent1.id = 1;
    // currentStudent1.unid = 1;
    // currentStudent1.name = "Weihong";
    // currentStudent1.gradYear = 2021;

    // const currentStudent2 = new UniStudent();
    // currentStudent2.id = 2;
    // currentStudent2.unid = 1;
    // currentStudent2.name = "Seth";
    // currentStudent2.gradYear = 2021;

    // const university2 = new University();
    // university2.id = 2;
    // university2.name = 'UCLA';

    // const currentStudent3 = new UniStudent();
    // currentStudent3.id = 3;
    // currentStudent3.unid = 2;
    // currentStudent3.name = "Caleb";
    // currentStudent3.gradYear = 2022;


    // const currentStudent4 = new UniStudent();
    // currentStudent4.id = 4;
    // currentStudent4.unid = 2;
    // currentStudent4.name = "Karina";
    // currentStudent4.gradYear = 2021;



    // const university3 = new University();
    // university3.id = 3;
    // university3.name = 'UCB';

    // const currentStudent5 = new UniStudent();
    // currentStudent5.id = 5;
    // currentStudent5.unid = 3;
    // currentStudent5.name = "Brandon";
    // currentStudent5.gradYear = 2023;

    // const currentStudent6 = new UniStudent();
    // currentStudent6.id = 6;
    // currentStudent6.unid = 3;
    // currentStudent6.name = "Zaid";
    // currentStudent6.gradYear = 2022;



    // university1.students.push(currentStudent1);
    // university1.students.push(currentStudent2);

    // university2.students.push(currentStudent3);
    // university2.students.push(currentStudent4);

    // university3.students.push(currentStudent5);
    // university3.students.push(currentStudent6);


    // this.universities.push(university1);
    // this.universities.push(university2);
    // this.universities.push(university3);
  }

  //universities
  getUniversity(): Observable<Array<University>>{
    console.log(this.httpClient.get<Array<University>>(`${this.baseURL}`)); // this is for testing purposes only
    return this.httpClient.get<Array<University>>(`${this.baseURL}`);
  }

  // createUniversity(university: University): Observable<Object>{
  //   return this.httpClient.post(`${this.baseURL}`, university);
  // }

  getUniversityById(id: number): Observable<Array<Student>>{
    return this.httpClient.get<Array<Student>>(`${this.baseURL}/${id}`);
  }


  // updateUniversity(id: number, university: University): Observable<Object>{
  //   return this.httpClient.put(`${this.baseURL}/${id}`, university);
  // }n

  // deleteUniversity(id: number): Observable<Object>{
  //   return this.httpClient.delete(`${this.baseURL}/${id}`);
  // }

  // //students
  // getStudents(): Observable<Student[]>{
  //   return this.httpClient.get<Student[]>(`${this.baseURL_Students}`);
  // }

  // createStudent(student: Student): Observable<Object>{
  //   return this.httpClient.post(`${this.baseURL}`, student);
  // }

  // getStudentById(id: number): Observable<Student>{
  //   return this.httpClient.get<Student>(`${this.baseURL}/${id}`);
  // }

  // updateStudent(id: number, student: Student): Observable<Object>{
  //   return this.httpClient.put(`${this.baseURL}/${id}`, student);
  // }

  // deleteStudent(id: number): Observable<Object>{
  //   return this.httpClient.delete(`${this.baseURL}/${id}`);
  // }

}
