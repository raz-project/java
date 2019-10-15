import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'myDate'
})
export class MyDatePipe implements PipeTransform {

  transform(date: any, args?: any): any {
   let currentDate = new Date(date);
    return new Date(date.year , date.monthValue , date.dayOfMonth) ;
  }

}
