
import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/model/coupon';
import { CompanyserviceService } from 'src/app/service/companyservice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css']
})
export class AddCouponComponent implements OnInit {


    public coupon = new Coupon;

    public categories:string[];

    public selectedCategory:string;

    private couponId:number;

  public  constructor(private companyService:CompanyserviceService) { }

  public addCoupon():void{

    this.coupon.category = this.getCategoryValue(this.selectedCategory);

      this.companyService.addCoupon(this.coupon).subscribe(coupon=>{
          alert("Coupon Succesfully added! the coupon id is: " + coupon.id);
      },err=>{
          alert("Error: " + err);
      });

      alert(`
      Title: ${this.coupon.title}
      StartDate: ${this.coupon.startDate}
      EndDate: ${this.coupon.endDate}
      Amount: ${this.coupon.amount}
      Category: ${this.coupon.category}
      Message: ${this.coupon.message}
      Price: ${this.coupon.price}
      ImageURL: ${this.coupon.imageURL}
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








  public ngOnInit():void {

    this.categories = this.companyService.getCategories();
    











  }
}

