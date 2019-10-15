import { Company } from './../model/Company';
import { Customer } from './../model/Customer';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminserviceService {

  headers = new HttpHeaders({ 'Content-Type': 'application/json' });


 public  constructor(private httpClient: HttpClient) { }



 //Customer
 public addCustomer(customer:Customer):Observable<any>{
  return this.httpClient.post("http://localhost:8080/cs/webapi/admin/createCustomer",
  customer,{withCredentials:true , responseType: 'text'});
}

public removeCustomer(id:number):Observable<any>{

  return this.httpClient.delete
  ("http://localhost:8080/cs/webapi/admin/removeCustomer/"+id

  ,{withCredentials:true, responseType: 'text'});

}

public updateCustomer(customer:Customer):Observable<Customer>{
  return this.httpClient.put("http://localhost:8080/cs/webapi/admin/updateCustomer",
  customer,{withCredentials:true});
}

public getCustomer(id:number):Observable<Customer>{

  return this.httpClient.get<Customer>
  ("http://localhost:8080/cs/webapi/admin/getCustomer/" +id

  ,{withCredentials:true});

}

public getAllCustomers():Observable<Customer[]> {
  return this.httpClient.get<Customer[]>
  ("http://localhost:8080/cs/webapi/admin/getAllCustomers"
  ,{withCredentials:true});
}





//Company

public addCompany(company:Company):Observable<any>{
  return this.httpClient.post("http://localhost:8080/cs/webapi/admin/createCompany",
  company,{withCredentials:true, responseType: 'text'});
}

public removeCompany(id:number):Observable<any>{

  return this.httpClient.delete
  ("http://localhost:8080/cs/webapi/admin/removeCompany/" + id

  ,{withCredentials:true , responseType: 'text'});

}

public updateCompany(company:Company):Observable<Company>{
  return this.httpClient.put("http://localhost:8080/cs/webapi/admin/updateCompany",
  company,{withCredentials:true});
}

public getCompany(id:number):Observable<Company>{

  return this.httpClient.get<Company>
  ("http://localhost:8080/cs/webapi/admin/getCompany/"  + id

  , { withCredentials: true});

}

public getAllCompany():Observable<any> {
  return this.httpClient.get<Company[]>
  ("http://localhost:8080/cs/webapi/admin/getAllCompanies"
  ,{withCredentials:true});
}

}
