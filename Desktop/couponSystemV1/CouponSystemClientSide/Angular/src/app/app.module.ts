import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, } from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { LayoutComponent } from './components/layout/layout.component';
import { HeaderComponent } from './components/header/header.component';
import { MenuComponent } from './components/menu/menu.component';
import { ContentComponent } from './components/content/content.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { CuopondetailComponent } from './components/cuopondetail/cuopondetail.component';
import { RegisterComponent } from './components/register/register.component';
import { MyDatePipe } from './pipes/my-date.pipe';
import { from } from 'rxjs';








@NgModule({
  declarations: [

    LayoutComponent,
    HeaderComponent,
    MenuComponent,
    ContentComponent,
    FooterComponent,
    LoginComponent,
    ThumbnailComponent,
    RegisterComponent,
    CuopondetailComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule, FormsModule, HttpClientModule, ReactiveFormsModule,
  ],
  providers: [MyDatePipe],
  bootstrap: [LayoutComponent]
})
export class AppModule { }
