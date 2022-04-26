package com.example.bus_e_no;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bus_e_no.adapter.Adapter;
import com.example.bus_e_no.model.Model;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class AllBusActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Model> userList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bus);

        initData();
        initRecyclerView();
    }

    private void initData() {
        userList = new ArrayList<>();
        userList.add(new Model("Route - 01","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 02","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 03","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 04","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 05","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 06","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 07","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 08","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 09","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 10","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 11","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 12","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 13","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 14","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 15","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 16","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 17","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 18","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 19","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 20","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 21","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 22","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 23","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 24","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 25","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 26","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 27","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 28","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 29","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Route - 30","Driver's Name",R.drawable.ic_callicon));
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}