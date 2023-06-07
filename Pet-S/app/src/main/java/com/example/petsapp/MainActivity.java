package com.example.petsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.example.petsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view  = binding.getRoot();
        setContentView(view);

        binding.btnHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View hasta) {
                Intent i = new Intent(MainActivity.this,hastaGiris.class);
                startActivity(i);
            }
        });

        binding.btnVet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,veterinerGiris.class);
                startActivity(i);
            }
        });

        binding.btnVetolustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,vet_hesapolustur.class);
                startActivity(i);
            }
        });

    }
}

