import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { HttpClient } from '@angular/common/http'

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
  private base_url = 'localhost:8080/registration'

  constructor(private authService: AuthService, private _http: HttpClient) { }

  ngOnInit(): void {
  }

  async onSubmit():Promise<void>{
    console.log(this.form)
    const recv = await this._http.post<any>(this.base_url, JSON.stringify(this.form));

    console.log(recv)
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
