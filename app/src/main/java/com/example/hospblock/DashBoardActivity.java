package com.example.hospblock;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class DashBoardActivity extends AppCompatActivity {

    //    private ArrayList<Doctor> doctors;
    private static final String TAG = "DashBoardActivity";
    String privateKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        privateKey = getIntent().getStringExtra("privateKey");
//        doctors=new ArrayList<>();
        Repo.getInstance().patientDashboard(privateKey).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "onChanged: " + s);
            }
        });
    }
}
