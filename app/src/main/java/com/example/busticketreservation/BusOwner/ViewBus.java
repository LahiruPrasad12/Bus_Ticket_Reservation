//package com.example.busticketreservation.BusOwner;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.cardview.widget.CardView;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.busticketreservation.Admin.MyAdapter;
//import com.example.busticketreservation.Admin.Routes;
//import com.example.busticketreservation.Admin.ViewRoute;
//import com.example.busticketreservation.R;
//import com.example.busticketreservation.ViewUserProfile;
//import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//
//import java.util.ArrayList;
//
//public class ViewBus extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//    private DrawerLayout drawer;
//
//    //new start
//    RecyclerView recyclerView;
//    DatabaseReference database;
//    ArrayList<Routes> list;
//
//    //click listener for card items
//    private com.example.busticketreservation.BusOwner.MyAdapter.RecyclerViewClickListener listener;
//    //new over
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all_routes);
//
//////      using the toolbar as the action bar
////        Toolbar toolbar = findViewById(R.id.hash_toolbar);
////        setSupportActionBar(toolbar);
//
////        getting the drawer layout
//        drawer = findViewById(R.id.hash_drawer_layout);
//
//        //        listen to click events of the navigation view
//        NavigationView navigationView = findViewById(R.id.hash_nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//
////        get the menu button in the top left corner
////        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
////                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
////        drawer.addDrawerListener(toggle);
////        toggle.syncState();
//
//        //new start
//
//        //creating onclick listener to item card
////        setOnClickListener();
//        recyclerView = findViewById(R.id.routeList);
//        database = FirebaseDatabase.getInstance().getReference("Bus");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        list = new ArrayList<>();
//        com.example.busticketreservation.BusOwner.MyAdapter myAdapter = new com.example.busticketreservation.BusOwner.MyAdapter(this, list, listener);
//        recyclerView.setAdapter(myAdapter);
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//                    Routes routes = dataSnapshot.getValue(Routes.class);
//                    list.add(routes);
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        //new over
//
//    }
//
////    private void setOnClickListener() {
////        listener = new MyAdapter.RecyclerViewClickListener() {
////            @Override
////            public void onClick(View v, int position) {
////
////                Intent intent = new Intent(getApplicationContext(), ViewRoute.class);
////                intent.putExtra("routeObj", list.get(position));
////                startActivity(intent);
////
//////                Intent intent = new Intent(getApplicationContext(), ViewRoute.class);
//////                intent.putExtra("routeNo", list.get(position).getRouteNo());
//////                startActivity(intent);
////            }
////        };
////    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }
//}