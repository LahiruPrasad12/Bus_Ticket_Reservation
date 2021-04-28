package com.example.busticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth frb;
    ProgressBar progressBar;
    EditText txtMail,txtPassword;
    String mail,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frb = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pBar);
        txtMail = findViewById(R.id.txNam);
        txtPassword = findViewById(R.id.txtP);

        progressBar.setVisibility(View.GONE);

        if(frb.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }

    public void register(View view){
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }


    public void Login(View view){
        progressBar.setVisibility(View.VISIBLE);

        mail = txtMail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Name Is Required", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Phone Is Required", Toast.LENGTH_SHORT).show();
        }else {


            //Authenticated User
            frb.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

//
//    public void signOut(View view){
//        FirebaseAuth.getInstance().signOut();
//    }

}