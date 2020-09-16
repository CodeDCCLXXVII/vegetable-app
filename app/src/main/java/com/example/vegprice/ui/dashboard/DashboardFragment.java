package com.example.vegprice.ui.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vegprice.DataService.DataService;
import com.example.vegprice.DataService.TransactionsAdapter;
import com.example.vegprice.R;
import com.example.vegprice.pojo.Transaction;
import com.example.vegprice.pojo.VegetableTrans;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public static List<VegetableTrans> vegetableTransList;
    public static TransactionsAdapter transactionsAdapter;
    public static EditText vegNameEditTxt, vegQtyEditTxt;
    public static Activity activity;
    public static Context context;
    public static AlertDialog dialog;
    private FloatingActionButton addVegCost;
    public static Transaction transaction;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        activity = getActivity();
        context = getContext();
        RecyclerView rv = root.findViewById(R.id.listViewTransItems);
        vegetableTransList = new ArrayList<>();
        transactionsAdapter = new TransactionsAdapter(vegetableTransList, getContext());
        rv.setAdapter(transactionsAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        addVegCost = root.findViewById(R.id.addVegCost);
        addVegCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calclVegetableCost();
            }
        });

        Button generateReceipt = root.findViewById(R.id.generateReceipt);
        generateReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transaction != null) {
                    DataService.calcTotalTransactionCost(context, "Transaction cost", "Getting total cost calculation, please wait...", transaction.getId());
                }
                else
                    DataService.alert(context, "No items calculated", "Calculate vegetable cost first");
            }
        });

        return root;
    }

    public static void calclVegetableCost(){

        LayoutInflater factory = LayoutInflater.from(activity);
        final View textEntryView = factory.inflate(R.layout.calc_veg_cost, null);
        vegNameEditTxt = textEntryView.findViewById(R.id.vegCostNameInput);
        vegQtyEditTxt = textEntryView.findViewById(R.id.vegCostQtyInput);
        final AlertDialog.Builder alert = new AlertDialog.Builder(activity,R.style.AppTheme_Dark_Dialog);
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_home_black_24dp).setTitle("Calculate Vegetable Cost").setView(textEntryView).setPositiveButton("Submit",
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
                    VegetableTrans vegetableTrans = new VegetableTrans();
                    vegetableTrans.setVegName(vegNameEditTxt.getText().toString());
                    vegetableTrans.setQuantity(Float.valueOf(vegQtyEditTxt.getText().toString()));
                    Log.d("=VegCost", vegetableTrans.toString());
                    DataService.calcVegetableCost(context,"Calculate Vegetable Cost", "Submitting vegetable details. Please wait...", vegetableTrans);
                }
            }
        });


    }


    public static boolean validateVegetableInput(){

        String name = vegNameEditTxt.getText().toString();
        String  price = vegQtyEditTxt.getText().toString();

        if(name.isEmpty() || name.length() < 3){

            vegNameEditTxt.setError("Invalid name input");
            vegNameEditTxt.requestFocus();
            return  false;
        }else
            vegNameEditTxt.setError(null);

        if(price.isEmpty()){

            vegQtyEditTxt.setError("Invalid input");
            vegQtyEditTxt.requestFocus();
            return  false;
        }else
            vegQtyEditTxt.setError(null);

        return true;

    }
}