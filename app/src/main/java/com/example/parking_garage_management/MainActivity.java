package com.example.parking_garage_management;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Button logIn;
    private Button createAcct;
    private EditText userNameInput;
    private EditText passWordInput;
    protected String userName;
    private String passWord;
    private String actualUserName;
    private String actualPassWord;
    private HashMap<String, UserAccount> userAccountBag = new HashMap<String, UserAccount>();
    UserAccount userAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMainActivity();

        userNameInput = (EditText) findViewById(R.id.edit_text_userName);
        passWordInput = (EditText) findViewById(R.id.edit_text_passWord);
        logIn = (Button) findViewById(R.id.logIn);
        createAcct = (Button) findViewById(R.id.createAccount);

        userNameInput.addTextChangedListener(loginTextWatcher);
        passWordInput.addTextChangedListener(loginTextWatcher);

        Intent intent = getIntent();
        userAccount = intent.getParcelableExtra("User Account");

        if(userAccount != null){
            actualUserName = userAccount.getUserName();
            actualPassWord = userAccount.getPassword();
            userAccountBag.put(actualUserName, userAccount);
        }

        saveMainActivity();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameInput.getText().toString().trim();
                passWord = passWordInput.getText().toString().trim();

                if(!userAccountBag.containsKey(userName)){
                    // USERNAME NOT FOUND
                    wrongUserNameDialogBox();
                } else
                if(!userAccountBag.get(userName).getPassword().equals(passWord)){
                    // PASSWORD DOES NOT MATCH
                    wrongPassWordDialogBox();
                } else {
                    // USERNAME / PASSWORD MATCH
                    UserAccount userAccountAccessed = userAccountBag.get(userName);
                    openParkOrRetrieveScreen(userAccountAccessed);
                }
            }
        });

        createAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccountScreen();
            }
        });
    }


    private void saveMainActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared UserAcct Bag", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userAccountBag);
        editor.putString("user accounts", json);
        editor.apply();
    }


    private void loadMainActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared UserAcct Bag", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user accounts", null);
        Type type = new TypeToken<HashMap<String, UserAccount>>(){}.getType();
        userAccountBag = gson.fromJson(json,type);

        if(userAccountBag == null){
            userAccountBag = new HashMap<String, UserAccount>();
        }

    }


    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userName = userNameInput.getText().toString().trim();
            passWord = passWordInput.getText().toString().trim();
            logIn.setEnabled(!userName.isEmpty() && !passWord.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void wrongPassWordDialogBox() {
        WrongPassWord wrongPassWord = new WrongPassWord();
        wrongPassWord.show(getSupportFragmentManager(), "wrong password");
    }

    private void wrongUserNameDialogBox() {
        WrongUserName wrongUserName = new WrongUserName();
        wrongUserName.show(getSupportFragmentManager(), "wrong user name");
    }

    private void openParkOrRetrieveScreen(UserAccount userAccountAccessed) {
        Intent intent = new Intent(this, Park_Or_Retrieve_Activity.class);
        intent.putExtra("User Account Accessed", userAccountAccessed);
        startActivity(intent);
    }

    private void openCreateAccountScreen() {
        Intent intent = new Intent(this, Create_Account_Activity.class);
        startActivity(intent);
    }

}
