import { RemoteCoupon } from './../model/RemoteCoupon';
import { Coupon } from 'src/app/model/coupon';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class CompanyserviceService {

  headers = new HttpHeaders({ 'Content-Type': 'application/json' });

 public constructor(private httpClient: HttpClient) { }

 public getAllCoupon(): Observable<Coupon[]> {
  return this.httpClient.get<Coupon[]>
  ('http://localhost:8080/cs/webapi/company/getAllCoupons'
  ,{withCredentials:true});
}

public getCoupon(id:number):Observable<Coupon>{
  return this.httpClient.get<Coupon>
  ("http://localhost:8080/cs/webapi/company/getCoupon/" + id

  , {withCredentials:true});

}

public getOneCoupon(id:number):Observable<Coupon>{
  return Observable.create(observer=>{
   this.httpClient.get<Coupon[]>('http://localhost:8080/cs/webapi/company/getAllCoupons')
   .subscribe(coupons =>{
       const coupon = coupons.find(c => c.id === id);
       observer.next(coupon);
   });
});
}

public addCoupon(coupon:Coupon):Observable<any>{
    return this.httpClient.post("http://localhost:8080/cs/webapi/company/createCoupon",
    coupon,{withCredentials:true , responseType: 'text'});
}


public removeCoupon(id:number):Observable<any>{

  return this.httpClient.delete
  ("http://localhost:8080/cs/webapi/company/removeCoupon/" + id

, {withCredentials:true, responseType: 'text'});

}

public updateCoupon(coupon:Coupon):Observable<Coupon>{
  return this.httpClient.post("http://localhost:8080/cs/webapi/company/updateCoupon",
  coupon, { withCredentials : true} );
}

public updateRemoteCoupon(remoteCoupon:RemoteCoupon):Observable<any>{
  return this.httpClient.put("http://localhost:8080/cs/webapi/company/updateCoupon",
  remoteCoupon, { withCredentials : true , responseType:'text'});
}

public getCouponsBelowPrice(price:number):Observable<Coupon[]>{

  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/company/getCouponsBelowPrice?price="  + price

  , {withCredentials:true});

}

public getCouponsByCategory(category:string):Observable<Coupon[]>{

  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/company/getCouponsByCategory?category=" + category

  , {withCredentials:true});

}

public getCouponsBeforeEndDate(localDate:string):Observable<Coupon[]>{

  return this.httpClient.get<Coupon[]>
  ("http://localhost:8080/cs/webapi/company/getCouponsBeforeEndDate?localDate=" + localDate

  , {withCredentials:true});

}



public getCategories() {

  return["TRAVELING", "FOOD", "HEALTH", "SPORTS", "CAMPING", "FASHION", "STUDIES", "ELECTTRICITY"];
}







}

