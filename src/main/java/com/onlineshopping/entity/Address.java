package com.onlineshopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "required")
	private String city;

	@NotNull(message = "required")
	private String street;

	@NotNull(message = "required")
	private String landmark;

	@NotNull(message = "required")
	private String building;

	@NotNull(message = "required")
	private String floor;

	@NotNull(message = "required")
	@Column(name = "flat_number")
	private int flatNumber;

	public Address() {

	}

	public Address(String city, String street, String landmark, String building, String floor, int flatNumber) {
		this.city = city;
		this.street = street;
		this.landmark = landmark;
		this.building = building;
		this.floor = floor;
		this.flatNumber = flatNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}

}
