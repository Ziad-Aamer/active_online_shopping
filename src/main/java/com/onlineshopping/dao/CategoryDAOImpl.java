package com.onlineshopping.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshopping.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> getCategories() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select categoryName from Category");
		List<Category> categories = query.list();
		System.out.println("categories from Category DAO : " + categories);
		return categories;
	}

}
