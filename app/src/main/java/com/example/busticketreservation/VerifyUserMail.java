package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyUserMail extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_user_mail);
        getSupportActionBar().setTitle("Verify Mail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = findViewById(R.id.verify);
        button2 = findViewById(R.id.button8);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


    }


    @Override
    protected void onResume() {
        super.onResume();

        if(firebaseUser.isEmailVerified()){
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    public void sendVerificationMail(View view){

        firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(VerifyUserMail.this, "Verification mail has been sent", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VerifyUserMail.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void refresh(View view){
        startActivity(new Intent (getApplicationContext(),VerifyUserMail.class));
    }
}