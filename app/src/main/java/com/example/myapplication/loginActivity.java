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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText textemail;
    private EditText textpass;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            //directly Start next activity(PROFILE ACTIVITY)
            finish();
            startActivity(new Intent(getApplicationContext(),profileActivity.class));
        }

        textemail = (EditText)findViewById(R.id.textemail);
        textpass = (EditText)findViewById(R.id.textpass);
        loginButton = (Button)findViewById(R.id.loginbutton);
        textViewSignUp = (TextView)findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        loginButton.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);


    }


    private void userLogin() {
        String email = textemail.getText().toString().trim();
        String password = textpass.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //Email is empty
            Toast.makeText(this, "Please Enter Email Id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            //start the next activity(PROFILE ACTIVITY)
                            finish();
                            startActivity(new Intent(getApplicationContext(),profileActivity.class));
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {

        if (view == loginButton){
            userLogin();
        }

        if (view == textViewSignUp){

            finish();
            startActivity(new Intent(this,loginActivity.class));
        }

    }
}
