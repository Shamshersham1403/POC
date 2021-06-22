import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { UserInfo } from '../model/user-info';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private baseUrl = 'http://localhost:8080/';  
  constructor(private http:HttpClient) { }


  createUser(userInfo: UserInfo): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+'user', userInfo);  
  }  
 
  getAllUser(): Observable<any>{
    return this.http.get(`${this.baseUrl}`+'user')
  }
  deleteUser(id:any,status:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}user/`+id+"?status="+status, {responseType: 'text'});
  }
 
  UpUser(userInfo: UserInfo): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+'user', userInfo);  
  }  

  sortUserList(sortkey:string): Observable<any>{
    return this.http.get(`${this.baseUrl}`+'user/sort?sortKey='+sortkey ); 
  }
}
