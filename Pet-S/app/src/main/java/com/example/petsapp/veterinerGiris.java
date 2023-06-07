package com.example.petsapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.petsapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class veterinerGiris extends AppCompatActivity {

    private EditText VeterinerID;
    private EditText   VeterinerSifre;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veteriner_giris);
        EditText VeterinerID = findViewById(R.id.VeterinerID);
        EditText VeterinerSifre = findViewById(R.id.VeterinerSifre);

        Button btngirveteriner = (Button) findViewById(R.id.btngirveteriner);

        btngirveteriner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = VeterinerID.getText().toString();
                String enteredPassword = VeterinerSifre.getText().toString();

                db.collection("veterinarians")
                        .whereEqualTo("email", enteredEmail)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if(task.isSuccessful()){
                                    if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(veterinerGiris.this);
                                        builder.setMessage("Kullanıcı adı ve şifre giriniz.")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.dismiss();//do things
                                                    }
                                                });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    } else {
                                        for (QueryDocumentSnapshot document : task.getResult()){
                                            if(document.getString("vetCode").equals(enteredPassword)){
                                                Intent intent = new Intent(veterinerGiris.this, veterinersayfa.class);
                                                startActivity(intent);
                                            }

                                            else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(veterinerGiris.this);
                                                builder.setMessage("Şifreniz hatalı.")
                                                        .setCancelable(false)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                AlertDialog alert = builder.create();
                                                alert.show();
                                            }

                                        }
                                    }


                                }else{


                                }
                            }
                        });





            }
        });
    }
    }
