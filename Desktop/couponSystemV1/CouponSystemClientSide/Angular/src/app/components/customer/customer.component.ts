import { CustomerserviceService } from './../../service/customerservice.service';
import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/model/coupon';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  public coupons:Coupon[];
  public categories: string[];
  public selectedCategory:number;
  public newDate : Date;
  public price = 0;
  public category = ""

  public coupon:Coupon;
  constructor(private customerService:CustomerserviceService,) { }

     public getAllCoupons():void{
    this.customerService.getAllCustomerCoupons()
    .subscribe(coupons => this.coupons = coupons,
      error => alert(error.message));

  }

  public getCategoryValue(category: string): number{
    switch (category) {
        case "TRAVELING":
            return 1;
            break;
        case "FOOD":
            return 2;
            break;
            case "ELECTTRICITY":
              return 3;
              break;
        case "HEALTH":
            return 4;
            break;
        case "SPORTS":
            return 5;
            break;
        case "CAMPING":
            return 6;
            break;
        case "FASHION":
            return 7;
            break;
        case "STUDIES":
            return 8;
            break;

    }
  }
 // price filter
 public getByBellowPrice(price:number){

  this.byBelowPrice(price);
 }
      private byBelowPrice(price:number):void{
        alert(price);
       this.customerService.getCouponsBelowPrice(price)
       .subscribe(coupons => this.coupons = coupons,
         error => alert(error.message));
      }
      // category filter
public getByCategory(category:string){

this.getCouponsByCategory(category);

}
 private getCouponsByCategory(category:string):void{
  this.customerService.getCouponsByCategory(category)
  .subscribe(coupons =>{ this.coupons = coupons },
    error => alert(error.message));

 }


  ngOnInit() {
    this.getAllCoupons();

    this.categories = this.customerService.getCategories();
    this.newDate  = new Date();
  }


}
