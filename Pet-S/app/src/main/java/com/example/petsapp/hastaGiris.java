package com.example.petsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class hastaGiris extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_giris);

        mAuth = FirebaseAuth.getInstance();

        Button girhasta = findViewById(R.id.girhasta);
        EditText HastaID = findViewById(R.id.HastaID);
        EditText HastaSifre = findViewById(R.id.HastaSifre);

        girhasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hastaId = HastaID.getText().toString().trim();
                String hastaSifre = HastaSifre.getText().toString().trim();

                if (hastaId.isEmpty() || hastaSifre.isEmpty()) {
                    Toast.makeText(hastaGiris.this, "Hasta ID veya Şifre boş olamaz", Toast.LENGTH_SHORT).show();
                } else {
                    girisYap(hastaId, hastaSifre);
                }
            }
        });
    }

    private void girisYap(String hastaId, String hastaSifre) {
        mAuth.signInWithEmailAndPassword(hastaId, hastaSifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Giriş başarılı olduğunda, hastasayfa aktivitesine yönlendir.
                            Intent intent = new Intent(hastaGiris.this, hastasayfa.class);
                            startActivity(intent);
                        } else {
                            // Giriş başarısız olduğunda, hatayı kullanıcıya göster.
                            Toast.makeText(hastaGiris.this, "Hasta girişi başarısız", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
