package com.example.bus_e_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.bus_e_no.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {
    ActivityRegistrationBinding b;
    String busRoute;

    String[] busRoutes = {"Route-01", "Route-02", "Route-03", "Route-04", "Route-05", "Route-06", "Route-07",
            "Route-08", "Route-09", "Route-10", "Route-11", "Route-12", "Route-13", "Route-14", "Route-15",
            "Route-16", "Route-17", "Route-18", "Route-19", "Route-20", "Route-21", "Route-22", "Route-23",
            "Route-24", "Route-25", "Route-26", "Route-27", "Route-28", "Route-29", "Route-30"};

    ArrayAdapter<String> busRouteItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());


        busRouteItems = new ArrayAdapter<>(this, R.layout.bus_route_item, busRoutes);
        b.autoCompleteBus.setAdapter(busRouteItems);
        b.autoCompleteBus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                busRoute = parent.getItemAtPosition(position).toString();
            }
        });


        b.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton();
            }
        });
    }

    public void registerButton() {
        String nameInput = b.textEditName.getEditableText().toString().trim();
        String emailInput = b.textEditEmail.getEditableText().toString().trim();
        String phoneInput = b.textEditPhn.getEditableText().toString().trim();
        String selectedBusRoute = busRoute;

        if (nameInput.isEmpty()) {
            b.textEditName.setError("Name can't be left blank!");
            b.textEditName.requestFocus();
            return;
        }

        if (emailInput.isEmpty()) {
            b.textEditEmail.setError("Email is required!");
            b.textEditEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            b.textEditEmail.setError("Invalid Email Address");
            b.textEditEmail.requestFocus();
            return;
        }

        if (phoneInput.isEmpty()) {
            b.textEditPhn.setError("Phone Number is required!");
            b.textEditPhn.requestFocus();
            return;
        }

        if (phoneInput.length() != 10) {
            b.textEditPhn.setError("Invalid Phone Number!");
            b.textEditPhn.requestFocus();
            return;
        }

        b.progressBar.setVisibility(View.VISIBLE);

        RegisteredUser regUser = new RegisteredUser(nameInput, emailInput, selectedBusRoute);


        /** Checking if the user already exists */
        DocumentReference docIdRef = FirebaseFirestore.getInstance().collection("Users").document(phoneInput);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        b.progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegistrationActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        b.textEditName.getText().clear();
                        b.textEditEmail.getText().clear();
                        b.textEditPhn.getText().clear();
                        b.autoCompleteBus.setText(null);
                        return;
                    } else {

                        //Saving user's data on Cloud FireStore
                        FirebaseFirestore.getInstance().collection("Users").document(phoneInput).set(regUser)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        b.progressBar.setVisibility(View.GONE);
                                        Toast.makeText(RegistrationActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        b.progressBar.setVisibility(View.GONE);
                                        Toast.makeText(RegistrationActivity.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            }
        });

    }
}