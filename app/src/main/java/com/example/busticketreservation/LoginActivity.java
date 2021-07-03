package com.example.busticketreservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.busticketreservation.Admin.AllRoutes;
import com.example.busticketreservation.BusOwner.AddBus;
import com.example.busticketreservation.TripManager.TripMain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth frb;
    ProgressBar progressBar;
    EditText txtMail,txtPassword;
    String mail,password;
    DatabaseReference databaseReference;
    private String mail1,mail2,useId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frb = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pBar);
        txtMail = findViewById(R.id.routeNumber);
        txtPassword = findViewById(R.id.numOfSeats);
        progressBar.setVisibility(View.INVISIBLE);

       Intent intent = getIntent();
       mail2 = intent.getStringExtra("userId");

        if(frb.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),VerifyUserMail.class));
            finish();
        }

    }



    //if Don't have account redirect to register page
    public void register(View view){
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }




    //Login
    public void Login(View view){
        progressBar.setVisibility(View.VISIBLE);

        mail = txtMail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Name Is Required", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password Is Required", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }else {
            //Authenticated User
            frb.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String useId = frb.getCurrentUser().getUid();
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(useId);

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                //assigning role to a variable
                                String role = snapshot.child("Roll").getValue().toString();
                                //Customer Login
                                if(role.equals("Customer")){
                                    Toast.makeText(LoginActivity.this, "Passenger Login Success!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),VerifyUserMail.class));
                                }

                                else if(role.equals("Admin")){
                                    Toast.makeText(LoginActivity.this, "Admin Login Success!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), AllRoutes.class));
                                }

                                else if(role.equals("Bus Owner")){
                                    Toast.makeText(LoginActivity.this, "Bus Owner Login Success!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), AddBus.class));
                                }

                                else if(role.equals("Trip Manager")){
                                    Toast.makeText(LoginActivity.this, "Trip Manager Login Success!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), TripMain.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }else {
                        Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
            });
        }
    }


//
//    public void signOut(View view){
//        FirebaseAuth.getInstance().signOut();
//    }

    public void forgotPassword(View view){

        progressBar.setVisibility(View.INVISIBLE);
        EditText resetMail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password ?");
        passwordResetDialog.setMessage("Enter email to received reset link");
        passwordResetDialog.setView(resetMail);


        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mail1 = resetMail.getText().toString();
                    frb.sendPasswordResetEmail(mail1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(LoginActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });

        passwordResetDialog.setNegativeButton("not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        passwordResetDialog.create().show();

    }

}