import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from 'src/app/data.service';
import { Student } from 'src/app/model/Student';
import { University } from 'src/app/model/University';

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.css']
})
export class UniversityComponent implements OnInit {

  id: number;
  name: string;
  students: Array<Student>;
  student: Student;

  constructor(private route: ActivatedRoute, private dataService: DataService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.student = new Student();
    this.students = new Array <Student>();
    this.dataService.getUniversityById(this.id).subscribe( data => {
      console.log(data);
      this.students = data;
      console.log(this.student.degree);
     })
  }
}
