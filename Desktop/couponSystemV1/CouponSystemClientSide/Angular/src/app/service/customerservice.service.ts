import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Coupon } from '../model/coupon';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerserviceService {

 public constructor(private httpClient: HttpClient) { }
 headers = new HttpHeaders({ 'Content-Type': 'application/json' });

 public getAllCustomerCoupons():Observable<Coupon[]> {
  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/customer/getCustomerCoupons"
  ,{withCredentials:true});
}

public getAllCoupons():Observable<Coupon[]> {
  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/customer/getAllCoupons"
  ,{withCredentials:true});
}


public purchaseCoupon(id:number):Observable<any> {
  return this.httpClient.delete
  ("http://localhost:8080/cs/webapi/customer/purchaseCoupon?id="+ id
  ,{withCredentials:true , responseType:"text" });
}

public getCouponsBelowPrice(price:number):Observable<Coupon[]>{

  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/customer/getCouponsBelowPrice?price="  + price

  , {withCredentials:true});

}

public getCouponsByCategory(category:string):Observable<Coupon[]>{

  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/customer/getCouponsByCategory?category=" + category

  , {withCredentials:true});

}

public getCouponsBeforeEndDate(localDate:string):Observable<Coupon[]>{

  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/customer/getCouponsBeforeEndDate?localDate=" + localDate

  , {withCredentials:true});

}

public getCategories() {

  return["TRAVELING", "FOOD", "HEALTH", "SPORTS", "CAMPING", "FASHION", "STUDIES", "ELECTTRICITY"];
}

}
