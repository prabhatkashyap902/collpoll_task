package com.prodev.collpoll_task.api;

import com.prodev.collpoll_task.model.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api_interface {

    String JSONURL = "https://swapi.co/api/";
/*
    @GET("people")
    Call<String> getString();*/

    @GET("people")
     //i.e https://api.test.com/Search?
    Call<String> getProducts(@Query("page") int page);



    @GET(".")
    Call<String> getString();
}
