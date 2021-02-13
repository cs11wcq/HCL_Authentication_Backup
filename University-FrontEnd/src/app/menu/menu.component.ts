import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../data.service';
import { University } from '../model/University';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  universities: Array <University>;
  constructor(private dataService: DataService,
    private router: Router) { }

  ngOnInit(): void {
    this.getUniversities();
  }

  navigateToHome(){
    this.router.navigate(['home']);
  }

  navigateToUniverity(){
    this.router.navigate(['universities']);
  }

  navigateToStudent(){
    this.router.navigate(['students']);
  }


  private getUniversities(){
    this.dataService.getUniversity().subscribe(data => {
      this.universities = data;
    });
  }

  // employeeDetails(id: number){
  //   this.router.navigate(['employee-details', id]);
  // }

  // updateEmployee(id: number){
  //   this.router.navigate(['update-employee', id]);
  // }

  // deleteEmployee(id: number){
  //   this.dataService.deleteUniversity.subscribe( data => {
  //     console.log(data);
  //     this.getEmployees();
  //   })
  // }
}
