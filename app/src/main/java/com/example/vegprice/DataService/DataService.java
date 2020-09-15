package com.example.vegprice.DataService;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.example.vegprice.R;
import com.example.vegprice.network.APIClient;
import com.example.vegprice.network.APIInterface;
import com.example.vegprice.network.APIMessage;
import com.example.vegprice.network.APIResponseVegList;
import com.example.vegprice.pojo.Vegetable;
import com.example.vegprice.ui.home.HomeFragment;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataService {

    static ProgressDialog progressDialog;
    private static APIInterface apiInterface=  APIClient.getClient().create(APIInterface.class);


    public static void getVegetables(final Context context){

        invokeProgressBar(context, "Available Vegetables", "Fetching vegetables, " +
                "please wait...");

        Call<APIResponseVegList> apiResponseCall = apiInterface.getVegetables(0, 100);

        apiResponseCall.enqueue(new Callback<APIResponseVegList>() {
            @Override
            public void onResponse(Call<APIResponseVegList> call, Response<APIResponseVegList> response) {

                progressDialog.dismiss();

                if(response.isSuccessful()) {

                    HomeFragment.vegetables.clear();
                    // productList = response.body()
                    if(response.body().getMessage() != null)
                        alert(context, response.body().getMessage(), response.body().getMessage());

                    else{
                        List<Vegetable> fetchedVegs = (List<Vegetable>) response.body().getMetadata();
                        for(int i = 0; i < fetchedVegs.size(); i++){

                            Vegetable tmp = new Vegetable(fetchedVegs.get(i).getName(), fetchedVegs.get(i).getPrice());
                            tmp.setId(fetchedVegs.get(i).getId());
                            HomeFragment.vegetables.add(tmp);
                        }
                        HomeFragment.vegetablesAdapter.notifyDataSetChanged();


                    }

                }

                if(response.errorBody() !=null) {

                    Toast.makeText(context, "Failed fetching Vegs"  , Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<APIResponseVegList> call, Throwable t) {

                parseNetworkIssue(context, t.getMessage());

            }
        });

    }

    public static void addVegetable(final Context context, String title, String sms, Vegetable vegetable){

        invokeProgressBar(context, title, sms);

        Call<APIMessage> apiResponseCall = apiInterface.addVegetable(vegetable);

        apiResponseCall.enqueue(new Callback<APIMessage>() {
            @Override
            public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {

                progressDialog.dismiss();

                if(response.isSuccessful()) {
                    HomeFragment.dialog.dismiss();

                    if(!response.body().getMessage().equalsIgnoreCase("success"))
                        alert(context, response.body().getMessage(), response.body().getMessage());

                    else{
                        getVegetables(context);
                    }

                }

                if(response.errorBody() !=null) {

                    Toast.makeText(context, "Failed adding Vegetable"  , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<APIMessage> call, Throwable t) {
                parseNetworkIssue(context, t.getMessage());

            }
        });

    }



    public static void updateVegetable(final Context context, String title, String sms, Vegetable vegetable){

        invokeProgressBar(context, title, sms);

        Call<APIMessage> apiResponseCall = apiInterface.updateVegetable(vegetable.getId(), vegetable);

        apiResponseCall.enqueue(new Callback<APIMessage>() {
            @Override
            public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {

                progressDialog.dismiss();

                if(response.isSuccessful()) {
                    HomeFragment.dialog.dismiss();

                    if(!response.body().getMessage().equalsIgnoreCase("success"))
                        alert(context, response.body().getMessage(), response.body().getMessage());

                    else{
                        getVegetables(context);
                    }

                }

                if(response.errorBody() !=null) {

                    Toast.makeText(context, "Failed updating Vegetable"  , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<APIMessage> call, Throwable t) {
                parseNetworkIssue(context, t.getMessage());

            }
        });

    }


    public static void invokeProgressBar(Context context, String event, String sms) {

        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(event);
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage(sms);
        progressDialog.show();


    }

    public static void parseNetworkIssue(Context context, String message){

        progressDialog.dismiss();
        message = message.toLowerCase();
        String error  = "Network Error";

        if(message.contains("after"))
            alert(context,error, "Our services are currently unavailable, we are performing an awesome update for you. Kindly try again later.");
        else if(message.contains("failed to connect"))
            alert(context,error, "Kindly check your internet connectivity and try again.");
        else
            alert(context,error, "Network error, if this persists, kindly contact the admin" + message);

    }

    public static void alert(Context context, String title, String message) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.AppTheme_Dark_Dialog);
        alertDialogBuilder.setMessage(message)
                .setTitle(title)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // activeAction(hour);
                        //  mapFragment.getMapAsync(MapsActivity.this);
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }

}
