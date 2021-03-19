package com.example.firebaseexempleauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistrarUsuari extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mRegistrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuari);

        mEmail= findViewById(R.id.ET_Email);
        mPassword = findViewById(R.id.ET_Password);


        mRegistrar=  findViewById(R.id.BTN_RegistrarUsuari);

        mAuth = FirebaseAuth.getInstance();
        mRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRegistrar.setText("Hola");
                String email  =mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mEmail.setError("Error format email.");
                    mEmail.setFocusable(true);
                }
                    else if(password.length()<6){

                        mPassword.setError("Password massa curt");
                        mPassword.setFocusable(true);
                    }else{
                        RegistrarUsuari(email, password);
                }
                }

        });
    }

    private void RegistrarUsuari(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password);
    }
}