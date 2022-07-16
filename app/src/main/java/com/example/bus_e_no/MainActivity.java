package com.example.bus_e_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.bus_e_no.databinding.ActivityMainBinding;
import com.example.bus_e_no.model.Model;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

//    private static final int LOCATION_REQUEST_CODE = 100001;
    ActivityMainBinding b;
    ActionBarDrawerToggle toggle;

    /**
     * To use Google Play Services Location API, we need to use FusedLocationProviderClient
     */
//    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


/**        To tell the Activity that the ActionBar coming on this Activity
 *          isn't the default Theme ActionBar but its the custom Toolbar
*          that we have made for this activity
 */
            setSupportActionBar(b.appBar);


        /** drawer layout instance to toggle the menu icon to open drawer
         * and back button to close drawer
         */
        toggle = new ActionBarDrawerToggle(this,b.drawerLayout,R.string.open,R.string.close);
        toggle.syncState();


        b.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.editProfile:
                        Intent edit = new Intent(MainActivity.this,PersonalDetailsActivity.class);
                        startActivity(edit);
                        return true;

                    case R.id.about:
                        Intent about = new Intent(MainActivity.this,AboutActivity.class);
                        startActivity(about);
                        return true;

                        case R.id.share:
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        String body = "Hey! Download Bus-e-No app of our college to have all details about your bus";
                        share.putExtra(Intent.EXTRA_TEXT,body);
                        startActivity(Intent.createChooser(share,"Share Using"));
                        return true;

                    case R.id.contact:
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback/Suggestions/Queries/Bug Reporting Bus-e-No ");
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        return true;
                }
                return true;
            }
        });

        b.allBuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AllBusActivity.class);
                startActivity(i);
            }
        });

        showData();
    }


    /**
     * To know whether the Location Permission is granted to us by the user or not
     */
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager
//                .PERMISSION_GRANTED) {
//            getLastLocation();
//        } else {
//            askLocationPermission();
//        }
//    }
//
//    private void askLocationPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
//                .PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
//                    .ACCESS_FINE_LOCATION)) {
//
//                //SHOW AN ALERT DIALOG AND REQUEST FOR THE PERMISSION
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        LOCATION_REQUEST_CODE);
//            } else {
//
//                //IF ITS THE FIRST TIME THAT USER IS USING THE APP THEN DIRECTLY ASK FOR THE PERMISSION
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        LOCATION_REQUEST_CODE);
//            }
//        }
//    }
//
//    private void getLastLocation() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
//
//        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if(location != null){
//                    //We have a location
//                    Log.d("MainActivity","onSuccess: "+location.toString());
//                    Log.d("MainActivity","onSuccess: "+location.getLatitude());
//                    Log.d("MainActivity","onSuccess: "+location.getLongitude());
//                } else{
//                    Log.d("MainActivity","onSuccess: Location was null...");
//                }
//            }
//        });
//
//        locationTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.e("MainActivity ","onFailure: "+e.getLocalizedMessage());
//            }
//        });
//    }


    /**
     * To know whether the user has granted Permission access, we need
     * to override onRequestPermissionsResult method
     */

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == LOCATION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                //Permission granted
//                getLastLocation();
//            } else {
//
//                //Permission not granted
//
//            }
//        }
//    }


/**    Override the [onOptionsItemSelected()]
*     to implement the item click listener
*     callback to open and close the navigation
*     drawer when the navigation/hamburger icon is clicked
 */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                b.drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.bellIcon:
                Intent i = new Intent(MainActivity.this,NotificationsActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //To have items to the right of Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void showData() {
        FirebaseFirestore.getInstance().collection("DriverData").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot document : task.getResult()) {

                        }
                    }
                });
    }
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//
//    }
}