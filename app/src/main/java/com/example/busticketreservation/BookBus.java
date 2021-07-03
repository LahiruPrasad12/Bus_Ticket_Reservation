package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;

public class BookBus extends AppCompatActivity {

    FirebaseAuth frbAuth;
    FirebaseFirestore fStore;
    private String userId;
    private String routeId;
    private String tripId;
    private int Available ;
    private int numOfSeats = 0,Price;
    DatabaseReference databaseReference;
    private Integer total=0;
    private int PAPAL_REQ_CODE = 12;
    private static PayPalConfiguration payPalConfiguration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PaypalClientIDConfig.PAYPAL_CLIENT_ID);


    TextView price,dropOn,dropOff,available,errMsg,finalBill;
    Button paymentButton;
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

         userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        startService(intent);
        selectBus();

    }

    public void selectBus(){
        Intent intent = getIntent();
         tripId = intent.getStringExtra("tripId");
         routeId = intent.getStringExtra("routeId");
        String amount = intent.getStringExtra("price");



        Query query = FirebaseDatabase.getInstance().getReference("Trips").orderByChild("trip_id").equalTo(tripId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        price.setText("Price : "+amount);
                        dropOn.setText("Drop on : "+snapshot1.child("dep_time").getValue().toString());
                        dropOff.setText("Drop off "+snapshot1.child("ar_time").getValue().toString());
                        available.setText("Available Seats : "+snapshot1.child("AvailableSeats").getValue().toString());
                        Available = Integer.parseInt(snapshot1.child("AvailableSeats").getValue().toString());
                        Price = Integer.parseInt(amount);
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



    public void calcBill(View view){
        finalBill = findViewById(R.id.finalBill);
        try{
            numOfSeats = Integer.parseInt(numSeats.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Please Enter number of seats that you want to book", Toast.LENGTH_SHORT).show();
        }

        total = numOfSeats*Price;
        finalBill.setText("LKR "+String.valueOf(total));
        finalBill.setVisibility(View.VISIBLE);


    }

    public void makePayment(View view){

        try{
            numOfSeats = Integer.parseInt(numSeats.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Please Enter number of seats that you want to book", Toast.LENGTH_SHORT).show();
        }

        if(numOfSeats>Available) {
            errMsg.setVisibility(View.VISIBLE);
        }else {
            errMsg.setVisibility(View.INVISIBLE);
            total = numOfSeats*Price;
            if(total == 0){
                Toast.makeText(this, "Please Enter number of seats that you want to book", Toast.LENGTH_SHORT).show();
            }else {
                PayPalPayment payment = new PayPalPayment(new BigDecimal(total / 180), "USD", "Test Payment", PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(this, PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                startActivityForResult(intent, PAPAL_REQ_CODE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PAPAL_REQ_CODE){
            if(requestCode == Activity.RESULT_OK){


                userBookingHistry userBookingHistry = new userBookingHistry();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Booking_History");

                userBookingHistry.setUserId(userId);
                userBookingHistry.setRouteId(routeId);
                userBookingHistry.setTripId(tripId);
                userBookingHistry.setNumOfseats(numOfSeats);
                userBookingHistry.setToatlAmount(total);
                databaseReference.push().setValue(userBookingHistry);
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this, "Payment Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }
}