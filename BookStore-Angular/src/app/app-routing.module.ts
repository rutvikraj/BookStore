import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { CartComponent } from './components/cart/cart.component';
// import { LoginComponent } from './components/login/login.component';
import { ProductsComponent } from './components/products/products.component';
import { SignupComponent } from './components/signup/signup.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { SearchComponent } from './components/search/search.component';
import { MyLoginComponent } from './components/pages/login/login.component';
import { FooterComponent } from './components/footer/footer.component';
import { MyhomeComponent } from './components/myhome/myhome.component';
import { AboutComponent } from './components/pages/about/about.component';

const routes: Routes = [
  // {path: '', component:HomeComponent},
  {path: 'about', component:AboutComponent},
  {path: 'home', component:MyhomeComponent},
  
 {path: 'mylogin', component:MyLoginComponent},
  
  // { path: 'login', component: LoginComponent },
  {
    path: 'products',
    component: ProductsComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'wishlist',
    component: WishlistComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'search/:searchTerm',
    component: SearchComponent,
    canActivate: [AuthGuard],
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

export const routingComponents = [
  // LoginComponent,
  ProductsComponent,
  SignupComponent,
  CartComponent,
  WishlistComponent,
];
