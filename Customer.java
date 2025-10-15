package com.car.rental.prms;

  public class Customer {

	private String customerId;

	private String name;

	public Customer(String customerId, String name) {
		super();
		this.customerId = customerId;
		this.name = name;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

}
