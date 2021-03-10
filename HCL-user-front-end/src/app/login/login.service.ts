import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private base_url = 'http://localhost:4200/login'

  constructor(private _http: HttpClient) { }

  login()
  {
    console.log("base_url", this.base_url);
    this._http.post<any>(this.base_url, '');
  }
}
