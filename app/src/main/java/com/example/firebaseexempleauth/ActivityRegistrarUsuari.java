package com.example.firebaseexempleauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegistrarUsuari extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mRegistrar;
    private com.example.firebaseexempleauth.DialogCarregant mDialogCarregant;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuari);

        mEmail= findViewById(R.id.ET_Email);
        mPassword = findViewById(R.id.ET_Password);

    mDialogCarregant = new com.example.firebaseexempleauth.DialogCarregant();
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
    private void PrepararActionBar(){
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setTitle("Registre usuari");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public boolean onSupportNavigateUp(){

        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void RegistrarUsuari(String email, String password) {

        mDialogCarregant.show(getSupportFragmentManager(),null);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDialogCarregant.dismiss();


                            FirebaseUser usuariActual = mAuth.getCurrentUser();


                            Usuari usuari = new Usuari();
                            usuari.setmUid(usuariActual.getUid());
                            usuari.setmEmail(usuariActual.getEmail());
                            usuari.setmNom("");
                            usuari.setmTelefon("");
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference reference = firebaseDatabase.getReference();
                            reference.child("Usuaris").child(usuari.getmUid()).setValue(usuari);


                            Toast.makeText(ActivityRegistrarUsuari.this,"Registre de" + usuariActual.getEmail(),
                                    Toast.LENGTH_SHORT).show();


                            startActivity(new Intent(ActivityRegistrarUsuari.this,PerfilUsuari.class));

                        }else{
                            Toast.makeText(ActivityRegistrarUsuari.this,"Ja hi ha un usuari amb aquest email", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mDialogCarregant.dismiss();
                Toast.makeText(ActivityRegistrarUsuari.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }



}