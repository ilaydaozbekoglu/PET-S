package com.example.petsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hastasayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hastasayfa);
        Button hastabilgiler = (Button) findViewById(R.id.hastabilgiler);

        hastabilgiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hastasayfa.this, hastabilgiler.class);
                startActivity(intent);
            }
        });
        Button hastabildirimler = (Button) findViewById(R.id.hastabildirimler);

        hastabildirimler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hastasayfa.this, hastabildiriler.class);
                startActivity(intent);
            }
        });
        Button hastaveteriner = (Button) findViewById(R.id.hastaveteriner);

        hastaveteriner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hastasayfa.this, hastaveteriner.class);
                startActivity(intent);
            }
        });

    }

}