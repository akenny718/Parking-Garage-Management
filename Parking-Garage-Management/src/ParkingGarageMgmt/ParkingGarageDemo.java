package ParkingGarageMgmt;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

public class ParkingGarageDemo {

	public static void main(String[] args) {

		Scanner entry = new Scanner(System.in);
		ParkingGarageManager pgm = new ParkingGarageManager();
		HashMap<String, Vehicle> parkingLot = new HashMap<String, Vehicle>();
		HashMap<String, UserAccount> userAcctBag = new HashMap<String, UserAccount>();
		DecimalFormat df = new DecimalFormat("#0.00");
		String parkingSpaceNum = null;
		int numOfCars = 0;
		int numOfTrucks = 0;
		int numOfMotorCycles = 0;

		while(true) {

			System.out.println("Welcome, please enter user name: ");
			String userName = entry.next();

			System.out.println("Please enter password: ");
			String passWord = entry.next();
			
			//Log In / Create Account

			System.out.println("Please enter vehicle type: ");
			String vehicleType = entry.next();

			System.out.println("Please enter licesne plate number: ");
			String licensePlateNum = entry.next();

			System.out.println("Park or Retrieve: ");
			String action = entry.next();


			if(vehicleType.compareTo("C") == 0) {

				if(action.compareTo("R") == 0) {

					LocalDateTime retrievalTime_Date = LocalDateTime.now();
					parkingLot.get(licensePlateNum).setRetrievalTime_Date(retrievalTime_Date);

					String exitedSpaceNum = parkingLot.get(licensePlateNum).getParkingLocation();
					pgm.insertVacantSpace(vehicleType, exitedSpaceNum);

					double totalDue = pgm.calculateTotal(parkingLot.get(licensePlateNum));

					System.out.println(parkingLot.get(licensePlateNum).showRetrievalInfo());
					parkingLot.remove(licensePlateNum);
					numOfCars--;
					System.out.println("Total Amount Due: $"+df.format(totalDue));
					System.out.println("Vehicle Retrieved");
					System.out.println(parkingLot);

				}
				else


					if(numOfCars == 10) {
						System.out.println("We're sorry, car lot is full");
					}
					else {

						Vehicle car = new Vehicle();
						car.setOwnerName("James");
						car.setLicensePlateNum(licensePlateNum);
						car.setVehicleType("Car");
						car.setHourlyRate("2.50");
						car.setEarlyBirdRate("20.00");
						LocalDateTime parkingTime_Date = LocalDateTime.now();
						car.setParkingTime_Date(parkingTime_Date);
						car.setPaymentScheme(car);
						parkingSpaceNum = pgm.assignParkingSpaceNum("C"); // Argument input for method is subject to change for GUI;
						car.setParkingLocation(parkingSpaceNum);
						parkingLot.put(car.getLicensePlateNum(), car);
						numOfCars++;
						System.out.println(car.showParkingInfo());
						System.out.println("Car Parked");
						System.out.println(parkingLot);
					}

			}



			if(vehicleType.compareTo("T") == 0) {

				if(action.compareTo("R") == 0) {

					LocalDateTime retrievalTime_Date = LocalDateTime.now();
					parkingLot.get(licensePlateNum).setRetrievalTime_Date(retrievalTime_Date);

					String exitedSpaceNum = parkingLot.get(licensePlateNum).getParkingLocation();
					pgm.insertVacantSpace(vehicleType, exitedSpaceNum);

					double totalDue = pgm.calculateTotal(parkingLot.get(licensePlateNum));

					System.out.println(parkingLot.get(licensePlateNum).showRetrievalInfo());
					parkingLot.remove(licensePlateNum);
					numOfTrucks--;
					System.out.println("Total Amount Due: $"+df.format(totalDue));
					System.out.println("Vehicle Retrieved");
					System.out.println(parkingLot);

				}
				else


					if(numOfTrucks == 10) {
						System.out.println("We're sorry, truck lot is full");
					}
					else {

						Vehicle truck = new Vehicle();
						truck.setOwnerName("James");
						truck.setLicensePlateNum(licensePlateNum);
						truck.setVehicleType("Truck");
						truck.setHourlyRate("5.00");
						truck.setEarlyBirdRate("40.00");
						LocalDateTime parkingTime_Date = LocalDateTime.now();
						truck.setParkingTime_Date(parkingTime_Date);
					    truck.setPaymentScheme(truck);
						parkingSpaceNum = pgm.assignParkingSpaceNum("T"); // Argument input for method is subject to change for GUI;
						truck.setParkingLocation(parkingSpaceNum);
						parkingLot.put(truck.getLicensePlateNum(), truck);
						numOfTrucks++;
						System.out.println(truck.showParkingInfo());
						System.out.println("Vehicle Parked");
						System.out.println(parkingLot);
					}

			}


			if(vehicleType.compareTo("M") == 0) {

				if(action.compareTo("R") == 0) {

					LocalDateTime retrievalTime_Date = LocalDateTime.now();
					parkingLot.get(licensePlateNum).setRetrievalTime_Date(retrievalTime_Date);

					String exitedSpaceNum = parkingLot.get(licensePlateNum).getParkingLocation();
					pgm.insertVacantSpace(vehicleType, exitedSpaceNum);

					double totalDue = pgm.calculateTotal(parkingLot.get(licensePlateNum));

					System.out.println(parkingLot.get(licensePlateNum).showRetrievalInfo());
					parkingLot.remove(licensePlateNum);
					numOfMotorCycles--;
					System.out.println("Total Amount Due: $"+df.format(totalDue));
					System.out.println("Vehicle Retrieved");
					System.out.println(parkingLot);

				}
				else


					if(numOfMotorCycles == 10) {
						System.out.println("We're sorry, motorcycle lot is full");
					}
					else {

						Vehicle motorCycle = new Vehicle();
						motorCycle.setOwnerName("James");
						motorCycle.setLicensePlateNum(licensePlateNum);
						motorCycle.setVehicleType("Motorcycle");
						motorCycle.setHourlyRate("1.00");
						motorCycle.setEarlyBirdRate("10.00");
						LocalDateTime parkingTime_Date = LocalDateTime.now();
						motorCycle.setParkingTime_Date(parkingTime_Date);
						motorCycle.setPaymentScheme(motorCycle);
						parkingSpaceNum = pgm.assignParkingSpaceNum("M"); // Argument input for method is subject to change for GUI;
						motorCycle.setParkingLocation(parkingSpaceNum);
						parkingLot.put(motorCycle.getLicensePlateNum(), motorCycle);
						numOfCars++;
						System.out.println(motorCycle.showParkingInfo());
						System.out.println("Vehicle Parked");
						System.out.println(parkingLot);
					}

			}

		}




	}

}
