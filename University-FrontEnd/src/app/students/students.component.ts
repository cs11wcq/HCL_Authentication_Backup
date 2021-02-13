import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Student } from '../model/Student';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  students: Array<Student>;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    // this.students = this.dataService.students;
  }

}
