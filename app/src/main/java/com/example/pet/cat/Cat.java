package com.example.pet.cat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pet.inscripcion.Add;
import com.example.pet.R;
import com.example.pet.veterinarian.Veterinarian;
import com.example.pet.dog.Dog;

public class Cat extends AppCompatActivity {

    ImageButton add, dog, vetr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        getSupportActionBar().setTitle("Gatos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = findViewById(R.id.inscrbirse);
        dog = findViewById(R.id.perro);
        vetr = findViewById(R.id.veterinario);
        buttons();

    }

    private void buttons() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Cat.this, Add.class);
                startActivity(i);
            }
        });

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Cat.this, Dog.class);
                startActivity(i);
            }
        });

        vetr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Cat.this, Veterinarian.class);
                startActivity(i);

            }
        });

    }
    @Override
    public void onBackPressed() {
    }
}