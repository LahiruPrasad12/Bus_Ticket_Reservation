package com.example.busticketreservation.TripManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.busticketreservation.R;

public class ViewTrips extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trips);

//      using the toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.pamo_toolbar);
        setSupportActionBar(toolbar);

//      get the menu button in the top left corner
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }

}





















//    public void ClickMenu(View view){
//        //open drawer
//        openDrawer(drawerLayout );
//    }
//
//    private static void openDrawer(DrawerLayout drawerLayout) {
//        //open drawer layout
//        drawerLayout.openDrawer(GravityCompat.START);
//    }
//
//    public void ClickLogo(View view){
//        //close drawer
//        CloseDrawer(drawerLayout);
//    }
//
//    private static void CloseDrawer(DrawerLayout drawerLayout) {
//        //close drawer layout
//        //check condition
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            //when drawer is open
//            //close drawer
//            drawerLayout.closeDrawer(GravityCompat.START);
//
//
//        }
//    }
//
//    public void CliCkViewTrips (View view){
//        //Recreate activity
//        recreate();
//    }
//
//
//    public void ClickAddTrip (View view){
//        //redirect activity to add trip
//        redirectActivity(this,ViewTrips.class);
//    }
//
//
//
//    private static void redirectActivity(Activity activity, Class aClass) {
//        //initialize intent
//        Intent intent = new Intent(activity,aClass);
//        //set flag
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //start activity
//        activity.startActivity(intent);
//    }
//
////    @Override
////    protected void onPause() {
////        super.onPause();
////        //close drawer
////        closeDrawer(drawerLayout);
////    }



