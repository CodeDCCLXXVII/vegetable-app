package com.example.vegprice.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vegprice.DataService.DataService;
import com.example.vegprice.DataService.VegetablesAdapter;
import com.example.vegprice.R;
import com.example.vegprice.pojo.Vegetable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static List<Vegetable> vegetables;
    public static VegetablesAdapter vegetablesAdapter;
    public static EditText vegNameEditTxt, vegPriceEditTxt;
    public static Activity activity;
    public static Context context;
    public static AlertDialog dialog;
    private FloatingActionButton addVeg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        context = getContext();
        RecyclerView rv = root.findViewById(R.id.listViewVegs);
        vegetables = new ArrayList<>();
        DataService.getVegetables(getContext());
        vegetablesAdapter = new VegetablesAdapter(vegetables, getContext());
        rv.setAdapter(vegetablesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        addVeg = root.findViewById(R.id.addVeg);
        addVeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVegetable();
            }
        });
        return root;
    }


    public static void updateVegetable(final Vegetable vegetable){

        LayoutInflater factory = LayoutInflater.from(activity);
        final View textEntryView = factory.inflate(R.layout.veg_alter, null);
        vegNameEditTxt = textEntryView.findViewById(R.id.vegNameInput);
        vegPriceEditTxt = textEntryView.findViewById(R.id.vegPriceInput);
        vegNameEditTxt.setText(vegetable.getName());
        vegPriceEditTxt.setText(String.valueOf(vegetable.getPrice()));
        final AlertDialog.Builder alert = new AlertDialog.Builder(activity,R.style.AppTheme_Dark_Dialog);
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_home_black_24dp).setTitle("Update Vegetable").setView(textEntryView).setPositiveButton("Submit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {

                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                    }
                });
        dialog  = alert.create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean wantToCloseDialog = false;
                wantToCloseDialog = validateVegetableInput();

                if(wantToCloseDialog) {

                    vegetable.setName(vegNameEditTxt.getText().toString());
                    vegetable.setPrice(Integer.valueOf(vegPriceEditTxt.getText().toString()));

                    DataService.updateVegetable(context,"Updating Vegetable", "Submitting vegetable details. Please wait...", vegetable);
                }
            }
        });
    }


    public static void addVegetable(){

        LayoutInflater factory = LayoutInflater.from(activity);
        final View textEntryView = factory.inflate(R.layout.veg_alter, null);
        vegNameEditTxt = textEntryView.findViewById(R.id.vegNameInput);
        vegPriceEditTxt = textEntryView.findViewById(R.id.vegPriceInput);
        final AlertDialog.Builder alert = new AlertDialog.Builder(activity,R.style.AppTheme_Dark_Dialog);
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_home_black_24dp).setTitle("Add Vegetable").setView(textEntryView).setPositiveButton("Submit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {

                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                    }
                });
        dialog  = alert.create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean wantToCloseDialog = false;
                wantToCloseDialog = validateVegetableInput();

                if(wantToCloseDialog) {
                    Vegetable vegetable = new Vegetable();
                    vegetable.setName(vegNameEditTxt.getText().toString());
                    vegetable.setPrice(Integer.valueOf(vegPriceEditTxt.getText().toString()));
                    DataService.addVegetable(context,"Add Vegetable", "Submitting vegetable details. Please wait...", vegetable);
                }
            }
        });


    }

    public static void alertWithAction(String title, String message, final String actionSms, final String vegetableId) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.AppTheme_Dark_Dialog);
        alertDialogBuilder.setMessage(message)
                .setTitle(title)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if(actionSms.equalsIgnoreCase("delete"))
                        {
                            DataService.deleteVegetable(context, "Deleting Vegetable", "Submitting deletion request, Please wait ...", vegetableId);

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }

    public static boolean validateVegetableInput(){

        String name = vegNameEditTxt.getText().toString();
        String  price = vegPriceEditTxt.getText().toString();

        if(name.isEmpty() || name.length() < 3){

            vegNameEditTxt.setError("Invalid name input");
            vegNameEditTxt.requestFocus();
            return  false;
        }else
            vegNameEditTxt.setError(null);

        if(price.isEmpty()){

            vegPriceEditTxt.setError("Invalid input");
            vegPriceEditTxt.requestFocus();
            return  false;
        }else
            vegPriceEditTxt.setError(null);

        return true;

    }





}