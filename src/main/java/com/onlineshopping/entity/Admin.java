package com.onlineshopping.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="ADMIN")
public class Admin extends User{
	
	Admin(){}
}
