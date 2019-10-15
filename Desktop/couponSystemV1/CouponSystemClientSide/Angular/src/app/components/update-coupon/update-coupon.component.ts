

import { Component, OnInit } from '@angular/core';
import { CompanyserviceService } from 'src/app/service/companyservice.service';
import { Coupon } from 'src/app/model/coupon';
import { ActivatedRoute } from '@angular/router';
import { RemoteCoupon } from 'src/app/model/RemoteCoupon';
import { MyDatePipe } from 'src/app/pipes/my-date.pipe';







@Component({
  selector: 'app-update-coupon',
  templateUrl: './update-coupon.component.html',
  styleUrls: ['./update-coupon.component.css']
})
export class UpdateCouponComponent implements OnInit {


    public coupon: Coupon;

    public categories: string[];

    public selectedCategory: string;

    private couponId: number;


     public remoteCoupon :  RemoteCoupon;

public currentDate:MyDatePipe;


  public newStartDate = Date();
  public newEndDate = Date();





public  constructor(private companyService: CompanyserviceService, private activatedRoute: ActivatedRoute) { }

public updateCoupon():void{

  this.remoteCoupon.category = this.getCategoryValue(this.selectedCategory);
  this.remoteCoupon.startDate = this.newStartDate.toLocaleString().toString();
  this.remoteCoupon.endDate = this.newEndDate.toLocaleString().toString();
  this.remoteCoupon.title = this.coupon.title;
  this.remoteCoupon.id = this.coupon.id;



    this.companyService.updateRemoteCoupon(this.remoteCoupon).subscribe(coupon=> {
        alert('Coupon Succesfully update! the coupon id ');
    },err=>{

        alert("Error: " + err);
    });



    alert(`
    id:   ${this.remoteCoupon.id}
    Title: ${this.remoteCoupon.title}
    StartDate: ${this.remoteCoupon.startDate}
    EndDate: ${this.remoteCoupon.endDate}
    Amount: ${this.remoteCoupon.amount}
    Category: ${this.remoteCoupon.category}
    Message: ${this.remoteCoupon.message}
    Price: ${this.remoteCoupon.price}
    ImageURL: ${this.remoteCoupon.imageURL}
    `);


}

public getCategoryValue(category: string): number{
  switch (category) {
      case "TRAVELLING":
          return 1;
          break;
      case "FOOD":
          return 2;
          break;
          case "ELECTRICITY":
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
public chek():void{



}

private prepareCoupon(coupon:Coupon, remoteCoupon:RemoteCoupon){


  // PROBLEM

}

public ngOnInit():void {
 this.categories = this.companyService.getCategories();

const id = +this.activatedRoute.snapshot.params.id;
alert(id);
this.remoteCoupon = new RemoteCoupon(id,"","","",1,1,"",1,"");
this.companyService.getCoupon(id)
.subscribe(coupon => {this.coupon = coupon}),
error=>alert("kek");






}




}

//this.remoteCoupon = RemoteCoupon();


// this.newDateStart = Date.parse( this.coupon.startDate.toString())
// this.year = this.coupon.startDate.year;
// this.newDateStart = new Date();
// this.year =  this.coupon.startDate.year;
// this.day = this.coupon.startDate.dayOfMonth;
//  this.mothValue = this.coupon.startDate.monthValue.toString();

//  this.coupon.startDate = this.myDate.transform(this.coupon.startDate,'yyyy-MM-dd');
  // console.log(this.coupon.startDate);
