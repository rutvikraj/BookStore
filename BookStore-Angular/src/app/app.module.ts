import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';

import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatListModule} from '@angular/material/list';

import { SignupComponent } from './components/signup/signup.component';
import { CartComponent } from './components/cart/cart.component';
import { HeaderComponent } from './components/header/header.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { FooterComponent } from './components/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './auth/auth.guard';
import { SearchComponent } from './components/search/search.component';
import { StarRatingModule } from 'angular-star-rating';
import { MyheaderComponent } from './components/myheader/myheader.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/pages/home/home.component';
import { MyhomeComponent } from './components/myhome/myhome.component';
import { MyLoginComponent } from './components/pages/login/login.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AboutComponent } from './components/pages/about/about.component';


@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    SignupComponent,
    CartComponent,
    HeaderComponent,
    WishlistComponent,
    FooterComponent,
    SearchComponent,
    MyheaderComponent,
    HomeComponent,
    MyhomeComponent,
    MyLoginComponent,
    SidebarComponent,
    AboutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    StarRatingModule.forRoot(),
    BrowserAnimationsModule,
    MatButtonModule,MatToolbarModule,
    MatIconModule,MatCardModule,MatSnackBarModule,
    MatInputModule,MatFormFieldModule,MatListModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
