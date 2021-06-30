package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookBus extends AppCompatActivity {

    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    private int Available ;
    private int numOfSeats = 0,Price;
    DatabaseReference databaseReference;


    TextView price,dropOn,dropOff,available,errMsg,finalBill;
    EditText numSeats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus);
        price = findViewById(R.id.tripId);
        dropOn = findViewById(R.id.dropOn);
        dropOff = findViewById(R.id.dropOff);
        available = findViewById(R.id.available);
        errMsg = findViewById(R.id.errMsg);
        numSeats = findViewById(R.id.numOfSeats);

        Intent intent = getIntent();
        String tripId = intent.getStringExtra("tripId");


        Query query = FirebaseDatabase.getInstance().getReference("Trips").orderByChild("trip_id").equalTo(tripId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        price.setText("Price : "+snapshot1.child("price").getValue().toString());
                        dropOn.setText("Drop on : "+snapshot1.child("dep_time").getValue().toString());
                        dropOff.setText("Drop off "+snapshot1.child("ar_time").getValue().toString());
                        available.setText("Available Seats : "+snapshot1.child("AvailableSeats").getValue().toString());
                        Available = Integer.parseInt(snapshot1.child("AvailableSeats").getValue().toString());
                        Price = Integer.parseInt(snapshot1.child("price").getValue().toString());
                    }

                }else {
                    Toast.makeText(BookBus.this, "Invalid Trip Id", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BookBus.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goToPayment(View view){
        try{
            numOfSeats = Integer.parseInt(numSeats.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Please Enter number of seats that you want to book", Toast.LENGTH_SHORT).show();
        }

        if(numOfSeats>Available) {
            errMsg.setVisibility(View.VISIBLE);
        }else {
            errMsg.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void calcBill(View view){
        finalBill = findViewById(R.id.finalBill);
        try{
            numOfSeats = Integer.parseInt(numSeats.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Please Enter number of seats that you want to book", Toast.LENGTH_SHORT).show();
        }

        Integer total = numOfSeats*Price;
        finalBill.setText("LKR "+String.valueOf(total));
        finalBill.setVisibility(View.VISIBLE);


    }
}