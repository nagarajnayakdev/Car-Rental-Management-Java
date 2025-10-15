package com.car.rental.prms;

public class Main {
	public static void main(String[] args) {
		CarRentalSystem rentalSystem = new CarRentalSystem();
		
		Car car1 = new Car("C001", "Toyota", "Camrey", 1500);
		Car car2 = new Car("C002", "Honda", "Accord", 2000);
		rentalSystem.addCar(car1);
		rentalSystem.addCar(car2);
		
		rentalSystem.menu();
		
	}

}
