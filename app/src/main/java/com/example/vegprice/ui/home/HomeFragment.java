package com.example.vegprice.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static List<Vegetable> vegetables;
    public static VegetablesAdapter vegetablesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

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
        return root;
    }
}