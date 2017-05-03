package com.example.devikaraathor.weatherfinal;
/**
 * @author draath2
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signUp;
    private Button logIn;
    private EditText userN;
    private EditText passW;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        signUp = (Button) findViewById(R.id.signUpButton);
        logIn = (Button) findViewById(R.id.loginButton);
        userN = (EditText) findViewById(R.id.userNameInfo);
        passW = (EditText) findViewById(R.id.passwordInfo);

        progressDialog = new ProgressDialog(this);

        signUp.setOnClickListener(this);
        logIn.setOnClickListener(this);

    }

    private void userLogIn() {
        String email = userN.getText().toString().trim();
        String password = passW.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            //stop function from executing further
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            //stop function from executing further
            return;
        }

        //show progressbar if validation is okay

        progressDialog.setMessage("Signing in user...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    //start login activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                } else {
                    Toast.makeText(MainActivity.this, "Could not login... Please try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == logIn) {
            finish();
            startActivity(new Intent(this, HomeScreen.class));
            //userLogIn();
        }
        if (v == signUp) {
            finish();
            startActivity(new Intent(this, SignUpScreen.class));
        }

    }
}
