import { Coupon } from 'src/app/model/coupon';
export class RemoteCoupon {

  public constructor(
      public id: number,
      public title?: string,
      public startDate?: string,
      public endDate?: string,
      public amount?: number,
      public category?: number,
      public message?: string,
      public price?: number ,
      public imageURL?: string
  ) {}









}
