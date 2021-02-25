import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
// this ervice sends signup, login HTTP POST request to back-end

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {headers: new HttpHeaders({ 'Content-Type': 'application/json' })};

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any>{
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any>{
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      password: user.password
    }, httpOptions);
  }
}
