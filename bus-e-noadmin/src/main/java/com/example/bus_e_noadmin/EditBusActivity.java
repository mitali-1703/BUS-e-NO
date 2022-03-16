package com.example.bus_e_noadmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.bus_e_noadmin.databinding.ActivityEditBusBinding;
import com.google.android.material.appbar.MaterialToolbar;

public class EditBusActivity extends AppCompatActivity {

    ActivityEditBusBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityEditBusBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        MaterialToolbar materialToolbar = findViewById(R.id.appBar);
//        To tell the Activity that the ActionBar coming on this Activity isn't the default Theme ActionBar but its the custom Toolbar
//        that we have made for this activity
        setSupportActionBar(materialToolbar);


//        Dialog Box
        b.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditBusActivity.this);

                builder.setMessage("Discard the change you made?")
                        .setCancelable(false)
                        .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }
}