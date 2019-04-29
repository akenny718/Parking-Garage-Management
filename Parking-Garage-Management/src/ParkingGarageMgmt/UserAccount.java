package ParkingGarageMgmt;

import java.util.concurrent.atomic.AtomicInteger;

public class UserAccount {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private static int ID = 10000000;
	private String iD;
	
	
	public UserAccount(String firstName, String lastName, String passWord){
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID++; 
		this.iD = Integer.toString(this.ID);
		this.userName = emitUserName(this.firstName, this.lastName, this.iD);
		if(checkPassWord(passWord) == false) {
			this.password = "0";
		}
		else {
		this.password = passWord;
		}
	}


    public UserAccount() {

	}

	
	
	public boolean checkPassWord(String passWord) {
		
		// TESTED //
		int numCase = 0;
	    int specCase = 0;
	    int upCase = 0;
	    int loCase = 0;
	    
	char[] charPassWordArray = passWord.toCharArray();
		
		if(charPassWordArray.length < 8) {
			return false;
		}
		
		for(int i = 0; i < charPassWordArray.length; i++) {
			char c = charPassWordArray[i];
			
			if(Character.isUpperCase(c)){
                upCase++;
            }
            if(Character.isLowerCase(c)){
                loCase++;
            }
            if(Character.isDigit(c)){
               numCase++;
            }
            if(c >= 33 && c <= 47||c >= 58 && c <= 64 || c >= 91 && c <= 96 || c >= 123 && c <= 126){
                specCase++;
            }
            
            if(c < 33 || c >= 127) {
            	return false;
            }
        }
		
		if(specCase >= 1 && loCase >= 1 && upCase >= 1 && numCase >=1){
            return true;
        }
		else{
			return false;
		}
	}
	
	
	public String emitUserName(String firstName, String lastName, String iD){

		int j = 0;
		char [] lastNameCharArray = lastName.toCharArray();
		char [] firstNameCharArray = firstName.toCharArray();
		char [] iDCharArray = iD.toCharArray();


		if(lastNameCharArray.length < 4) {

			char [] userNameCharArray = new char[lastNameCharArray.length + 2];

			for(int i = 0; i < lastNameCharArray.length; i++) {

				userNameCharArray[i] = lastNameCharArray[i];
				j = i + 1;
			}

			userNameCharArray[j] = firstNameCharArray[0];
			j++;
			userNameCharArray[j] = iDCharArray[7];

			String userNameString = new String(userNameCharArray);

			return userNameString;

		}
		else {

			char [] userNameCharArray = new char[6];

			for(int i = 0; i < 4; i++){

				userNameCharArray[i] = lastNameCharArray[i];
				j = i + 1;
			}

			userNameCharArray[j] = firstNameCharArray[0];
			j++;
			userNameCharArray[j] = iDCharArray[7];

			String userNameString = new String(userNameCharArray);

			return userNameString;
		}

	}
	

	
	public UserAccount clone() {
		UserAccount clone = new UserAccount(firstName, lastName, password);
		return clone;
	}



    //GETTERS
	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


}