package com.onlineshopping.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

	Admin() {
	}
}
