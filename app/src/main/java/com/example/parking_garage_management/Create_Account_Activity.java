package com.example.parking_garage_management;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Screen allows user to create a new account
 *
 * @author Arthur K. Edouard
 */

public class Create_Account_Activity extends MainActivity {

    private Button backButton;
    private Button finishedButton;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText passWordInput;
    private String firstName;
    private String lastName;
    private String passWord;
    protected UserAccount userAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account_);

        getSupportActionBar().setTitle("Create Account");

        finishedButton = (Button) findViewById((R.id.button_finished));
        firstNameInput = (EditText) findViewById(R.id.edit_text_firstname);
        lastNameInput = (EditText) findViewById(R.id.edit_text_lastname);
        passWordInput = (EditText) findViewById(R.id.edit_text_createPassWord);

        firstNameInput.addTextChangedListener(createAccountTextWatcher);
        lastNameInput.addTextChangedListener(createAccountTextWatcher);
        passWordInput.addTextChangedListener(createAccountTextWatcher);

        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameInput.getText().toString().trim();
                lastName = lastNameInput.getText().toString().trim();
                passWord = passWordInput.getText().toString().trim();
                userAccount = new UserAccount(firstName, lastName, passWord);
                String userName = userAccount.getUserName();

                if(userAccount.getPassword().contains("0")){
                    incorrectPassWordFormat();
                }else {
                   // userAccountBag.put(userName, userAccount);
                    displayUserName(userName);
                    openMainScreenFromCreateAccount();
                }
            }
        });
    }


    /**
     * Displays user name that has been created for user
     *
     * @param userName emitted user name
     */

    public void displayUserName(String userName) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);

        toastText.setText("Username is: "+userName);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.setDuration((Toast.LENGTH_LONG));
        toast.setView(layout);
        toast.show();
    }

    /**
     * Displays dialog box that informs user that the format
     * of the password created does not meet the criteria
     */

    public void incorrectPassWordFormat() {
        WrongPassWordFormat wrongFormat = new WrongPassWordFormat();
        wrongFormat.show(getSupportFragmentManager(), "wrong format");
    }


    /**
     * Opens the log screen
     */

    public void openMainScreenFromCreateAccount() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User Account", userAccount);
        startActivity(intent);
    }


    /**
     * Checks to make sure all fields have been filled, enables log in button
     * when all fields are filled
     */

    public TextWatcher createAccountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            firstName = firstNameInput.getText().toString().trim();
            lastName = lastNameInput.getText().toString().trim();
            passWord = passWordInput.getText().toString().trim();
            finishedButton.setEnabled(!firstName.isEmpty() && !lastName.isEmpty() && !passWord.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
