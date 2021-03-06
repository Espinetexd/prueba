package com.example.firebaseexempleauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilUsuari extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuLogout){
            mAuth.signOut();
            ComprobarUsuari();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        ComprobarUsuari();
        super.onStart();
    }

    private void ComprobarUsuari() {

        FirebaseUser usuariActual = mAuth.getCurrentUser();
        if(usuariActual!=null){
            //Hi ha un usuari loguejat

        }else{
            startActivity(new Intent(PerfilUsuari.this, MainActivity.class));
        }
    }
}