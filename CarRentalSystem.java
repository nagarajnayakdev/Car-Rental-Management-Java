package com.car.rental.prms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 public class CarRentalSystem {
	private List<Car> cars;

	private List<Customer> customers;

	private List<Rental> rentals;

	public CarRentalSystem() {
		cars = new ArrayList<Car>();
		customers = new ArrayList<Customer>();
		rentals = new ArrayList<Rental>();
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void rentCar(Car car, Customer customer, int days) {
		if (car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car, customer, days));

		} else {
			System.out.println("Car is not available");
		}
	}

	public void returnCar(Car car) {
		car.returnCar();
		Rental rentalToRemove = null;
		for (Rental rental : rentals) {
			if (rental.getCar() == car) {
				rentalToRemove = rental;
				break;
			}

		}

		if (rentalToRemove != null) {
			rentals.remove(rentalToRemove);
			System.out.println("Car returnd succesfully");
		} else {
			System.out.println("Car was not rented");
		}
	}

	public void menu() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("==== Car Rental System ====");
			System.out.println("1. Rent a Car");
			System.out.println("2. Return a Car");
			System.out.println("3. Exit");
			System.out.println("Enter yoour choice");

			int choice = scanner.nextInt();
			scanner.nextLine(); // to consume a line

			if (choice == 1) {
				System.out.println("\n == Rent a car == \n");
				System.out.println("Enter Your Name: ");
				String customerName = scanner.nextLine();

				System.out.println("\n Avilabel cars: ");
				for (Car car : cars) {
					if (car.isAvailable()) {
						System.out.println(car.getCarId() + " " + car.getBrand() + " " + car.getModel());
					}
				}

				System.out.println("\n Enter the car ID you want to rent");
				String carId = scanner.nextLine();

				System.out.println("Enter the number of days you rental");
				int rentalDays = scanner.nextInt();
				scanner.nextLine();

				Customer newCustomer = new Customer("Cus" + (customers.size() + 1), customerName);
				addCustomer(newCustomer);

				Car selectedCar = null;
				for (Car car : cars) {
					if (car.getCarId().equals(carId) && car.isAvailable()) {
						selectedCar = car;
						break;
					}
				}

				if (selectedCar != null) {
					double totalPrice = selectedCar.calculatePrice(rentalDays);
					System.out.println("\n== Rental Information == \n");
					System.out.println("Customer ID: " + newCustomer.getCustomerId());
					System.out.println("Customer Name: " + newCustomer.getName());
					System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
					System.out.println("Rental Days: " + rentalDays);
					System.out.println("Total Price: " + totalPrice);

					System.out.println("\nConfirm rental (Y/N)");
					String confirm = scanner.nextLine();

					if (confirm.equalsIgnoreCase("Y")) {
						rentCar(selectedCar, newCustomer, rentalDays);
						System.out.println("\nCarrented successfully");
					} else {
						System.out.println("\nRental cancled.");
					}

				} else {
					System.out.println("\nInvalid car selection or car not available for rent");
				}
			} else if (choice == 2) {
				System.out.println("\n == Return a Car ==\n");
				System.out.println("Enter the car ID you want to return: ");
				String carId = scanner.nextLine();

				Car carToReturn = null;
				for (Car car : cars) {
					if (car.getCarId().equals(carId) && !car.isAvailable()) {
						carToReturn = car;
						break;
					}

				}
				if (carToReturn != null) {
					Customer customer = null;
					for (Rental rental : rentals) {
						if (rental.getCar() == carToReturn) {
							customer = rental.getCustomer();
							break;
						}

					}

					if (customer != null) {
						returnCar(carToReturn);
						System.out.println("Car returned succesfully: " + customer.getName());

					} else {
						System.out.println("Car was not rented or rental information is missing.");
					}

				} else {
					System.out.println("Inavlid car ID or car is not rented");
				}
			} else if (choice == 3) {
				break;
			} else {
				System.out.println("Inavlid Choice please enter a valid option");
			}
		}

		System.out.println("\nThank you for using the car");
		scanner.close();
	}
}
