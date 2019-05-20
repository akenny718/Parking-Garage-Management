package com.example.parking_garage_management;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserAccount facilitates the process of creating a new user account
 *
 * @author Arthur K. Edouard
 */

public class UserAccount implements Parcelable {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private static int ID = 10000000;
    private String iD;

    /**
     * Constructs and initializes a user account
     *
     * @param firstName first name of new user
     * @param lastName last name of new user
     * @param passWord password of new user
     */

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


    /**
     * Constructs and initializes a user account
     */

    public UserAccount() {

    }


    protected UserAccount(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        userName = in.readString();
        password = in.readString();
        iD = in.readString();
    }

    public static final Creator<UserAccount> CREATOR = new Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };


    /**
     * Checks to see if password that is entered meets password criteria
     *
     * @param passWord password entered by user
     * @return true if correct format
     */

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


    /**
     *
     * Emits a user name for the user based on the first name, last name, and ID
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param iD ID for user
     * @return returns a user name
     */

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


    /**
     *
     * Returns a clone of object
     *
     * @return
     */

    public UserAccount clone() {
        UserAccount clone = new UserAccount(firstName, lastName, password);
        return clone;
    }


    /**
     * Returns the first name of user
     *
     * @return
     */

    public String getFirstName() {
        return firstName;
    }


    /**
     * Sets the first name of user
     *
     * @param firstName first name of user
     */

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    /**
     * Returns last name
     *
     * @return
     */

    public String getLastName() {
        return lastName;
    }


    /**
     * Sets last name of user
     *
     * @param lastName last name of user
     */

    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    /**
     * Returns user name
     *
     * @return
     */

    public String getUserName() {
        return userName;
    }


    /**
     * Sets user name
     * @param userName user name of user
     */

    public void setUserName(String userName){
        this.userName = userName;
    }


    /**
     * Returns password of user
     *
     * @return
     */

    public String getPassword() {
        return password;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(iD);
    }
}
