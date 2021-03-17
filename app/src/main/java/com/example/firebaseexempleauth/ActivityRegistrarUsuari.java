package com.example.firebaseexempleauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityRegistrarUsuari extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuari);

        mEmail= findViewById(R.id.ET_Email);
        mPassword = findViewById(R.id.ET_Password);


        mRegistrar=  findViewById(R.id.BTN_RegistrarUsuari);
        mRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRegistrar.setText("Hola");
            }
        });
    }
}