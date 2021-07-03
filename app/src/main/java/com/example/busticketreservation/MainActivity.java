package com.example.busticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView name,errMsg;
    String from,to,depTime,arTime;
    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    String useId ;
    public static final String TAG = "TAG";
    String busNo;
    ProgressBar progressBar;
    private int count = 0;
    private int numRetrieve = 0;
    private String mail;
    Button button;

    private DatabaseReference databaseReference;
    private ArrayList<String> BusNo = new ArrayList<>();
    private ArrayList<String> Location = new ArrayList<>();
    private ArrayList<String> Time = new ArrayList<>();
    private Context mContext;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button7);
        errMsg = findViewById(R.id.vmsg);


        frbAuth = FirebaseAuth.getInstance();
        useId = frbAuth.getCurrentUser().getUid();
        FirebaseUser firebaseUser = frbAuth.getCurrentUser();

        if(firebaseUser.isEmailVerified()){
            button.setVisibility(View.INVISIBLE);
            errMsg.setVisibility(View.INVISIBLE);
        }


        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }else {


            drawerLayout = findViewById(R.id.tx1111);
            toolbar = findViewById(R.id.toolBar);
            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            navigationView = findViewById(R.id.nav_view);


            //Navigation  Bar
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int id = menuItem.getItemId();
                    Fragment fragment = null;
                    switch (id) {
                        case R.id.Trips:

                            break;
                        case R.id.wallet:

                            break;
                        case R.id.Helps:

                            break;

                        case R.id.setting:
                            startActivity(new Intent(getApplicationContext(), ViewUserProfile.class));
                            break;
                        case R.id.logOut:
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            break;
                        default:
                            return true;
                    }
                    return true;
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        } else{

            name = findViewById(R.id.name);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(useId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("Name").getValue().toString());
                mail = snapshot.child("Mail").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }


    public void findRouteNumber(View view){
        startActivity(new Intent(this,FindRouteNumber.class));
    }


    public void gotoFindBus(View view){
        Intent intent = new Intent(this,FindBus.class);
        intent.putExtra("routeNo","0");
        startActivity(intent);
    }


    public void gotoChooseSavedPlace(View view){
        Intent intent = new Intent(this,ViewSavedPlace.class);
        startActivity(intent);
    }


    public void sendVerificationMail(View view){
        FirebaseUser firebaseUser = frbAuth.getCurrentUser();
        firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Verification mail has been sent", Toast.LENGTH_SHORT).show();
               
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    //    private void loadFragment(Fragment fragment) {
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame,fragment).commit();
//        drawerLayout.closeDrawer(GravityCompat.START);
//        fragmentTransaction.addToBackStack(null);
//    }
//
//
//    public void FindBus(View view) {
//        From = findViewById(R.id.From);
//        To = findViewById(R.id.To);
//        progressBar = findViewById(R.id.progressBar);
//
//
//        progressBar.setVisibility(View.VISIBLE);
//        frbAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//        useId = frbAuth.getCurrentUser().getUid();
//        from = From.getText().toString();
//        to = To.getText().toString();
//
//
//        if(from.length()==0){
//            Toast.makeText(this, "Please Enter Your Location", Toast.LENGTH_SHORT).show();
//            progressBar.setVisibility(View.INVISIBLE);
//        }else if(to.length()==0){
//            Toast.makeText(this, "Please Enter Finding Location", Toast.LENGTH_SHORT).show();
//            progressBar.setVisibility(View.INVISIBLE);
//        }else{
//
//            //Retrieve Data Using From and To place
//            Query query = FirebaseDatabase.getInstance().getReference("Routes").
//                    orderByChild("from").equalTo(from);
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.exists()){
//
//                        progressBar.setVisibility(View.INVISIBLE);
//
//                        //Check whether have 1 bus
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            if(dataSnapshot.child("to").getValue().toString().equals(to)){
//                                count++;
//                            }
//                        }
//
//                        if(count>=1){
//                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                                TextView textView = new TextView(MainActivity.this);
//                                Button button = new Button(getApplicationContext());
//                                progressBar.setVisibility(View.INVISIBLE);
//                                busNo = dataSnapshot.child("bus_No").getValue().toString();
//                                depTime = dataSnapshot.child("departure_Time").getValue().toString();
//                                arTime = dataSnapshot.child("arrival_Time").getValue().toString();
//                                BusNo.add(busNo);
//                                Time.add(arTime +" - "+depTime);
//                                Location.add(from + " - "+to);
//                                initRecyclerView();
//                            }
//                        }else {
//                            Toast.makeText(MainActivity.this, "Can't Find Location", Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        Toast.makeText(getApplicationContext(), "Can't Find Location", Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.INVISIBLE);
//                    }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.INVISIBLE);
//                }
//            });
//
//        }
//










//        fStore.collection("Routes").whereEqualTo("From", from).whereEqualTo("To", to).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    if(task.getResult().isEmpty() ){
//                        progressBar.setVisibility(View.INVISIBLE);
//                        Toast.makeText(MainActivity.this, "Can't Find Value", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//
//
//                        TextView textView = new TextView(MainActivity.this);
//                        Button button = new Button(getApplicationContext());
//                        progressBar.setVisibility(View.INVISIBLE);
//                        busNo = document.getString("Bus_No");
//                        depTime = document.getString("Departure_Time");
//                        arTime = document.getString("Arrival_Time");
//                        BusNo.add(busNo);
//                        Time.add(arTime +" - "+depTime);
//                        Location.add(from + " - "+to);
//                        initRecyclerView();
//
//                    }
//
//                } else {
//                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//        });


}


//    //Invoke Bus View Adapter
//    private void initRecyclerView(){
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        BusViewAdapter adapter = new BusViewAdapter(BusNo,Location,Time,this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
