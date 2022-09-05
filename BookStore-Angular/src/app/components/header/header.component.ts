import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CartDataService } from 'src/app/services/cart-data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  cartdata: any;

  public totalItem: number = 0;

  search: String = '';

  constructor(
    private carts: CartDataService,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) {
    carts.cartdata().subscribe((data: any) => {
      this.totalItem = data.length;
      this.cartdata = data;
    });
  }

  ngOnInit(): void {}

  //search function
  getValue(value: string) {
    console.log('Data', value);
    this.search = value;

    this.router.navigate(['/search/' + this.search.search]);
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    // console.log("^^^^^^^^")
    this.router.onSameUrlNavigation = 'reload';
  }

  Logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/home']);
  }
}
