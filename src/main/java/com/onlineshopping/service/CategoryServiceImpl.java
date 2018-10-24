package com.onlineshopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.CategoryDao;
import com.onlineshopping.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Transactional
	@Override
	public List<Category> getCategories() {

		return categoryDao.getCategories();
	}
}
