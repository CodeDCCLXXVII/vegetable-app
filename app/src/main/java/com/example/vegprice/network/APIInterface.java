package com.example.vegprice.network;

import com.example.vegprice.pojo.Vegetable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface APIInterface {

    @PUT("vegetables/updateVegetable/{vegetableId}")
    Call<APIMessage> updateVegetable(@Path("vegetableId") String vegetableId, @Body Vegetable vegetable);

    @POST("vegetables/addVegetable")
    Call<APIResponseVegList> addVegetable(@Body Vegetable vegetable);

    @DELETE("vegetables/deleteVegetable/{vegetableId}")
    Call<APIResponseVegList> deleteVegetable(@Path("vegetableId") String vegetableId);

    @GET("vegetables/getall/{page}/{size}")
    Call<APIResponseVegList> getVegetables(@Path("page") int page, @Path("size") int size);

}
