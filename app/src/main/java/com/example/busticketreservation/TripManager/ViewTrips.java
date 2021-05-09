package com.example.busticketreservation.TripManager;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busticketreservation.R;
import com.example.busticketreservation.ViewUserProfile;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewTrips extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<TripsModelClass>tripList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trips);

        iniData();
        iniRecyclerView();


    }

    private void iniData() {
        tripList = new ArrayList<>();

        tripList.add(new TripsModelClass(002,"09/05/2020","14.30","20.30","ZA5563"));
        tripList.add(new TripsModelClass(002,"09/05/2020","14.30","20.30","ZA5563"));
        tripList.add(new TripsModelClass(002,"09/05/2020","14.30","20.30","ZA5563"));
    }

    private void iniRecyclerView() {

        recyclerView = findViewById(R.id.view_trips_recycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(tripList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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



