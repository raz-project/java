import { Router } from '@angular/router';
import { CustomerserviceService } from './../../service/customerservice.service';
import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/model/coupon';
import { MyDatePipe } from 'src/app/pipes/my-date.pipe';
import { text } from '@angular/core/src/render3';

@Component({
  selector: 'app-all-coupons',
  templateUrl: './all-coupons.component.html',
  styleUrls: ['./all-coupons.component.css']
})
export class AllCouponsComponent implements OnInit {
  public coupons : Coupon[];
  public categories: string[];
  public selectedCategory:number;

 public coupon:Coupon;
  public updateCalled : boolean = false;
  public newDate : Date;
  public price:number=0;
  public category:string="";
  public date:string;
  public size:number;
  public myDate = new MyDatePipe();


  constructor(private customerService : CustomerserviceService, private router:Router) { }
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

  public getAllCoupons():void{
    this.customerService.getAllCoupons()
    .subscribe(coupons => this.coupons = coupons,
      error => alert(error.message));

  }

  // price filter
  public getByBellowPrice(price:number){

    this.byBelowPrice(price);
   }
        private byBelowPrice(price:number):void{
          alert(this.price);
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

      // date filter
      public getByDate(newDate:Date){
        const endDate =newDate.toString();
        alert(endDate);
        this.getCouponsBeforeEndDate(endDate);

       }

       private getCouponsBeforeEndDate(date:string):void{
        this.customerService.getCouponsBeforeEndDate(date)
        .subscribe(coupons => this.coupons = coupons,
          error => alert(error.message));
       }


      public purchace(id:number){
        alert(id);
        this.prepareTopurch(id);
        this.router.navigate(['/customer']);



      }

       private prepareTopurch(id:number){
         this.customerService.purchaseCoupon(id)
         .subscribe(
           text=>{alert(text)}
         ),
         err => alert(err.message);


       }

  ngOnInit() {
    this.getAllCoupons();
    this.newDate  = new Date();
    this.categories = this.customerService.getCategories();
  }

}
