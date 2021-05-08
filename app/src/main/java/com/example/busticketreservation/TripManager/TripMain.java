//package com.example.busticketreservation.TripManager;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//
//import com.example.busticketreservation.R;
//import com.google.android.material.navigation.NavigationView;
//
//
//public class TripMain extends AppCompatActivity {
//
//    private DrawerLayout drawer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_trips);
//
////      using the toolbar as the action bar
//        Toolbar toolbar = findViewById(R.id.pamo_toolbar);
//        setSupportActionBar(toolbar);
//
////      get the menu button in the top left corner
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//    }
//
//    public void ClickViewTrips(View view){
//        //Redirect activity to add trips
////        redirectActivity(this,AddTrips.class);
//        startActivity(new Intent(getApplicationContext(),AddTrips.class));
//    }
//
//    public void ClickAddTrips(View view){
//        //Redirect activity to select routes
//        //redirectActivity(this,SelectRoutesTm.class);
//    }
//
//    private static void redirectActivity(Activity activity, Class aClass) {
//        //Initialize intent
//        Intent intent = new Intent(activity,aClass);
//        //Set flag
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //Start activity
//        activity.startActivity(intent);
//
//    }
//
//
//}
//
//
//
//
//
