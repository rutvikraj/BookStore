import { Component, OnInit } from '@angular/core';
import { ClickEvent, RatingChangeEvent } from 'angular-star-rating';
import { CartDataService } from 'src/app/services/cart-data.service';
import { FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartdata: any;
  public my = 10;
  public totalItem: number = 0;

  public grandTotal!: number;

  public subTotal!: number;

  search: String = '';

  getTotalPrice(): number {
    let grandTotal = 0;
    this.cartdata.map((a: any) => {
      grandTotal += a.product.price * a.quantity;
    });
    return grandTotal;
  }

  getSubPrice(): number {
    let subTotal = 0;
    this.cartdata.map((a: any) => {
      subTotal += a.vendor.deliveryCharge;
    });
    return subTotal;
  }

  constructor(
    private carts: CartDataService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    carts.cartdata().subscribe((data: any) => {
      console.log(data);
      this.totalItem = data.length;
      this.cartdata = data;
      this.grandTotal = this.getTotalPrice();
      this.subTotal = this.getSubPrice();
    });
  }

  onClickResult: any;

  itemstarid: any;



  clickStar(data: any) {
    this.itemstarid = data;
    console.log("clicked");
    
    console.log(this.itemstarid);
  }

  onClick = ($event: any,id:any) => {
    console.log(this.my+" this wxy");
    this.itemstarid = id;
    console.log('onClick $event: ', $event);
    this.onClickResult = $event;

    this.http
      .post(
        'http://localhost:5000/product/addProductRating/' +
          this.itemstarid +
          '/' +
          this.onClickResult.rating
          ,
        {
          withCredentials: true,
        }
      )
      .subscribe((res: any) => {
        console.log(res);
      },
      (error)=>{
        // this.router.onSameUrlNavigation = 'reload';
        console.log("i am error");
        // location.reload();
        this.router.navigate(['/cart'])
          
      }
      );
  };

  deletebtn(data: any) {
    console.log('DELETE ID', data);
    this.http
      .delete('http://localhost:5001/cart/removeProductFromCart/' + data
      // , 
      // {
      //   withCredentials: true,
      // }
      )

      .subscribe((res: any) => {
        console.log(res);
        this.router.routeReuseStrategy.shouldReuseRoute = function () {
          return false;
        };
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['/cart']);
      });
  }

  ngOnInit(): void {}

  myfun(){
    this.my += 1;
  }

}
