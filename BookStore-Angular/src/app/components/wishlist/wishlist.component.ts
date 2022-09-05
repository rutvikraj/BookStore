import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { WishlistDataService } from 'src/app/services/wishlist-data.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css'],
})
export class WishlistComponent implements OnInit {
  wishlistdata: any;

  constructor(
    private wishlist: WishlistDataService,
    private route: ActivatedRoute,
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    wishlist.wishlistdata().subscribe((data: any) => {
      console.log(data);

      this.wishlistdata = data;
    });
  }

  deletebtn(data: any) {
    console.log('DELETE ID', data);
    this.http
       .delete('http://localhost:5001/cart/removeProductFromWishlist/' + data
     // , {
      //   withCredentials: true,
      // }
      )

      .subscribe((res: any) => {
        console.log(res);
        this.router.routeReuseStrategy.shouldReuseRoute = function () {
          return false;
        };
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['/wishlist']);
      });
  }

  ngOnInit(): void {}
}
