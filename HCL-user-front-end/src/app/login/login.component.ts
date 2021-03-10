import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form:any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private base_url = environment.awsUrl+"/login";


  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private _http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    if(this.tokenStorage.getToken()){
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  async onSubmit():Promise<void>{

    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    this._http.post(this.base_url, this.form ,{ headers: headers}).subscribe(response => {
        console.log(response);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        // this.goToProfile();
      },
      err => {
        this.isLoginFailed = true;
      }
    );
    // this.authService.login(this.form).subscribe(
    //   data => {
    //     this.tokenStorage.saveToken(data.accessToken);
    //     this.tokenStorage.saveUser(data);

    //     this.isLoginFailed = false;
    //     this.isLoggedIn = true;
    //     this.roles = this.tokenStorage.getUser().roles;
    //     this.reloadPage();
    //   },
    //   err => {
    //     this.errorMessage = err.error.message;
    //     this.isLoginFailed = true;
    //   }
    // );
  }

  goToProfile(){
    this.router.navigate(['/profile']);
  }
  reloadPage(): void{
    window.location.reload();
  }
}
