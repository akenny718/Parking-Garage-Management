package ParkingGarageMgmt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vehicle {
	
    private LocalDateTime parkingTime_Date;
    private LocalDateTime retrievalTime_Date;
    private String licensePlateNum;
    private String paymentScheme;
    private String earlyBirdRate = null;
	private String hourlyRate = null;
    private String parkingLocation;
    private String ownerName;
    private String phone;
    private String vehicleType;
	
	
	Vehicle(){
	}


	public String getLicensePlateNum() {
		return licensePlateNum;
	}


	public void setLicensePlateNum(String licensePlateNum) {
		this.licensePlateNum = licensePlateNum;
	}


	public LocalDateTime getParkingTime_Date() {
		return parkingTime_Date;
	}


	public void setParkingTime_Date(LocalDateTime parkingTime_Date) {
		this.parkingTime_Date = parkingTime_Date;
	}


	public LocalDateTime getRetrievalTime_Date() {
		return retrievalTime_Date;
	}


	public void setRetrievalTime_Date(LocalDateTime retrievalTime_Date) {
		this.retrievalTime_Date = retrievalTime_Date;
	}



	public String getParkingLocation() {
		return parkingLocation;
	}


	public void setParkingLocation(String parkingLocation) {
		this.parkingLocation = parkingLocation;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getVehicleType() {
		return vehicleType;
	}


	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	
	public String getEarlyBirdRate() {
		return earlyBirdRate;
	}


	public void setEarlyBirdRate(String earlyBirdRate) {
		this.earlyBirdRate = earlyBirdRate;
	}


	public String getHourlyRate() {
		return hourlyRate;
	}


	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	
    public void setPaymentScheme(Vehicle vehicle) {
    	
    	int parkingTime_Hour = vehicle.getParkingTime_Date().getHour();
		
		if(parkingTime_Hour > 10 || parkingTime_Hour < 4 ) {
			this.paymentScheme = vehicle.getHourlyRate();
		}
		else
		    this.paymentScheme = vehicle.getEarlyBirdRate();
	}
    
    
    public String getPaymentScheme() {
		return paymentScheme;
	}
    
    
    public String showParkingInfo() {
		return  "Attendant: "+this.getOwnerName()+"\n"+
				"Vehicle Type: "+this.getVehicleType()+"\n"+
				"License: "+this.getLicensePlateNum()+"\n"+
				"Parking Location: "+this.getParkingLocation()+"\n"+
				"Parking Date/Time: "+this.getParkingTime_Date()+"\n"+
				"Payment Rate: $"+this.getPaymentScheme()+"/Hr";
	}

	
	public String showRetrievalInfo() {
		return  "Attendant: "+this.getOwnerName()+"\n"+
				"Vehicle Type: "+this.getVehicleType()+"\n"+
				"License: "+this.getLicensePlateNum()+"\n"+
				"Parking Location: "+this.getParkingLocation()+"\n"+
				"Parking Date/Time: "+this.getParkingTime_Date()+"\n"+
				"Retrieval Date/Time: "+this.getRetrievalTime_Date()+"\n"+
				"Payment Rate: $"+this.getPaymentScheme()+"/Hr";
		
	}


}
