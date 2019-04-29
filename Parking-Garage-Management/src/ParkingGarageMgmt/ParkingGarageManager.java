package ParkingGarageMgmt;
import java.util.*;

public class ParkingGarageManager {
	
	private ArrayList<String> vacantCarSpaces = new ArrayList<String>(Arrays.asList("0C","1C","2C","3C","4C","5C","6C","7C","8C","9C"));
	private ArrayList<String> vacantTruckSpaces = new ArrayList<String>(Arrays.asList("0T","1T","2T","3T","4T","5T","6T","7T","8T","9T"));
	private ArrayList<String> vacantMotorCycleSpaces = new ArrayList<String>(Arrays.asList("0M","1M","2M","3M","4M","5M","6M","7M","8M","9M"));
	private double totalDue = 0;
	
	
	ParkingGarageManager(){
		
	}
	
	
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
	
	
	public double calculateTotal(Vehicle vehicle) {
		
		int parkedHour = vehicle.getParkingTime_Date().getHour();
		int retrievalHour = vehicle.getRetrievalTime_Date().getHour();
		int totalHours =  retrievalHour - parkedHour;
		
		if(parkedHour < 4 || parkedHour > 10) {
			totalDue = totalHours * Double.parseDouble(vehicle.getHourlyRate());
		}
		else
		   totalDue = 20; 
		
		return totalDue;
	}
	
	
	public ArrayList getListOfCarVacancies() {
		return vacantCarSpaces;
	}
	
	
	public ArrayList getListOfTruckVacancies() {
		return vacantTruckSpaces;
	}
	
	
	public ArrayList getListOfMotorCycleVacancies() {
		return vacantMotorCycleSpaces;
	}
	

}
