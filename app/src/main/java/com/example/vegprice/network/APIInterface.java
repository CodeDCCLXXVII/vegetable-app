package com.example.vegprice.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface APIInterface {

    @PUT("vegetables/updateVegetable/{vegetableId}")
    Call<APIMessage> updateVegetable(@Path("vegetableId") String vegetableId, @Body TaskRequest taskRequest);

    @POST("vegetables/addVegetable")
    Call<APIMessage> addVegetable(@Body TaskRequest taskRequest);

    @DELETE("vegetables/deleteVegetable/{vegetableId}")
    Call<APIMessage> deleteVegetable(@Path("vegetableId") String vegetableId);

    @GET("vegetables/getall/{page}/{size}")
    Call<APIResponseVegList> getVegetables(@Path("page") int page, @Path("size") int size);

}
