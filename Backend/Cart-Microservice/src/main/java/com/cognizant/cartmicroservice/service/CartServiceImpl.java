package com.cognizant.cartmicroservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.cartmicroservice.Repository.CartRepository;
import com.cognizant.cartmicroservice.Repository.CustomerWishlistRepository;
import com.cognizant.cartmicroservice.dao.CartDAO;
import com.cognizant.cartmicroservice.dto.CartDTO;
import com.cognizant.cartmicroservice.dto.CartRequestDTO;
import com.cognizant.cartmicroservice.dto.CartResponseDTO;
import com.cognizant.cartmicroservice.entity.Cart;
import com.cognizant.cartmicroservice.entity.Product;
import com.cognizant.cartmicroservice.entity.Vendor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
	
	@Autowired 
	private CartDAO cartDAOImpl;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@Autowired
	private VendorService vendorServiceImpl;
	
	@Autowired
	private CartRepository carRepo;
	
	@Autowired
	private CustomerWishlistRepository wishListRepo;
	
	@Override
	public Cart exists(Integer customerId, Integer productId) {
		log.info("To check the Cart is available from exits service method");
		return cartDAOImpl.existsDao(customerId,productId);
	}
	
	//vk
	@Override
	public Cart exists(Integer customerId, Integer productId, Integer vendorId) {
		log.info("To check the Cart is available from exits service method");
		return cartDAOImpl.existsDao(customerId,productId, vendorId);
	}
	
	
	@Override
	public void saveCart(Cart cart) {
		log.info("To save Cart from saveCart service method");
		cartDAOImpl.saveCartDao(cart);
	}
	
	@Override
	public String addProductToCart(CartRequestDTO cartRequestDTO) {
		
		Integer productId = cartRequestDTO.getProductId();
		Integer quantity = cartRequestDTO.getQuantity();

		Vendor vendoreDetails = vendorServiceImpl.getVendorDetails(productId,quantity);
		if (isVendorEmpty(vendoreDetails)) {
			log.info("Vendore Detail is Empty");
			return "Not Enough Stock";
		}
		Cart cartTemp = exists(cartRequestDTO.getCustomerId(), cartRequestDTO.getProductId());
		if (cartTemp != null) {
			cartTemp.setQuantity(cartTemp.getQuantity() + cartRequestDTO.getQuantity());
			saveCart(cartTemp);
			log.info("successfully added in the cart");
			ModelMapper mapper = new ModelMapper();
			CartDTO cartDTO = new CartDTO();
			mapper.map(cartTemp, cartDTO);
			return "Item Added Successfully to Cart";
		}

		LocalDate date = LocalDate.now().plusDays(5);
		Cart cart = new Cart(cartRequestDTO.getProductId(), cartRequestDTO.getZipcode(), date,
				vendoreDetails.getVendorId(), cartRequestDTO.getCustomerId(), cartRequestDTO.getQuantity());
		saveCart(cart);
		return "Item Added Successfully to Cart";
	}

	//add by vendor id implementation
	public String addProductToCartOfSpecificVendor(CartRequestDTO cartRequestDTO, Integer vendorId) {
		Integer productId = cartRequestDTO.getProductId();
		Integer quantity = cartRequestDTO.getQuantity();
		Integer customerId = cartRequestDTO.getCustomerId();
		
		System.out.println("vendor id is -> "+vendorId);
		System.out.println("Producd id is -> "+productId);
		System.out.println("quautity id is -> "+quantity);
		System.out.println("Cust id is -> "+customerId);
		Vendor vendorDetails = vendorServiceImpl.getVendorById(productId,vendorId,quantity);
		if(isVendorEmpty(vendorDetails)) {
			log.info("Vendor Detail is Empty");
			return "Not Enough Stock";
		}
		
		Cart cartTemp = exists(customerId,productId,vendorId);
		if(cartTemp != null) {
			cartTemp.setQuantity(cartTemp.getQuantity()+quantity);
			saveCart(cartTemp);
			log.info("Successfully added in the cart");
			ModelMapper mapper = new ModelMapper();
			CartDTO cartDTO = new CartDTO();
			mapper.map(cartTemp, cartDTO);
			return "Item Added Successfully to Cart";
		}
		
		LocalDate date = LocalDate.now().plusDays(5);
		Cart cart= new Cart(cartRequestDTO.getProductId(), cartRequestDTO.getZipcode(), date, vendorDetails.getVendorId(),
				cartRequestDTO.getCustomerId(), cartRequestDTO.getQuantity());
		saveCart(cart);
		return "Item Added Successfully to Cart";
	}
	
	
	@Override
	public List<CartResponseDTO> getCartList(Integer customerId) {
		log.info("Get CartList From getCartList service method");
		List<CartResponseDTO> cartDTOList = new ArrayList<>();
		List<Cart> cartList = cartDAOImpl.getCartListDao(customerId);
		for (Cart cart : cartList) {
			Product product = productServiceImpl.getProductbyId(cart.getProductId());
			Vendor vendor = vendorServiceImpl.getVendorById(cart.getVendorId());
			cartDTOList.add(new CartResponseDTO(cart.getCartId(), cart.getZipcode(), cart.getDeliveryDate(),
					cart.getCustomerId(), cart.getQuantity(), product, vendor));
			log.info(cart.getZipcode());
		}
		return cartDTOList;
	}
	
	public boolean isVendorEmpty(Vendor vendoreDetails) {
		boolean isEmpty = false;
		if ((vendoreDetails.getVendorId() == 0 && vendoreDetails.getDeliveryCharge() == 0
				&& vendoreDetails.getRating() == 0))
			isEmpty = true;
		return isEmpty;
	}

	//delete product from cart
	public void deleteCartProduct(int userId) {
		Product product = productServiceImpl.getProductbyId(userId);
		if(product != null) {
			carRepo.deleteById(userId);
			System.out.println("Delete success");
		}
		else {
			System.out.println("No such product with id "+userId);
		}
	}
	
	//delete product from wishlist
	public void deleteWishlistProduct(int userId) {
		Product product = productServiceImpl.getProductbyId(userId);
		if(product != null) {
			wishListRepo.deleteById(userId);
			System.out.println("Delete wishlist success");
		}
		else {
			System.out.println("No such product with id "+userId);
		}
	}
}
