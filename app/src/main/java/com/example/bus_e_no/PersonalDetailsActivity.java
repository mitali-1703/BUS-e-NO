package com.example.bus_e_no;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.bus_e_no.databinding.ActivityPersonalDetailsBinding;

public class PersonalDetailsActivity extends AppCompatActivity {

    ActivityPersonalDetailsBinding b;
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

        courseItems = new ArrayAdapter<>(this,R.layout.course_item,courses);
        b.autoCompleteGraduation.setAdapter(courseItems);

        b.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButton();
            }
        });
    }

    private void onSaveButton() {
        String nameInput = b.textEditName.getEditableText().toString().trim();
        if(nameInput.isEmpty()){
            b.textEditName.setError("Name can't be left blank!");
            b.textEditName.requestFocus();
            return;
        }

        int radioID1 = b.radioGroup1.getCheckedRadioButtonId();
        int radioID2 = b.radioGroup2.getCheckedRadioButtonId();
        b.progressBar.setVisibility(View.VISIBLE);
    }


}