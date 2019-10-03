package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        /*if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(this,MainActivity.class);
        }*/

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView)findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome"+user.getEmail());

        buttonLogOut = (Button)findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}
