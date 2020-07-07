package com.smile.spotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smile.spotapp.controller.AppUtil;
import com.smile.spotapp.controller.TempData;
import com.smile.spotapp.model.Signup_details;

public class Login extends AppCompatActivity {

    TextInputLayout phno,pass;
    TextView forgetpass,signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        phno = findViewById(R.id.login_phno);
        pass = findViewById(R.id.login_pinno);
        forgetpass = findViewById(R.id.login_forgetpass);
        signup = findViewById(R.id.login_signup);
        login = findViewById(R.id.login_loginbtn);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(new TempData(Login.this).getlog()==null){
            Toast.makeText(this, "Welocme To Login", Toast.LENGTH_SHORT).show();
        }else {
            startActivity(new Intent(Login.this , Login_Main.class));finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this , Registration.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phno1 = phno.getEditText().getText().toString();
                final String pinno1 = pass.getEditText().getText().toString();
                if(phno1.length()!=0 && phno1.length()==10){
                    if(pinno1.length()!=0){
                        DatabaseReference df = FirebaseDatabase.getInstance().getReference(AppUtil.REGNAME).child(phno1);
                        df.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue()!=null){
                                    Signup_details s = dataSnapshot.getValue(Signup_details.class);
                                    if(pinno1.equals(s.getPinno())){
                                        TempData t = new TempData(Login.this);
                                        t.adduid(phno1);
                                        t.addlogdetails("login");
                                        startActivity(new Intent(Login.this , Login_Main.class));
                                    }
                                }else {
                                    Toast.makeText(Login.this, "No Account Found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else {
                        Toast.makeText(Login.this, "Please fill details", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this, "Please fill details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
