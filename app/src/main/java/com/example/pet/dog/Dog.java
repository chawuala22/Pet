package com.example.pet.dog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pet.inscripcion.Add;
import com.example.pet.cat.Cat;
import com.example.pet.R;
import com.example.pet.veterinarian.Veterinarian;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Dog extends AppCompatActivity {

Button button;
    ImageButton add,cat,vetr;
    RecyclerView recyclerView;
    Adapter_dog adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        getSupportActionBar().setTitle("Perros");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        add = findViewById(R.id.inscrbirse);
        cat = findViewById(R.id.gato);
        vetr = findViewById(R.id.veterinario);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Adatos_dog> options =
                new FirebaseRecyclerOptions.Builder<Adatos_dog>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Inscripcion"), Adatos_dog.class)
                        .build();

        adapter = new Adapter_dog(options);
        recyclerView.setAdapter(adapter);

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

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
    }
}
