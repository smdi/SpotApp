package com.smile.spotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smile.spotapp.controller.AppUtil;
import com.smile.spotapp.controller.TempData;
import com.smile.spotapp.model.Signup_details;

public class Login_Main extends AppCompatActivity {

    Toolbar mytoolbar;
    TextView picname,name,email,phno,bsts,blocname,btrack,salert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login__main);

        mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);

        picname = findViewById(R.id.lmain_picname);
        name = findViewById(R.id.lmain_name);
        email = findViewById(R.id.lmain_email);
        phno = findViewById(R.id.lmain_phno);
        bsts = findViewById(R.id.lmain_bsts);
        blocname = findViewById(R.id.lmain_bloc);
        btrack = findViewById(R.id.lmain_btrack);
        salert = findViewById(R.id.lmain_salert);

        bsts.setTextColor(Color.RED);

        DatabaseReference df = FirebaseDatabase.getInstance().getReference(AppUtil.REGNAME).child(new TempData(Login_Main.this).getuid());
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Signup_details s = dataSnapshot.getValue(Signup_details.class);
                    char initial = s.getName().charAt(0);
                    picname.setText(String.valueOf(initial).toUpperCase());
                    name.setText(s.getName());
                    email.setText(s.getEmail());
                    phno.setText(s.getPhno());
                }else {
                    picname.setText(" ");
                    name.setText(" ");
                    email.setText(" ");
                    phno.setText(" ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final String token = SharedPrefManager.getInstance(Login_Main.this).getDeviceToken();
        if(token!=null) {
            DatabaseReference df1 = FirebaseDatabase.getInstance().getReference(AppUtil.REGNAME).child(new TempData(Login_Main.this).getuid());
            df1.child("token").setValue(token);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        btrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Main.this , MapsPage.class));
            }
        });
        salert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login_Main.this, "Successfull send alert", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loginmenu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu_logout){
            final Dialog d = new Dialog(Login_Main.this);
            d.setContentView(R.layout.calertdialog);
            d.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            TextView message = d.findViewById(R.id.calert_message);
            Button yes = d.findViewById(R.id.calert_yes);
            Button no = d.findViewById(R.id.calert_no);

            message.setText("Are You Sure ? Want to Logout ?");
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                    new TempData(Login_Main.this).logout();
                    finish();
                    startActivity(new Intent(Login_Main.this , Login.class));
                    finish();
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });
            d.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        final Dialog d = new Dialog(Login_Main.this);
        d.setContentView(R.layout.calertdialog);
        d.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        TextView message = d.findViewById(R.id.calert_message);
        Button yes = d.findViewById(R.id.calert_yes);
        Button no = d.findViewById(R.id.calert_no);

        message.setText("Are You Sure ? Want to close this app ?");
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();

    }
}
