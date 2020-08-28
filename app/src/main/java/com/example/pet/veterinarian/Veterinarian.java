package com.example.pet.veterinarian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pet.R;
import com.example.pet.cat.Cat;
import com.example.pet.dog.Dog;
import com.example.pet.inscripcion.Add;

public class Veterinarian extends AppCompatActivity {

    ImageButton dog,cat,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinarian);
        getSupportActionBar().setTitle("Veterinarios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cat = findViewById(R.id.gato);
        dog = findViewById(R.id.perro);
        add = findViewById(R.id.inscrbirse);
        buttons();
    }

    private void buttons() {
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Veterinarian.this, Cat.class);
                startActivity(i);
            }
        });
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Veterinarian.this, Dog.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Veterinarian.this, Add.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}