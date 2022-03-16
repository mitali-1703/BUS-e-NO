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

        MaterialToolbar materialToolbar = findViewById(R.id.appBar);
//        To tell the Activity that the ActionBar coming on this Activity isn't the default Theme ActionBar but its the custom Toolbar
//        that we have made for this activity
        setSupportActionBar(materialToolbar);

    }

    private void initData() {
        userList = new ArrayList<>();
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_callicon));
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