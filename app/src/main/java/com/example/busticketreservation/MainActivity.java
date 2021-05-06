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
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
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
    TextView From,To,tx3;
    String from,to,depTime,arTime;
    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    String useId ;
    public static final String TAG = "TAG";
    String busNo;
    ProgressBar progressBar;

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
        drawerLayout=findViewById(R.id.tx1111);
        toolbar=findViewById(R.id.toolBar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);

        //Navigation  Bar
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.Trips:

                        break;
                    case R.id.wallet:

                        break;
                    case R.id.Helps:

                        break;

                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(),ViewUserProfile.class));

                        break;
                    case R.id.logOut:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        break;
                    default:
                        return true;
                }
                return true;
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


    public void FindBus(View view) {
        From = findViewById(R.id.From);
        To = findViewById(R.id.To);
        progressBar = findViewById(R.id.progressBar);


        progressBar.setVisibility(View.VISIBLE);
        frbAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        useId = frbAuth.getCurrentUser().getUid();
        from = From.getText().toString();
        to = To.getText().toString();



        //Retrieve Data Using From and To place
        Query query = FirebaseDatabase.getInstance().getReference("Users").
                orderByChild("Name").equalTo("Hashen");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);

                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            TextView textView = new TextView(MainActivity.this);
                            Button button = new Button(getApplicationContext());
                            progressBar.setVisibility(View.INVISIBLE);
                            busNo = dataSnapshot.child("Name").getValue().toString();
                            depTime = dataSnapshot.child("Name").getValue().toString();
                            arTime = dataSnapshot.child("Name").getValue().toString();
                            BusNo.add(busNo);
                            Time.add(arTime +" - "+depTime);
                            Location.add(from + " - "+to);
                            initRecyclerView();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Can't", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });









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


    //Invoke Bus View Adapter
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        BusViewAdapter adapter = new BusViewAdapter(BusNo,Location,Time,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}