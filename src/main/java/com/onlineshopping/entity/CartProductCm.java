package com.onlineshopping.entity;

import java.util.List;

public class CartProductCm {

	private List<CartProduct> listOfProducts;

	public CartProductCm() {
	}

	public CartProductCm(List<CartProduct> listofProducts) {

		this.listOfProducts = listofProducts;
	}

	public List<CartProduct> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<CartProduct> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

}
