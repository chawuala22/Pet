package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Dog extends AppCompatActivity {

    ImageButton add,cat,vetr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        getSupportActionBar().setTitle("Perros");
        add = findViewById(R.id.inscrbirse);
        cat = findViewById(R.id.gato);
        vetr = findViewById(R.id.veterinario);
        buttons();

    }

    private void buttons() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Dog.this, Add.class);
                startActivity(i);
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Dog.this, Cat.class);
                startActivity(i);
            }
        });

        vetr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Dog.this, Veterinarian.class);
                startActivity(i);

            }
        });
    }

}