package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button regbutton;
    private EditText textemail;
    private EditText textpass;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            //directly Start next activity(PROFILE ACTIVITY)
            finish();
            startActivity(new Intent(getApplicationContext(),profileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        regbutton = (Button) findViewById(R.id.regbutton);
        textemail = (EditText) findViewById(R.id.textemail);
        textpass = (EditText) findViewById(R.id.textpass);

        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        regbutton.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);


    }

    private void registerUser(){
        String email = textemail.getText().toString().trim();
        String password = textpass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //Email is empty
            Toast.makeText(this,"Please Enter Email Id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //User is succesfully registered
                            //just adding a Toast temporarily


                                //directly Start next activity(PROFILE ACTIVITY)
                                finish();
                                startActivity(new Intent(getApplicationContext(),profileActivity.class));
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "FAILED to Register", Toast.LENGTH_SHORT).show();
                        }
                    }});
    }


    @Override
    public void onClick(View view) {
        if (view == regbutton){
            registerUser();
        }
        if (view == textViewSignIn){
            //will open sign in activity
            startActivity(new Intent(this,loginActivity.class));
        }


    }
}
