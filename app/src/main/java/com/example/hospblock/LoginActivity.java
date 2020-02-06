package com.example.hospblock;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final String URL_FOR_LOGIN = "https://XXX.XXX.X.XX/android_login_example/login.php";
    ProgressDialog progressDialog;
    private EditText phone, otp;
    private Button login,register;
    String privateKey;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText) findViewById(R.id.phone);
        otp = (EditText) findViewById(R.id.otp);
        login = (Button) findViewById(R.id.login);
        register=findViewById(R.id.register);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        phone.getText().toString(),        // Phone number to verify
//                        60,                 // Timeout duration
//                        TimeUnit.SECONDS,   // Unit of timeout
//                        LoginActivity.this,               // Activity (for callback binding)
//                        mCallbacks
//                );        // OnVerificationStateChangedCallbacks

                loginUser(phone.getText().toString());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        // [END phone_auth_callbacks]
    }


    private void loginUser( final String phoneNumber) {

        FirebaseDatabase.getInstance().getReference().child("Users").child(phoneNumber).child("keystore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   privateKey=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Intent intent=new Intent(this,DashBoardActivity.class);
        intent.putExtra("privateKey",privateKey);
        startActivity(intent);

    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
