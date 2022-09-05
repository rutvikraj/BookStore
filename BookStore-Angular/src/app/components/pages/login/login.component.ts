import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class MyLoginComponent implements OnInit {
  loginData = {
    username : '',
    password : ''
  };
  constructor(
    private snack:MatSnackBar,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  formSubmit(){
    console.log("form submitted");

    if(
      this.loginData.username.trim()=='' ||
      this.loginData.username == null
    ){
      this.snack.open("Username is required !!",'',{duration:3000});
      return;
    } 
    if(
      this.loginData.password.trim()=='' ||
      this.loginData.password == null
    ){
      this.snack.open("Password is required !!",'',{duration:3000});
      return;
    }

    //request to server to generate Token
    this.http.post('http://localhost:9999/authenticate',this.loginData)
    .subscribe(
      (data:any)=>{
        console.log("Success");
        localStorage.setItem('token', JSON.stringify(data.jwttoken));
        localStorage.setItem('customerId', JSON.stringify(data.customerId));
        this.router.navigate(['/products']);
      },
      (error)=>{
        console.log("Error!");
        console.log(error);
        this.snack.open("Invalid Details !! Try again","",{duration:3000})
      }
    )

  }

}
