package com.example.bus_e_no;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bus_e_no.databinding.ActivityPersonalDetailsBinding;

public class PersonalDetailsActivity extends AppCompatActivity {

    ActivityPersonalDetailsBinding b;
    String[] courses = {"B.Com.","B.Sc.Agriculture","B.Sc.Biotech","B.Sc.Hospitality & Hotel Mgmt.","B.Sc.Microbiology",
                        "B.Sc.PCM","B.of Hotel Mgmt.","B.A. J&MC","B.A. Other","B.Tech. Civil","B.Tech. CSE","B.Tech. ECE",
                        "B.Tech. EE","B.Tech. ME","BBA","BCA","Design Courses","Law","M.Sc. Agriculture","M.Sc. Biotech","M.Sc. CS",
                        "M.Sc. Microbiology","M.Sc. PCM","M.A. J&MC","M.Tech. Civil","M.Tech. CSE","M.Tech. ECE","M.Tech. EE",
                        "M.Tech. ME","MBA","MCA","PG Diploma"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityPersonalDetailsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

//        b.autoCompleteCourse
    }
}