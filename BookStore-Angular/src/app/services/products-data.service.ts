import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductsDataService {
  token = localStorage.getItem('token');

  header = new HttpHeaders({ Authorization: 'Bearer ' + this.token });
  requestOptions = { headers: this.header };
  
  // options = new RequestOptions({ headers: headers, withCredentials: true });

  url = 'http://localhost:5000/product/getAllProducts';

  constructor(private http: HttpClient) {}

  products() {
    return this.http.get(this.url, this.requestOptions) ;
    
  }
}
