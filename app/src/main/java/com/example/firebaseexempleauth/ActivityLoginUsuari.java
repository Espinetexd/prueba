package com.example.firebaseexempleauth;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityLoginUsuari extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuari);
        PrepararActionBar();
    }

    private void PrepararActionBar(){
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setTitle("Registre usuari");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp(){

        onBackPressed();
        return super.onSupportNavigateUp();
    }
}