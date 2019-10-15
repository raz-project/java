import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyDatePipe } from './pipes/my-date.pipe';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [MyDatePipe],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [MyDatePipe]
})
export class SharedModeleModule { }
