package com.example.pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Dog extends AppCompatActivity {

      ImageButton add,cat,vetr;
       RecyclerView recyclerView;
       Adapter_dog adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        getSupportActionBar().setTitle("Perros");

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
}