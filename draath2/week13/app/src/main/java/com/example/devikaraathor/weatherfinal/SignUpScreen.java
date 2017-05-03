package com.example.devikaraathor.weatherfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEdit;
    private EditText passwordEdit;
    private Button register;
    private TextView signUp;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        emailEdit = (EditText) findViewById(R.id.mail);
        passwordEdit = (EditText) findViewById(R.id.passwordText);
        register = (Button) findViewById(R.id.registerButton);
        signUp = (TextView) findViewById(R.id.signUpText);

        register.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private void registerUser() {
        String email = emailEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            //stop executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            //stop executing further
            return;
        }

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpScreen.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                } else {
                    Toast.makeText(SignUpScreen.this, "Could not register...please try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == register) {
            registerUser();
        }
        if (v == signUp) {
            //open login activity
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
