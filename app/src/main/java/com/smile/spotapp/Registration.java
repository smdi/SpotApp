package com.smile.spotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.spotapp.controller.AppUtil;
import com.smile.spotapp.model.Signup_details;

public class Registration extends AppCompatActivity {

    TextInputLayout name,phno,email,pass;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        name = findViewById(R.id.signup_name);
        phno = findViewById(R.id.signup_phno);
        email = findViewById(R.id.signup_email);
        pass = findViewById(R.id.signup_pinno);
        submit = findViewById(R.id.signup_sumbitbtn);

    }

    @Override
    protected void onResume() {
        super.onResume();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getEditText().getText().toString();
                String phno1 = phno.getEditText().getText().toString();
                String email1 = email.getEditText().getText().toString();
                String pinno1 = pass.getEditText().getText().toString();

                if(name1.length()!=0){
                    if(phno1.length()!=0 && phno1.length()==10){
                        if(email1.length()!=0){
                            if(pinno1.length()!=0 && pinno1.length()>=4){
                                Signup_details s = new Signup_details(name1,email1,phno1,pinno1);
                                DatabaseReference df = FirebaseDatabase.getInstance().getReference(AppUtil.REGNAME);
                                df.child(phno1).setValue(s);
                                Toast.makeText(Registration.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(Registration.this, "fill pinn number minimum 4 digits", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Registration.this, "fill email first", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Registration.this, "fill phone number first", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Registration.this, "fill name first", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
