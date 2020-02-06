package com.example.hospblock;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    ProgressDialog progressDialog;

    private EditText name, address, phone, age, aadhar, password;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        aadhar = findViewById(R.id.aadhar);
        password = findViewById(R.id.password);

        btnSignUp = (Button) findViewById(R.id.register);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(name.getText().toString(),
                             address.getText().toString(),
                             phone.getText().toString(),
                             age.getText().toString(),
                             aadhar.getText().toString(),
                             password.getText().toString());

            }
        });

    }

    private void registerUser(final String Name, final String Address, final String Phone,
                              final String Age, String aadhar, String password) {
        // Tag used to cancel the request
//        String cancel_req_tag = "register";

        progressDialog.setMessage("Adding you ...");
        showDialog();
        RegisterPatientRequest registerPatientRequest = new RegisterPatientRequest();
        registerPatientRequest.aadhar = aadhar;
        registerPatientRequest.address = Address;
        registerPatientRequest.age = Age;
        registerPatientRequest.name = Name;
        registerPatientRequest.phone = Phone;
        registerPatientRequest.pass = password;
        Repo.getInstance().registerPatient(registerPatientRequest).observe(this, new Observer<RegisterPatientResponse>() {
            @Override
            public void onChanged(RegisterPatientResponse registerPatientResponse) {
                Log.d(TAG, "onChanged: " + registerPatientResponse);
                hideDialog();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void showDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
