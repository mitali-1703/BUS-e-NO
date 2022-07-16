package com.example.bus_e_noadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bus_e_noadmin.adapter.Adapter;
import com.example.bus_e_noadmin.model.Model;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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

        ImageView edit = findViewById(R.id.cardImage1);
        ImageView delete = findViewById(R.id.cardImage2);


        initData();
        initRecyclerView();


        MaterialToolbar materialToolbar = findViewById(R.id.appBar);

/**        To tell the Activity that the ActionBar coming on this Activity
 *          isn't the default Theme ActionBar but its the custom Toolbar
 *          that we have made for this activity
 */
        setSupportActionBar(materialToolbar);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"Sure to remove bus?", BaseTransientBottomBar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: DO SOMETHING HERE
                    }
                });
                snackbar.show();
            }
        });

    }

    private void initData() {
        userList = new ArrayList<>();
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
        userList.add(new Model("Bus No. 1","Driver's Name",R.drawable.ic_pen,R.drawable.ic_delete));
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