package com.example.busticketreservation.BusOwner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.busticketreservation.Admin.Routes;
import com.example.busticketreservation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddBus extends AppCompatActivity {

    EditText txtID,txtRoute,txtLicense,txtSeats;
    Button addBus;
    DatabaseReference dbref;
    Bus bs;
    Spinner myspinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
//    ArrayList<String> spinnerDataList;
    ArrayList<Routes> list;

    ArrayList<String> stringArrayList = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        txtID = findViewById(R.id.pt1);
        txtRoute = findViewById(R.id.pt2);
        txtLicense = findViewById(R.id.pt3);
        txtSeats = findViewById(R.id.pt4);

        addBus = findViewById(R.id.bt1);
        bs = new Bus();


        //getting data from route table
        dbref = FirebaseDatabase.getInstance().getReference("Routes_Admin");

        list = new ArrayList<>();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Routes routes = dataSnapshot.getValue(Routes.class);
                    list.add(routes);
                }
                for(int x=0; x<list.size(); x++){
                    System.out.println(list.get(x).getRouteNo());

                    stringArrayList.add(list.get(x).getRouteNo());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // auto created
            }
        });

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stringArrayList);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);



        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Bus");

                try {
                    if (TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid ID",Toast.LENGTH_SHORT).show();

                   else if(TextUtils.isEmpty(txtRoute.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid Route",Toast.LENGTH_SHORT).show();

                  else  if (TextUtils.isEmpty(txtLicense.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid License Number",Toast.LENGTH_SHORT).show();

                 else   if(TextUtils.isEmpty(txtSeats.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter a valid Number of Seats",Toast.LENGTH_SHORT).show();

                    else {
                        bs.setID(txtID.getText().toString().trim());
                        bs.setRouteNo(txtRoute.getText().toString().trim());
                        bs.setLicenseNo(txtLicense.getText().toString().trim());
                        bs.setSeats(Integer.parseInt(txtSeats.getText().toString().trim()));

                        dbref.push().setValue(bs);
                        Toast.makeText(getApplicationContext(),"Bus added Successfully",Toast.LENGTH_SHORT).show();

                        clearControls();
                    }



                }
                catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"Please Enter a Number In here",Toast.LENGTH_SHORT).show();

                }

            }
        });





    }

    private void clearControls(){
        txtID.setText("");
        txtRoute.setText("");
        txtLicense.setText("");
        txtSeats.setText("");




    }
//    public void retriveData(){
//
//        listener = dbref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot item:dataSnapshot.getChildren()){
//
//
//                    spinnerDataList.add(item.getValue().toString());
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }


    public void View_Bus(View view){
        startActivity(new Intent(getApplicationContext(),ViewBus.class));
    }
}