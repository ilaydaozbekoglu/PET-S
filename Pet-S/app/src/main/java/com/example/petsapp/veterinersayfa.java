package com.example.petsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;

public class veterinersayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinersayfa);

            Button btnhastahesap = (Button) findViewById(R.id.btnhastahesap);

            btnhastahesap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(veterinersayfa.this, kullanicihesap.class);
                    startActivity(intent);
                }
            });
        Button btnbildirim = (Button) findViewById(R.id.btnbildirim);

        btnbildirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(veterinersayfa.this, hastabildirim.class);
                startActivity(intent);
            }
        });
        Button btnrandevu = (Button) findViewById(R.id.btnrandevu);

        btnrandevu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(veterinersayfa.this, randevu.class);
                startActivity(intent);
            }
        });
        Button btnbilgiler = (Button) findViewById(R.id.btnbilgiler);

        btnbilgiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(veterinersayfa.this, hastabilgi.class);
                startActivity(intent);
            }
        });

    }
}