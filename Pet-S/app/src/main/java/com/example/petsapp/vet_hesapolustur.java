package com.example.petsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import com.example.petsapp.databinding.ActivityMainBinding;
import com.example.petsapp.databinding.ActivityVetHesapolusturBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;



public class vet_hesapolustur extends AppCompatActivity {

    private EditText txt_vetadi, txt_vetEmail, txtPhoneVet, txt_vetadres, txt_kod;
    private Button btn_kaydol;
    private TextView txt_info;
    private FirebaseFirestore db;
    ActivityVetHesapolusturBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_hesapolustur);

        db = FirebaseFirestore.getInstance(); // Initialize Firestore

        txt_vetadi = findViewById(R.id.txt_vetadi);
        txt_vetEmail = findViewById(R.id.txt_vetEmail);
        txtPhoneVet = findViewById(R.id.txtPhoneVet);
        txt_kod = findViewById(R.id.txt_kod);
        txt_vetadres = findViewById(R.id.txt_vetadres);
        txt_kod = findViewById(R.id.txt_kod); // Find the txt_kod
        btn_kaydol = findViewById(R.id.btn_kaydol);
        txt_info = findViewById(R.id.txt_info);

        btn_kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    public void registerUser() {
        String vetName = txt_vetadi.getText().toString();
        String email = txt_vetEmail.getText().toString();
        String phone = txtPhoneVet.getText().toString();
        String address = txt_vetadres.getText().toString();

        Map<String, Object> vet = new HashMap<>();
        vet.put("name", vetName);
        vet.put("email", email);
        vet.put("phone", phone);
        vet.put("address", address);
        String randomCode = createRandomCode(5); // Creates a 5 digit random code.
        vet.put("vetCode", randomCode);

        db.collection("veterinarians")
                .add(vet)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(vet_hesapolustur.this, "Veteriner kaydı başarılı!", Toast.LENGTH_SHORT).show();
                        txt_kod.setText(randomCode); // Display the code to the user.
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(vet_hesapolustur.this, "Veteriner kaydı başarısız!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String createRandomCode(int codeLength) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            code.append(random.nextInt(10)); // Generate a random number between 0 and 9
        }
        return code.toString();
    }
}
