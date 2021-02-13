import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule, Routes } from '@angular/router';
import { StudentsComponent } from './students/students.component';
import { StudentComponent } from './students/student/student.component';
import { UniversitiesComponent } from './universities/universities.component';
import { UniversityComponent } from './universities/university/university.component';
import { HomeComponent } from './home/home.component';
import { CreateUniversityComponent } from './create-university/create-university.component';
import { CreateStudentComponent } from './create-student/create-student.component';
import { UpdateStudentComponent } from './update-student/update-student.component';
import { UpdateUniversityComponent } from './update-university/update-university.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    StudentsComponent,
    StudentComponent,
    UniversitiesComponent,
    UniversityComponent,
    HomeComponent,
    CreateUniversityComponent,
    CreateStudentComponent,
    UpdateStudentComponent,
    UpdateUniversityComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
