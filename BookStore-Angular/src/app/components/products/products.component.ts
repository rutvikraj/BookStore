import { Component, OnInit, Provider } from '@angular/core';
import { ProductsDataService } from 'src/app/services/products-data.service';
import { FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, of, throwError } from 'rxjs';
import { forkJoin } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
})
export class ProductsComponent implements OnInit {
  products: any;
  errorMs: any = [];      //for out of stock
  public vendorList: any;

  constructor(
    private productData: ProductsDataService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    productData.products().subscribe((data: any) => {
      console.log('products', {data});
      this.products = data;
        //product 8
      for (let i = 0; i < data.length; i++) {
        this.http
          .get(
            'http://localhost:5002/vendor/getStock/' + data[i].id + '/1'
          )
        //   { "vendorId": 1, "vendorName": "Amazon", "deliveryCharge": 30.5, "rating": 5.0 }
          // .pipe(catchError(this.errorHandle))
          .subscribe(
            (result: any) => {console.log("run",{result});
            },
            (error) => {
              console.log('+++', data[i].id);
              this.errorMs.push(data[i].id);      //out of stock
            }
          );
      }
      // console.log("asus");
      console.log(this.errorMs)
    });
  }

  errorHandle(error: HttpErrorResponse) {
    console.log('==========', error);
    return throwError(error.error.message || 'Server Error');
  }

  // form = new FormGroup({});

  wishform = new FormGroup({});

  ngOnInit(): void {}

  formData = {
    productId : '',
    customerId : localStorage.getItem('customerId'),
    zipcode : '',
    quantity: 1
  }

  wishformData = {
    productId: '',
      customerId: localStorage.getItem('customerId'),
      quantity: 1
  }

  mystock:any;
  stockdetails:any;

  formSubmit(){
    console.log("submitted");
    console.log(this.formData);
    // console.log(this.formData.productId);
    this.http.post('http://localhost:5001/cart/addProductToCart',this.formData)
    .subscribe(
      
      (data:any)=>{
        console.log("success");
        console.log(data);
        // this.router.navigate(['/cart']);
        let n = data.message.length;
        if(n>35){
          // console.log("error ->"+ data.message)
      
          this.mystock=data.message.split('|');
          // console.log(this.mystock)
          console.log("------------")
        
          Swal.fire({  
            title: 'Product is OUT OF STOCK!!?',  
            text: 'Please enter quantity of the product below limit of '+this.mystock[1]+' else add to wishlist',  
            icon: 'warning',  
            showCancelButton: true,  
            confirmButtonText: 'Add to wishlist',  
            cancelButtonText: 'Cancel'  
          }).then((result) => {  
            if (result.value) {  
              this.wishformData.productId = this.formData.productId;
              this.wishformData.customerId = this.formData.customerId;
              this.wishformData.quantity = this.formData.quantity;
              this.http.post(
                  'http://localhost:5001/cart/addToCustomerWishlist',
                  this.wishformData
                )
                .subscribe((res: any) => {
                  this.router.navigate(['/wishlist']);
                  // alert(res.message);
                  Swal.fire('Successfully done !!',res.message ,'success');
                });
                        
            } else if (result.dismiss === Swal.DismissReason.cancel) {  
              
            }  
          })  



        }
        else{
          this.router.navigate(['/cart']);
          console.log("succes");
          Swal.fire('Successfully done !!', data.message ,'success');
        }
      },

      (error)=>{
        console.log(error);
        
      }
    )
  }



  wishData(data: any) {
    this.wishform = this.formBuilder.group({
      productId: data.productId,
      customerId: localStorage.getItem('customerId'),
      quantity: data.quantity,
    });
    console.log(this.wishform);
    this.http
      .post(
        'http://localhost:5001/cart/addToCustomerWishlist',
        this.wishform.getRawValue()
        // {
        //   withCredentials: true,
        // }
      )
      .subscribe((res: any) => {
        this.router.navigate(['/wishlist']);
        // alert(res.message);
        Swal.fire('Successfully done !!',res.message ,'success');
      });
  }

  isFound(id:any){
    // console.log("fun run");
    for (let i = 0; i < this.errorMs.length; i++) {
      if(this.errorMs[i]===id)
        return true; 
    }
    return false;
}

myValue(id:any){
  console.log("product id : "+id)
  this.formData.productId = id;
}

}
