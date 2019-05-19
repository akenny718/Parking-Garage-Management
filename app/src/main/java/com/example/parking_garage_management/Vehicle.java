package com.example.parking_garage_management;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;

public class Vehicle {

    private String parkingTime_Date;
    private LocalDateTime retrievalTime_Date;
    private int parkingTime_Date_Hour;
    private String licensePlateNum;
    private String paymentScheme;
    private String earlyBirdRate = null;
    private String hourlyRate = null;
    private String parkingLocation;
    private String ownerName;
    private String vehicleType;


    Vehicle(){
    }


    public void setParkingTime_Date_Hour(int parkingTime_Date_Hour){
        this.parkingTime_Date_Hour = parkingTime_Date_Hour;
    }


    public int getParkingTime_Date_Hour(){
        return parkingTime_Date_Hour;
    }


    public String getLicensePlateNum() {
        return licensePlateNum;
    }


    public void setLicensePlateNum(String licensePlateNum) {
        this.licensePlateNum = licensePlateNum;
    }


    public String getParkingTime_Date() {
        return parkingTime_Date;
    }



    public void setParkingTime_Date(String parkingTime_Date) {
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setPaymentScheme(Vehicle vehicle) {

        int parkingTime_Hour = vehicle.parkingTime_Date_Hour;

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
