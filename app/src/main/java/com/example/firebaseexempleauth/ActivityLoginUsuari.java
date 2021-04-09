package com.example.firebaseexempleauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLoginUsuari extends AppCompatActivity {

    private TextView mRecuperarPsw;
    private DialogCarregant mDialogCarregant;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuari);
        mRecuperarPsw = findViewById(R.id.RecuperarPassword);
        PrepararActionBar();
        mDialogCarregant = new DialogCarregant();
        mAuth = FirebaseAuth.getInstance();
        mRecuperarPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarDialogRecuperarPassword();
            }
        });




    }
    private void MostrarDialogRecuperarPassword(){
        AlertDialog.Builder constructorDialog = new AlertDialog.Builder(this);
        constructorDialog.setTitle("Recuperar password");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setPadding(10,10,10,10);
        final EditText emailET = new EditText(this);

        emailET.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailET.setMinEms(20);
        linearLayout.addView(emailET);

        constructorDialog.setView(linearLayout);
        constructorDialog.setPositiveButton("Recuperar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = emailET.getText().toString().trim();
                
                IniciarRecuperacioPassword(email);
                
                
            }
        });
        constructorDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        constructorDialog.create().show();
    }

    private void IniciarRecuperacioPassword(String email) {
        mDialogCarregant.show(getSupportFragmentManager(),null);


        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mDialogCarregant.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(ActivityLoginUsuari.this,"Email enviat", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ActivityLoginUsuari.this,"Email NO enviat", Toast.LENGTH_SHORT).show();

                }
            }
        });

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