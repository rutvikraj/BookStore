package com.cognizant.productmicroservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.productmicroservice.dao.ProductDAO;
import com.cognizant.productmicroservice.dto.ProductDTO;
import com.cognizant.productmicroservice.entity.Product;
import com.cognizant.productmicroservice.exception.ProductNotFoundException;
import com.cognizant.productmicroservice.exception.RatingGreaterThan5Exception;
import com.cognizant.productmicroservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAOImpl;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDTO searchProductById(Integer id) throws ProductNotFoundException {

		log.info("Inside searchProductById Service Method");
		Product entity = productDAOImpl.searchProductByIdDao(id);
		if(entity!=null) {
		ProductDTO dto = new ProductDTO();
		ModelMapper mapper = new ModelMapper();
		mapper.map(entity, dto);
		log.info("Exiting searchProductById Service Method");
		return dto;
		}
		else {
			log.info("product id not found");
			throw new ProductNotFoundException("Product with id [" + id + "] not found");
		}

	}

	@Override
	public List<ProductDTO> searchProductByName(String name) throws ProductNotFoundException{

		log.info("Inside searchProductByName Service Method");
		List<Product> entity = productDAOImpl.searchProductByNameDao(name);
		if(entity.size()>0) {
//		ProductDTO dto = new ProductDTO();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<ProductDTO>>(){}.getType();
		List<ProductDTO> dto = mapper.map(entity, listType);
//		mapper.map(entity, dto);
		log.info("Exiting searchProductByName Service Method");
		return dto;
		}
		else {
			log.info("product name not found");
			throw new ProductNotFoundException("Product with name [" + name + "] not found");
		}
	}

	@Override
	public void addProductRating(Integer id, int rating) throws ProductNotFoundException, RatingGreaterThan5Exception{
		log.info("Inside addProductRating Service Method");
		Product product = productRepository.findById(id).orElse(null);

		if (rating >= 1 && rating <= 5) {

			if (product != null) {
				if (product.getRating() != 0) {
					product.setRating((product.getRating() * product.getNoOfUsersRated() + rating)
							/ (product.getNoOfUsersRated() + 1));
					product.setNoOfUsersRated(product.getNoOfUsersRated() + 1);

				} else {
					log.info("Rating is adding for the product");
					product.setRating(rating);
					product.setNoOfUsersRated(product.getNoOfUsersRated() + 1);
				}
				
			} else {
				log.info("prouduct id not found");
				throw new ProductNotFoundException("Product with id [" + id + "] not found");
			}
		} else if (rating > 5 || rating < 0) {
			log.info("Rating is not in between 1 to 5");
			throw new RatingGreaterThan5Exception("Rating should be between 1 to 5");
		}
		productDAOImpl.addProductRatingDao(product);
		log.info("Exiting addProductRating Service Method");

	}

	@Override
	public List<ProductDTO> getAllProducts() {
		log.info("Inside getAllProducts Service Method");
		List<Product> entity = productDAOImpl.getAllProductsDao();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<ProductDTO>>(){}.getType();
		List<ProductDTO> dto = mapper.map(entity,listType);
		log.info("Exiting getAllProducts Service Method");
		return dto;
	}
}
