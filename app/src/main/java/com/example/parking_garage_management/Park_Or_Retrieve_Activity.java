package com.example.parking_garage_management;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Allows user to park or retrieve vehicle by selecting
 * the vehicle type and entering license plate number
 *
 * @author Arthur K. Edouard
 */

public class Park_Or_Retrieve_Activity extends Create_Account_Activity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button park;
    private Button retrieve;
    private EditText licenseNumberInput;
    private String licenseNumber;
    private String parkingSpaceNum = null;
    private int numOfCars = 0;
    private int numOfTrucks = 0;
    private int numOfMotorcycles = 0;
    private UserAccount userAccountAccessed;
    private ParkingGarageManager pgm = new ParkingGarageManager();
    HashMap<String, Vehicle> carLot = new HashMap<String, Vehicle>();
    HashMap<String, Vehicle> truckLot = new HashMap<String, Vehicle>();
    HashMap<String, Vehicle> motorcycleLot = new HashMap<String, Vehicle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park__or__retrieve_);

        getSupportActionBar().setTitle("Park Or Retrieve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadParkOrRetrieve();
        radioGroup = findViewById(R.id.radio_group);
        park = (Button) findViewById(R.id.button_park);
        retrieve = (Button) findViewById(R.id.button_retrieve);
        licenseNumberInput = (EditText) findViewById(R.id.edit_text_licensenumber);

        licenseNumberInput.addTextChangedListener(parkRetrieveTextWatcher);

        Intent intent = getIntent();
        userAccountAccessed = intent.getParcelableExtra("User Account Accessed");

        park.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if(radioButton == findViewById(R.id.radio_button_car)){
                    if(numOfCars == 10){
                        carLotFullDialog();
                    }else if(carLot.containsKey(licenseNumber)){
                        vehicleAlreadyParked();
                    }
                    else{
                        parkCar();
                    }
                }
                else if (radioButton == findViewById(R.id.radio_button_truck)){
                    if(numOfTrucks == 10){
                        truckLotFullDialog();
                    }else if(truckLot.containsKey(licenseNumber)){
                        vehicleAlreadyParked();
                    }
                    else{
                        parkTruck();
                    }
                }else{
                    if(numOfMotorcycles == 10){
                        motorcycleLotFullDialog();
                    }else if(motorcycleLot.containsKey(licenseNumber)){
                        vehicleAlreadyParked();
                    }
                    else{
                        parkMotorcycle();
                    }
                }

                saveParkOrRetrieve();
            }
        });


        retrieve.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if(radioButton == findViewById(R.id.radio_button_car)){
                    if(!carLot.containsKey(licenseNumber)){
                        vehicleNotFoundDialogBox();
                    }else{
                        retrieveCar();
                    }
                }
                else if (radioButton == findViewById(R.id.radio_button_truck)){
                    if(!truckLot.containsKey(licenseNumber)){
                        vehicleNotFoundDialogBox();
                    }else{
                        retrieveTruck();
                    }
                }else{
                    if(!motorcycleLot.containsKey(licenseNumber)){
                        vehicleNotFoundDialogBox();
                    }else{
                        retrieveMotorcylce();
                    }
                }

                saveParkOrRetrieve();
            }
        });
    }


    /**
     * Displays dialog box that informs user that the vehicle
     * has already been parked after entering a licence number that
     * has already been entered.
     */

    private void vehicleAlreadyParked() {
        VehicleAlreadyParked vehicleAlreadyParked = new VehicleAlreadyParked();
        vehicleAlreadyParked.show(getSupportFragmentManager(), "vehicle already parked");
    }


    /**
     * Displays dialog box that informs user that the motorcycle lot is full
     */

    private void motorcycleLotFullDialog() {
        MotorcycleLotIsFull motorcycleLotIsFullL = new MotorcycleLotIsFull();
        motorcycleLotIsFullL.show(getSupportFragmentManager(), "motorcycle lot is full");
    }


    /**
     * Displays dialog box that informs user that the truck lot is full
     */

    private void truckLotFullDialog() {
        TruckLotIsFull truckLotIsFull = new TruckLotIsFull();
        truckLotIsFull.show(getSupportFragmentManager(), "truck lot is full");
    }


    /**
     * Displays dialog box that informs user that the car lot is full
     */

    private void carLotFullDialog() {
        CarLotIsFull carLotIsFull = new CarLotIsFull();
        carLotIsFull.show(getSupportFragmentManager(), "car lot is full");
    }


    /**
     * Saves status of all lots and number of vehicles parked
     */


    private void saveParkOrRetrieve(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared Parking", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(carLot);
        String json1 = gson.toJson(truckLot);
        String json2 = gson.toJson(motorcycleLot);
        String json3 = gson.toJson(pgm);
        editor.putString("car lot", json);
        editor.putString("truck lot", json1);
        editor.putString("motorcycle lot", json2);
        editor.putString("parking garage manager", json3);
        editor.putInt("number of cars", numOfCars);
        editor.putInt("number of trucks", numOfTrucks);
        editor.putInt("number of motorcycles", numOfMotorcycles);
        editor.apply();
    }


    /**
     * Restores last status of vehicle lots and number of vehicles parked
     */

    private void loadParkOrRetrieve() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared Parking", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("car lot", null);
        String json1 = sharedPreferences.getString("truck lot", null);
        String json2 = sharedPreferences.getString("motorcycle lot", null);
        String json3 = sharedPreferences.getString("parking garage manager", null);
        Type type = new TypeToken<HashMap<String, Vehicle>>(){}.getType();
        carLot = gson.fromJson(json,type);
        truckLot = gson.fromJson(json1, type);
        motorcycleLot = gson.fromJson(json2, type);
        pgm = gson.fromJson(json3, ParkingGarageManager.class);
        int i = 0;
        numOfCars = sharedPreferences.getInt("number of cars", i);
        numOfTrucks = sharedPreferences.getInt("number of trucks", i);
        numOfMotorcycles = sharedPreferences.getInt("number of motorcycles", i);

        if(carLot == null){
            carLot = new HashMap<String, Vehicle>();
        }

        if(truckLot == null){
            truckLot = new HashMap<String, Vehicle>();
        }

        if(motorcycleLot == null){
            motorcycleLot = new HashMap<String, Vehicle>();
        }

        if(pgm == null){
            pgm = new ParkingGarageManager();
        }

    }


    /**
     * Displays dialog box that shows the user parking information
     * after user clicks park button
     *
     * @param vehicle vehicle that has been parked
     */

    private void displayTicketMsg(Vehicle vehicle) {
        DisplayTicket ticket = new DisplayTicket();
        ticket.setMessage(vehicle.showParkingInfo());
        ticket.show(getSupportFragmentManager(), "vehicle ticket");
    }


    /**
     * Displays dialog bos that shows the receipt information
     * after user clicks retrieve button
     *
     * @param vehicle
     * @param totalDue
     */

    private void displayReceiptMsg(Vehicle vehicle, String totalDue) {
        DisplayReceipt receipt = new DisplayReceipt();
        receipt.setMessage(vehicle.showRetrievalInfo()+"\n"+"Amount Due: $"+totalDue);
        receipt.show(getSupportFragmentManager(), "vehicle receipt");
    }


    /**
     * Displays dialog box that informs user that the vehicle can not be found
     */

    private void vehicleNotFoundDialogBox() {
        VehicleNotFound vehicleNotFound = new VehicleNotFound();
        vehicleNotFound.show(getSupportFragmentManager(), "vehicle not found");
    }

    /**
     * Creates vehicle object referenced as a car. Assigns car with the
     * name of the owner, license number etc., stores the object
     * in car lot and calls display ticket method to show information
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void parkCar() {

        Vehicle car = new Vehicle();
        String ownerFirstName = userAccountAccessed.getFirstName();
        String ownerLastName = userAccountAccessed.getLastName();
        car.setOwnerName(ownerFirstName+" "+ownerLastName);
        car.setLicensePlateNum(licenseNumber);
        car.setVehicleType("Car");
        car.setHourlyRate("2.50");
        car.setEarlyBirdRate("20.00");
        LocalDateTime parkingTime_Date = LocalDateTime.now();
        car.setParkingTime_Date(parkingTime_Date.toString());
        car.setParkingTime_Date_Hour(parkingTime_Date.getHour());
        car.setPaymentScheme(car);
        parkingSpaceNum = pgm.assignParkingSpaceNum("C");
        car.setParkingLocation(parkingSpaceNum);
        carLot.put(licenseNumber, car);
        numOfCars++;
        displayTicketMsg(car);
    }


    /**
     * Creates vehicle object referenced as a truck. Assigns truck with the
     * name of the owner, license number etc., stores the object
     * in truck lot and calls display ticket method to show information
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void parkTruck() {

        Vehicle truck = new Vehicle();
        String ownerFirstName = userAccountAccessed.getFirstName();
        String ownerLastName = userAccountAccessed.getLastName();
        truck.setOwnerName(ownerFirstName+" "+ownerLastName);
        truck.setLicensePlateNum(licenseNumber);
        truck.setVehicleType("Truck");
        truck.setHourlyRate("5.00");
        truck.setEarlyBirdRate("40.00");
        LocalDateTime parkingTime_Date = LocalDateTime.now();
        truck.setParkingTime_Date(parkingTime_Date.toString());
        truck.setParkingTime_Date_Hour(parkingTime_Date.getHour());
        truck.setPaymentScheme(truck);
        parkingSpaceNum = pgm.assignParkingSpaceNum("T");
        truck.setParkingLocation(parkingSpaceNum);
        truckLot.put(licenseNumber, truck);
        numOfTrucks++;
        displayTicketMsg(truck);
    }


    /**
     * Creates vehicle object referenced as a motorcycle. Assigns motorcycle with the
     * name of the owner, license number etc., stores the object
     * in motorcycle lot and calls display ticket method to show information
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void parkMotorcycle() {

        Vehicle motorcycle = new Vehicle();
        String ownerFirstName = userAccountAccessed.getFirstName();
        String ownerLastName = userAccountAccessed.getLastName();
        motorcycle.setOwnerName(ownerFirstName+" "+ownerLastName);
        motorcycle.setLicensePlateNum(licenseNumber);
        motorcycle.setVehicleType("Motorcycle");
        motorcycle.setHourlyRate("1.00");
        motorcycle.setEarlyBirdRate("10.00");
        LocalDateTime parkingTime_Date = LocalDateTime.now();
        motorcycle.setParkingTime_Date(parkingTime_Date.toString());
        motorcycle.setParkingTime_Date_Hour(parkingTime_Date.getHour());
        motorcycle.setPaymentScheme(motorcycle);
        parkingSpaceNum = pgm.assignParkingSpaceNum("M");
        motorcycle.setParkingLocation(parkingSpaceNum);
        motorcycleLot.put(licenseNumber, motorcycle);
        numOfMotorcycles++;
        displayTicketMsg(motorcycle);
    }


    /**
     * Removes vehicle object referenced as car from car lot.
     *
     * Vehicle is first found using get method. The retrieval date and time is then
     * set for the vehicle and the space that has been vacated is inserted into
     * list of vacated spaces via parking garage manager object method insertVacantSpace
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void retrieveCar() {

        DecimalFormat df = new DecimalFormat("#0.00");
        LocalDateTime retrievalTime_Date = LocalDateTime.now();
        Vehicle car = carLot.get(licenseNumber);

        car.setRetrievalTime_Date(retrievalTime_Date);

        String exitedSpaceNum = car.getParkingLocation();
        pgm.insertVacantSpace("C", exitedSpaceNum);

        Double totalDue = pgm.calculateTotal(car);
        String formattedTotal = df.format(totalDue);

        displayReceiptMsg(car, formattedTotal);
        carLot.remove(licenseNumber);
        numOfCars--;
    }


    /**
     * Removes vehicle object referenced as truck from truck lot.
     *
     * Vehicle is first found using get method. The retrieval date and time is then
     * set for the vehicle and the space that has been vacated is inserted into
     * list of vacated spaces via parking garage manager object method insertVacantSpace
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void retrieveTruck() {

        DecimalFormat df = new DecimalFormat("#0.00");
        LocalDateTime retrievalTime_Date = LocalDateTime.now();
        Vehicle truck = truckLot.get(licenseNumber);

        truck.setRetrievalTime_Date(retrievalTime_Date);

        String exitedSpaceNum = truck.getParkingLocation();
        pgm.insertVacantSpace("T", exitedSpaceNum);

        Double totalDue = pgm.calculateTotal(truck);
        String formattedTotal = df.format(totalDue);

        displayReceiptMsg(truck, formattedTotal);
        truckLot.remove(licenseNumber);
        numOfTrucks--;
    }


    /**
     * Removes vehicle object referenced as motorcycle from motorcycle lot.
     *
     * Vehicle is first found using get method. The retrieval date and time is then
     * set for the vehicle and the space that has been vacated is inserted into
     * list of vacated spaces via parking garage manager object method insertVacantSpace
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void retrieveMotorcylce() {

        DecimalFormat df = new DecimalFormat("#0.00");
        LocalDateTime retrievalTime_Date = LocalDateTime.now();
        Vehicle motorcycle = motorcycleLot.get(licenseNumber);

        motorcycle.setRetrievalTime_Date(retrievalTime_Date);

        String exitedSpaceNum = motorcycle.getParkingLocation();
        pgm.insertVacantSpace("M", exitedSpaceNum);

        Double totalDue = pgm.calculateTotal(motorcycle);
        String formattedTotal = df.format(totalDue);

        displayReceiptMsg(motorcycle, formattedTotal);
        motorcycleLot.remove(licenseNumber);
        numOfMotorcycles--;
    }


    /**
     * Checks if license field has been filled, enables retrieve button
     * when license field has been filled
     */

    private TextWatcher parkRetrieveTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            licenseNumber = licenseNumberInput.getText().toString().trim();
            park.setEnabled(!licenseNumber.isEmpty());
            retrieve.setEnabled(!licenseNumber.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
