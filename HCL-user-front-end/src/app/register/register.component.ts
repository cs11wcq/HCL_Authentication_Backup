import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {environment} from "../../environments/environment";
// import { environment } from './environment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  private base_url = environment.awsUrl+"/register";

  constructor(private authService: AuthService, private _http: HttpClient) { }

  ngOnInit(): void {
  }

  async onSubmit():Promise<void>{
    console.log(this.form)
    // console.log(await this._http.post<any>(this.base_url, JSON.stringify(this.form)));

    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    this._http.post(this.base_url, this.form ,{ headers: headers
    }).subscribe(response => { console.log(response);})

    // this.authService.register(this.form).subscribe(
    //   data =>{
    //     console.log(data);
    //     this.isSuccessful = true;
    //     this.isSignUpFailed = false;
    //     this._http.post<any>(this.base_url, JSON.stringify(data))
    //   },
    //   err => {
    //     console.log(JSON.stringify(this.form));
    //     this._http.post<any>(this.base_url, JSON.stringify(this.form))
    //     this.errorMessage = err.error.message;
    //     this.isSignUpFailed = true;
    //   }
    // )
  }

}
