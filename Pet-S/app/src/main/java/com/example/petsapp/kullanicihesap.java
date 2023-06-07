package com.example.petsapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petsapp.databinding.ActivityKullanicihesapBinding;
import com.example.petsapp.databinding.ActivityVetHesapolusturBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class kullanicihesap<EmailPasswordActivity> extends AppCompatActivity {

    FirebaseAuth mAuth;

    ActivityKullanicihesapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKullanicihesapBinding.inflate(getLayoutInflater());
        View view  = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hesap_kontrol();
            }
        });
    }

    public void hesap_kontrol(){
        String hastaId = binding.hasta.getText().toString().trim();
        String password = binding.sifre.getText().toString().trim();

        if(hastaId.length() == 0){
            Toast.makeText(kullanicihesap.this, "hasta ID giriniz", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length()==0){
            Toast.makeText(kullanicihesap.this, "Şifre giriniz", Toast.LENGTH_LONG).show();
            return;
        }

        // Hasta Id'nin email olup olmadığını kontrol etmek isteyebilirsiniz.
        // Eğer hasta id bir email değilse, Firebase Auth hata verecektir.

        mAuth.createUserWithEmailAndPassword(hastaId, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Hesap oluşturma başarılı olduğunda, kullanıcı bilgilerini alın.
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(kullanicihesap.this, "Hesabınız başarıyla oluşturuldu. ID:" + user.getUid(), Toast.LENGTH_LONG).show();
                        } else {
                            // Hesap oluşturma başarısız olduğunda, hatayı kullanıcıya gösterin.
                            Toast.makeText(kullanicihesap.this, "Hesap oluşturma başarısız.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
