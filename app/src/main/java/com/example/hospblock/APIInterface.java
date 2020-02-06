package com.example.hospblock;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * User: Aman
 * Date: 06-02-2020
 * Time: 03:00 AM
 */
public interface APIInterface {
    @GET(value = "patientDashboard/{privateKey}")
    Call<String> getPatientDashboard(@Path("privateKey") String privateKey);

    @POST(value = "register")
    Call<RegisterPatientResponse> registerPatient(@Body RegisterPatientRequest registerPatientRequest);
}
