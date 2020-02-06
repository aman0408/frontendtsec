package com.example.hospblock;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * User: Aman
 * Date: 06-02-2020
 * Time: 03:54 AM
 */
public class Repo {
    private static Repo repo;
    private APIInterface apiInterface;

    private static final String TAG = "Repo";
    public Repo() {
        apiInterface = RetrofitService.getRetrofitInstance().create(APIInterface.class);
    }

    public static Repo getInstance() {
        if (repo == null) {
            repo = new Repo();
        }
        return repo;
    }
    public LiveData<String> patientDashboard(String privateKey){
        MutableLiveData<String> resp=new MutableLiveData<>();
        apiInterface.getPatientDashboard(privateKey).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: "+response.body());
                resp.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return resp;
    }
    public LiveData<RegisterPatientResponse> registerPatient(RegisterPatientRequest registerPatientRequest){
        MutableLiveData<RegisterPatientResponse> resp=new MutableLiveData<>();
        apiInterface.registerPatient(registerPatientRequest).enqueue(new Callback<RegisterPatientResponse>() {
            @Override
            public void onResponse(Call<RegisterPatientResponse> call, Response<RegisterPatientResponse> response) {
                resp.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RegisterPatientResponse> call, Throwable t) {

            }
        });
        return resp;
    }

}
