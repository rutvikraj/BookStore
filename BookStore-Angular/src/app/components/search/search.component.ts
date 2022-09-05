import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ProductsDataService } from 'src/app/services/products-data.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { catchError, elementAt, of, throwError } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  routeparam: any;
  product: any;
  myproducts :any=[];
  myvendors: any=[];
  products: any;
  productsdesc: any = [];
  errorMs: any = [];
  search: String = '';
  vendorId : any;
  
  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private productData: ProductsDataService,
    private router: Router
  ) {
    productData.products().subscribe((data: any) => {
      console.log('Data in serach comp', data);
      this.products = data;
    });
  }

  errorHandle(error: HttpErrorResponse) {
    console.log('==========', error);
    return throwError(error.error.message || 'Server Error');
  }

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
    console.log("VENDOR ID : "+this.vendorId);
    console.log(this.formData);
    // console.log(this.formData.productId);
    this.http.post('http://localhost:5001/cart/addProductToCartOfSpecificVendor/'+this.vendorId,this.formData)
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

  

  url = 'http://localhost:5000/product/searchProductByName/';

  ngOnInit() {
    // subscribe to router event
    this.route.params.subscribe((params: any) => {
      this.routeparam = params.searchTerm;
        // params.searchTerm.charAt(0).toUpperCase() +
        // params.searchTerm.substr(1).toLowerCase();
    });

    this.http.get(this.url + this.routeparam).subscribe(
      (data: any) => {
      console.log('searchData', data);
      this.myproducts = data;
      data.forEach((element: any) => {
        console.log("in loop "+element.name+"id is "+element.id);
        this.http
        .get('http://localhost:5002/vendor/getAllVendors/' + element.id)
        .pipe(catchError(this.errorHandle))
        .subscribe(
          (result: any) => { this.myvendors = result;
            console.log(result)
            console.log("this is search best vender res = "+result[0].vendorId)},
          (error) => {
            console.log("alert error found")
            console.log('+++', element.id);
            this.errorMs.push(element.id);
          }
          );
          console.log("DEBUG "+element.description);
          let dataToArray = element.description
          .split(',')
          .map((item: string) => item.trim());
          
          this.productsdesc = dataToArray;
          console.log("oo-"+this.productsdesc);
          this.product = element;
        }
        );
    },(error)=>{
      // this.errorMs.push(element.id);
      console.log("hi lucky you got error");
    }
    );

    // console.log('search local', this.product.toLocalString());
    // this.http.get("http://localhost:5002/vendor/getAllVendors/"+).subscribe(
    //   (result:any)=>{}
    //   ,(error:any)=>{}
    // )
  }//nginit

  

  myvalue(id:any,vid:any){
    console.log(id)
    this.vendorId=vid;
    this.formData.productId = id;
  }

  wishform = new FormGroup({});
  
  
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

}
