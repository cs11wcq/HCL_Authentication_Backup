import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private base_url = 'http://localhost:4200/register';

  constructor(private _http: HttpClient) { }

  register()
  {
    console.log("hello");
    console.log("base_url", this.base_url);
    this._http.post<any>(this.base_url, '');
  }
}
