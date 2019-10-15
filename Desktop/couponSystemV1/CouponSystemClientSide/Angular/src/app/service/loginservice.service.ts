
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {

 public constructor(private httpClient: HttpClient) { }


 public login(username: string, password: string, loginType ): Observable<any> {
  return this.httpClient.post('http://localhost:8080/cs/login?userName='
   + username + '&password=' + password + '&loginType=' + loginType,null,
    { withCredentials: true , responseType: 'text'});
}

public register(username: string,password: string, email: string, loginType: string): Observable<any> {
  return this.httpClient.post('http://localhost:8080/cs/register?userName='
   + username + '&password=' + password + '&email=' + email + '&loginType=' +loginType.toUpperCase(), null,
    { withCredentials: true , responseType: 'text'});
}

}
