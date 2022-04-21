package com.example.bus_e_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.bus_e_no.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {
    ActivityRegistrationBinding b;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Access a Cloud Firestore instance from your Activity
        fStore = FirebaseFirestore.getInstance();

        b.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton();
            }
        });
    }

    public void registerButton()
    {
        String emailInput = b.textEditEmail.getEditableText().toString().trim();
        String passwordInput = b.textEditPassword.getEditableText().toString().trim();
        String confirmPasswordInput = b.textEditConfirmPassword.getEditableText().toString().trim();

        if(emailInput.isEmpty()){
            b.textEditEmail.setError("Email is required!");
            b.textEditEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            b.textEditEmail.setError("Invalid Email Address");
            b.textEditEmail.requestFocus();
            return;
        }

        if(passwordInput.isEmpty()){
            b.textEditPassword.setError("Password is required!");
            b.textEditPassword.requestFocus();
            return;
        }

        if(passwordInput.length()<6){
            b.textEditPassword.setError("Minimum 6 Character Password");
            b.textEditPassword.requestFocus();
            return;
        }

        if(confirmPasswordInput.isEmpty()){
            b.textEditConfirmPassword.setError("Password is required!");
            b.textEditConfirmPassword.requestFocus();
            return;
        }

        if(!confirmPasswordInput.matches(passwordInput)){
            b.textEditConfirmPassword.setError("Passwords do not match!");
            b.textEditConfirmPassword.requestFocus();
            return;
        }

        b.progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(emailInput,passwordInput)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //For registration of user
                            if (task.isSuccessful()) {
                                User user = new User(emailInput);

                                //Saving user's data on Cloud FireStore Database
                                fStore.collection("Users").add(user)
                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                b.progressBar.setVisibility(View.GONE);
                                                Intent intent = new Intent(RegistrationActivity.this, PersonalDetailsActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(RegistrationActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegistrationActivity.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                        b.progressBar.setVisibility(View.GONE);
                                    }
                                });
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                b.progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }

    }
