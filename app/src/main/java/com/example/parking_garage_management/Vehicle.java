package com.example.parking_garage_management;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;

/**
 * Vehicle class is used to create vehicles and assign information to them
 * associated with the vehicle and its activities
 *
 * @author Arthur K. Edouard
 */

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


    /**
     * Creates and initializes a vehicle
     */
    Vehicle(){
    }

    /**
     * Sets the hour of when the vehicle was parked
     *
     * @param parkingTime_Date_Hour hour which vehicle was parked
     */

    public void setParkingTime_Date_Hour(int parkingTime_Date_Hour){
        this.parkingTime_Date_Hour = parkingTime_Date_Hour;
    }


    /**
     * Returns the hour of when the vehicle was parked
     *
     * @return
     */

    public int getParkingTime_Date_Hour(){
        return parkingTime_Date_Hour;
    }


    /**
     * Returns license plate number
     *
     * @return
     */

    public String getLicensePlateNum() {
        return licensePlateNum;
    }


    /**
     * Sets license plate number
     *
     * @param licensePlateNum license plate number of vehicle
     */

    public void setLicensePlateNum(String licensePlateNum) {
        this.licensePlateNum = licensePlateNum;
    }


    /**
     * Returns both date and time of parking
     *
     * @return
     */

    public String getParkingTime_Date() {
        return parkingTime_Date;
    }


    /**
     * Sets the time and date of parking
     *
     * @param parkingTime_Date time and date of parking
     */

    public void setParkingTime_Date(String parkingTime_Date) {
        this.parkingTime_Date = parkingTime_Date;
    }


    /**
     * Returns date and time of retrieval
     *
     * @return
     */

    public LocalDateTime getRetrievalTime_Date() {
        return retrievalTime_Date;
    }


    /**
     * Sets date and time of retrieval
     *
     * @param retrievalTime_Date
     */

    public void setRetrievalTime_Date(LocalDateTime retrievalTime_Date) {
        this.retrievalTime_Date = retrievalTime_Date;
    }


    /**
     * Returns parking location of vehicle
     *
     * @return
     */

    public String getParkingLocation() {
        return parkingLocation;
    }


    /**
     * Sets parking location of vehicle
     *
     * @param parkingLocation parking loaction of vehicle
     */

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }


    /**
     * Returns name of owner
     *
     * @return
     */

    public String getOwnerName() {
        return ownerName;
    }


    /**
     * Sets name of owner
     *
     * @param ownerName
     */

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    /**
     * Returns the type of vehicle
     *
     * @return
     */

    public String getVehicleType() {
        return vehicleType;
    }


    /**
     * Sets the type of vehicle
     *
     * @param vehicleType type of vehicle
     */

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }


    /**
     * Returns the rate charged for "early bird" parking
     *
     * @return
     */

    public String getEarlyBirdRate() {
        return earlyBirdRate;
    }


    /**
     * Sets the rate charged for "early bird" parking
     *
     * @param earlyBirdRate amount charged for "early bird"
     */

    public void setEarlyBirdRate(String earlyBirdRate) {
        this.earlyBirdRate = earlyBirdRate;
    }


    /**
     * Returns the hourly rate that the attendant will be charged
     *
     * @return
     */

    public String getHourlyRate() {
        return hourlyRate;
    }


    /**
     * Sets the hourly rate that attendant will be charged
     *
     * @param hourlyRate amount that will be charged hourly
     */

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }


    /**
     * Determines whether the attendant will be charged the "early bird" rate
     * or the hourly rate
     *
     * @param vehicle vehicle that will be charged for parking
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setPaymentScheme(Vehicle vehicle) {

        int i = 0;
        int parkingTime_Hour = vehicle.parkingTime_Date_Hour;

        if(parkingTime_Hour > 10 || parkingTime_Hour < 4 ) {
            this.paymentScheme = vehicle.getHourlyRate();
        }
        else
            this.paymentScheme = vehicle.getEarlyBirdRate();
    }


    /**
     * Returns rate which attendant was charged
     *
     * @return
     */

    public String getPaymentScheme() {
        return paymentScheme;
    }


    /**
     * Displays vehicle parking information
     *
     * @return
     */

    public String showParkingInfo() {
        return  "Attendant: "+this.getOwnerName()+"\n"+
                "Vehicle Type: "+this.getVehicleType()+"\n"+
                "License: "+this.getLicensePlateNum()+"\n"+
                "Parking Location: "+this.getParkingLocation()+"\n"+
                "Parking Date/Time: "+this.getParkingTime_Date()+"\n"+
                "Payment Rate: $"+this.getPaymentScheme()+"/Hr";
    }


    /**
     * Displays vehicle retrieval information
     *
     * @return
     */

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
