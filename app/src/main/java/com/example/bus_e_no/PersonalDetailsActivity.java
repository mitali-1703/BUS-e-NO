package com.example.bus_e_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bus_e_no.databinding.ActivityPersonalDetailsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class PersonalDetailsActivity extends AppCompatActivity {

    ActivityPersonalDetailsBinding b;
    String busRoute,course;

    String[] busRoutes = {"Route-01","Route-02","Route-03","Route-04","Route-05","Route-06","Route-07",
                        "Route-08","Route-09","Route-10","Route-11","Route-12","Route-13","Route-14","Route-15",
                        "Route-16","Route-17","Route-18","Route-19","Route-20","Route-21","Route-22","Route-23",
                        "Route-24","Route-25","Route-26","Route-27","Route-28","Route-29","Route-30"};

    ArrayAdapter<String> busRouteItems;

    String[] courses = {"B.Com.","B.Sc.Agriculture","B.Sc.Biotech","B.Sc.Hospitality & Hotel Mgmt.","B.Sc.Microbiology",
                        "B.Sc.PCM","B.of Hotel Mgmt.","B.A. J&MC","B.A. Other","B.Tech. Civil","B.Tech. CSE","B.Tech. ECE",
                        "B.Tech. EE","B.Tech. ME","BBA","BCA","Design Courses","Law","M.Sc. Agriculture","M.Sc. Biotech","M.Sc. CS",
                        "M.Sc. Microbiology","M.Sc. PCM","M.A. J&MC","M.Tech. Civil","M.Tech. CSE","M.Tech. ECE","M.Tech. EE",
                        "M.Tech. ME","MBA","MCA","PG Diploma"};

    ArrayAdapter<String> courseItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityPersonalDetailsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        busRouteItems = new ArrayAdapter<>(this,R.layout.bus_route_item,busRoutes);
        b.autoCompleteBus.setAdapter(busRouteItems);
        b.autoCompleteBus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                busRoute = parent.getItemAtPosition(position).toString();
            }
        });

        courseItems = new ArrayAdapter<>(this,R.layout.course_item,courses);
        b.autoCompleteGraduation.setAdapter(courseItems);
        b.autoCompleteGraduation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                course = parent.getItemAtPosition(position).toString();
            }
        });

        b.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButton();
            }
        });
    }



    private void onSaveButton() {

        //Getting email from RegistrationActivity through intent
        String email = getIntent().getStringExtra("EMAIL_ID");


        String nameInput = b.textEditName.getEditableText().toString().trim();
        if(nameInput.isEmpty()){
            b.textEditName.setError("Name can't be left blank!");
            b.textEditName.requestFocus();
            return;
        }

        if(!nameInput.matches("([A-Z][a-z]*){3,}+[A-Z]+(['-][a-zA-Z]+)*")){
            b.textEditName.setError("Invalid name!");
            b.textEditName.requestFocus();
            return;
        }

        // get selected radio button from radioGroup1
        int radioID1 = b.radioGroup1.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton1 = findViewById(radioID1);
        String radioValue1 = (String) radioButton1.getText();


        // get selected radio button from radioGroup2
        int radioID2 = b.radioGroup2.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton2 = findViewById(radioID2);
        String radioValue2 = (String) radioButton2.getText();

        b.progressBar.setVisibility(View.VISIBLE);

        User user = new User(nameInput,busRoute,radioValue1,radioValue2,course);

        //Saving user's data on Cloud FireStore Database
        FirebaseFirestore.getInstance().collection("Users").document(email).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        b.progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(PersonalDetailsActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(PersonalDetailsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}