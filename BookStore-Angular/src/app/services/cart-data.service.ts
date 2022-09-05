import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CartDataService {
  userid = localStorage.getItem('customerId');
  url = 'http://localhost:5001/cart/getCart/';
  constructor(private http: HttpClient) {}
  cartdata() {
    return this.http.get(this.url + this.userid);
  }
}
