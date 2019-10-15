
import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/model/coupon';
import { CompanyserviceService } from 'src/app/service/companyservice.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MyDatePipe } from 'src/app/pipes/my-date.pipe';







@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css'],

})
export class CompanyComponent implements OnInit {


  public coupons:Coupon[];
  public categories: string[];
  public selectedCategory:number;

  public coupon:Coupon;
  public updateCalled : boolean = false;
  public newDate : Date;
  public price:number;
  public category:string;
  public date:string;
  public size:number;
  public myDate = new MyDatePipe();


  public selectedPricefilter = 0;



  constructor(private companyService: CompanyserviceService , private router:Router , private activetedRoute:ActivatedRoute ) {
   }

   public remove(couponId:number):void{
    this.companyService.removeCoupon(couponId)
    .subscribe( ()=>
      alert("coupon remove sucss!!") ,

      error =>
      alert(error.message));
      this.getAllCoupons();
    this.router.navigate(['/company']).then(()=>{
      window.location.reload();
    });

   }

   public update(id:number):void{
    this.updateCalled = true;
    //this.router.navigate(["/update-coupon"]);
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
         alert(this.price);
        this.companyService.getCouponsBelowPrice(price)
        .subscribe(coupons => this.coupons = coupons,
          error => alert(error.message));
       }

// category filter
public getByCategory(category:string){

  this.getCouponsByCategory(category);

}
   private getCouponsByCategory(category:string):void{
    this.companyService.getCouponsByCategory(category)
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
    this.companyService.getCouponsBeforeEndDate(date)
    .subscribe(coupons => this.coupons = coupons,
      error => alert(error.message));
   }





  ngOnInit() {

    this.getAllCoupons();

    this.categories = this.companyService.getCategories();
    this.newDate  = new Date();


}



public getAllCoupons():void{
  this.companyService.getAllCoupon()
  .subscribe(coupons => this.coupons = coupons,
    error => alert(error.message));

}


}






