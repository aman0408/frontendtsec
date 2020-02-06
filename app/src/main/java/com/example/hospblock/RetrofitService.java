package com.example.hospblock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * User: Aman
 * Date: 29-12-2019
 * Time: 01:45 PM
 */
public class RetrofitService {
    private static final String BASE_URL = "http://192.168.43.108:5000/";
//    private static String TOKEN;
    private static Retrofit retrofit;

//    public static String getTOKEN() {
//        return TOKEN;
//    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
//            TOKEN = PrefManager.getInstance().getDeviceToken();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .connectTimeout(5, TimeUnit.SECONDS)
//                    .writeTimeout(5, TimeUnit.SECONDS)
//                    .readTimeout(5, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
