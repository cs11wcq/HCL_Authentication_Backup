import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateUniversityComponent } from './create-university/create-university.component';
import { HomeComponent } from './home/home.component';
import { University } from './model/University';
import { StudentsComponent } from './students/students.component';
import { UniversitiesComponent } from './universities/universities.component';
import { UpdateStudentComponent } from './update-student/update-student.component';
import { UpdateUniversityComponent } from './update-university/update-university.component';
import { Student } from './model/Student'
import{ UniversityComponent } from './universities/university/university.component'

const routes: Routes =[
  // {path: 'home', component: HomeComponent},
  // {path: 'students', component: StudentsComponent},
  {path: 'universities', component: UniversitiesComponent},
  {path: 'create-university', component: CreateUniversityComponent},
  {path: '', redirectTo: 'university/1', pathMatch: 'full'},
  {path: 'update-university/:id', component: UpdateUniversityComponent},
  {path: 'Update-student/:id', component: UpdateStudentComponent},
  {path: 'university/:id', component: UniversityComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
