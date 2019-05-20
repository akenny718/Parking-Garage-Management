package com.example.parking_garage_management;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Parking garage manager manages and assigns parking spaces as well
 * as calculates the amount due upon retrieval
 *
 * @author Arthur K. Edouard
 */

public class ParkingGarageManager {
    private ArrayList<String> vacantCarSpaces = new ArrayList<String>(Arrays.asList("0C","1C","2C","3C","4C","5C","6C","7C","8C","9C"));
    private ArrayList<String> vacantTruckSpaces = new ArrayList<String>(Arrays.asList("0T","1T","2T","3T","4T","5T","6T","7T","8T","9T"));
    private ArrayList<String> vacantMotorCycleSpaces = new ArrayList<String>(Arrays.asList("0M","1M","2M","3M","4M","5M","6M","7M","8M","9M"));
    private double totalDue = 0;

    /**
     * Contructs a garage manager
     */

    ParkingGarageManager(){

    }


    /**
     * Inserts the vehicle space number that has been vacated after a retrieval
     * and sorts spaces in ascending order to ensure closest space is always assigned
     *
     * @param vehicleType type of vehicle that has left the lot
     * @param newVacantSpace space which the vehicle has vacated
     */

    public void insertVacantSpace(String vehicleType, String newVacantSpace) {

        if(vehicleType.compareTo("C") == 0){
            vacantCarSpaces.add(newVacantSpace);
            Collections.sort(vacantCarSpaces);
        }

        if(vehicleType.compareTo("T") == 0){
            vacantTruckSpaces.add(newVacantSpace);
            Collections.sort(vacantTruckSpaces);
        }

        if(vehicleType.compareTo("M") == 0){
            vacantMotorCycleSpaces.add(newVacantSpace);
            Collections.sort(vacantMotorCycleSpaces);
        }

    }


    /**
     * Assigns the closest parking space available in the matching
     * vehicle type location, removes from list of vacant spaces, then sorts
     * the list in ascending order to ensure closest space is always assigned
     *
     * @param vehicleType the type of vehicle that is being parked
     * @return parking space number to assign to a vehicle
     */

    public String assignParkingSpaceNum(String vehicleType) {

        String parkingSpaceNum = null;

        if(vehicleType.compareTo("C") == 0) {
            parkingSpaceNum = vacantCarSpaces.get(0);
            vacantCarSpaces.remove(parkingSpaceNum);
            Collections.sort(vacantCarSpaces);
        }

        if(vehicleType.compareTo("T") == 0) {
            parkingSpaceNum = vacantTruckSpaces.get(0);
            vacantTruckSpaces.remove(parkingSpaceNum);
            Collections.sort(vacantTruckSpaces);
        }

        if(vehicleType.compareTo("M") == 0) {
            parkingSpaceNum = vacantMotorCycleSpaces.get(0);
            vacantMotorCycleSpaces.remove(parkingSpaceNum);
            Collections.sort(vacantMotorCycleSpaces);
        }

        return parkingSpaceNum;

    }


    /**
     * Calculates the amount due based on when a vehicle was parked and for how long;
     * Only uses hour of park and retrieve
     *
     * @param vehicle vehicle which will be evaluated for length of stay
     * @return dollar amount due for stay
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double calculateTotal(Vehicle vehicle) {

        int parkedHour = vehicle.getParkingTime_Date_Hour();
        int retrievalHour = vehicle.getRetrievalTime_Date().getHour();
        int totalHours =  retrievalHour - parkedHour;

        if(parkedHour < 4 || parkedHour > 10) {
            totalDue = totalHours * Double.parseDouble(vehicle.getHourlyRate());
        }
        else
            totalDue = 20;

        return totalDue;
    }


    /**
     * Returns an ArrayList object that is a list of vacant car spaces
     *
     * @return returns vacant car spaces object
     */

    public ArrayList getListOfCarVacancies() {
        return vacantCarSpaces;
    }


    /**
     * Returns an ArrayList object that is a list of vacant truck spaces
     *
     * @return returns vacant truck spaces object
     */

    public ArrayList getListOfTruckVacancies() {
        return vacantTruckSpaces;
    }


    /**
     * Returns an ArrayList object that is a list of vacant motorcycle spaces
     *
     * @return returns vacant motorcycle spaces object
     */

    public ArrayList getListOfMotorCycleVacancies() {
        return vacantMotorCycleSpaces;
    }

}
