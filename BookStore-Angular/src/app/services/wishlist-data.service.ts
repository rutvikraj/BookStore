import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class WishlistDataService {
  userid = localStorage.getItem('customerId');
  url = 'http://localhost:5001/cart/getWishlist/';
  constructor(private http: HttpClient) {}
  wishlistdata() {
    return this.http.get(this.url + this.userid);
  }
}
