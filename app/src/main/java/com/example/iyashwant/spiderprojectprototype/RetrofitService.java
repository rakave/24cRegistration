package com.example.iyashwant.spiderprojectprototype;

import com.example.iyashwant.spiderprojectprototype.auditions.AuditionHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by srikanth on 10/11/17.
 */

public interface RetrofitService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://24crafts.ml:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/user/audition/view")
    Call<List<AuditionHelper>> fetchAuditions(@Header("authorization") String jwtToken);
    }