package com.onlineshopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.CartDao;
import com.onlineshopping.dao.ProductDao;
import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.CartProductId;
import com.onlineshopping.entity.Product;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartDao cartDao;

	@Transactional
	public void addProduct(Cart cart, int productId) {

		Product product = productDao.getProduct(productId);

		CartProduct cartProduct = cartDao.getCartProduct(cart, product);

		System.out.println(cartProduct);
		if (cartProduct == null) {
			cartProduct = new CartProduct();
			cartProduct.setCartProductId(new CartProductId(cart, product));
		}

		cartProduct.setCart(cart);
		cartProduct.setProduct(product);

		cartProduct.setQuantity(cartProduct.getQuantity() + 1);
		cartDao.addCartProduct(cartProduct);
		cart.setTotalNumberOfProducts(cart.getTotalNumberOfProducts() + 1);
		cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());

		System.out.println(cartProduct);

	}

	@Transactional
	@Override
	public List<CartProduct> getProducts(int cartId) {
		System.out.println("cart id in cart service : " + cartId);
		return cartDao.getProducts(cartId);
	}

	@Transactional
	@Override
	public CartProduct updateCartProduct(CartProduct cp) {

		return cartDao.updateCartProduct(cp);
	}

	@Transactional
	@Override
	public void removeProduct(Cart cart, int productId) {

		Product product = productDao.getProduct(productId);
		CartProductId id = new CartProductId(cart, product);

		cartDao.removeCartProduct(id);
	}
}
