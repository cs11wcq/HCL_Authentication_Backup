import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private base_url = 'http://localhost:4200/registration'

  constructor(private _http: HttpClient) { }

  register()
  {
    this._http.post<any>(this.base_url, '')
  }
}
