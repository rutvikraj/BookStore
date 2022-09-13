# BookStore-Microservices
### To genrate jar files of each microservices use 'maven clean' on Spring Tool Suite or on to CMD.

## Authentication Microservice
- This microservice is used to Authenticate the User using JWT.
- ## Methods:
  - POST: /authenticate

## Cart Microservice
- This is middleware Microservice which provides Cart details of the user & accessing the items present in Cart.
- ## Methods:
  - GET: /cart/getCart/{customerId}
  - POST: /cart/addProductToCart
  - POST: /cart/addProductToCartOfSpecificVendor/{vendorId}
  - POST: /cart/addToCustomerWishlist
  - GET: /cart/getWishlist/{customerId}
  - DELETE: /cart/removeProductFromCart/{userId}
  - DELETE: /cart/removeProductFromWishlist/{userId}

## Product Microservice
- This is a middleware microservice which gives user the details about the Book like price, rating, etc. and are they available in stock or not.
- ## Methods:
  - GET: /product/getAllProducts
  - GET: /product/searchProductById/{id}
  - GET: /product/searchProductByName/{name}
  - POST: /product/addProductRating/{id}/{rating}

## Vendor Microservice
- It gives the Vendor Details and if they have stock of a perticular book or not.
- ## Methods:
  - GET: /vendor/getAllVendors/{productId}
  - GET: /vendor/getVendorDetails/{vendorId}
  - GET: /stockdetails/{productId}/{vendorId}
