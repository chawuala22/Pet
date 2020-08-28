package com.example.pet.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pet.R;

public class ViewDog extends AppCompatActivity {

    private ImageView imageView;
    TextView namepet,edadpet,descpet,nameper,direper,emailper,ubiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dog);

        namepet=findViewById(R.id.nombrepet_activity);
        edadpet=findViewById(R.id.edadpet_activity);
        descpet=findViewById(R.id.descpetpet_activity);
        nameper=findViewById(R.id.nombreper_activity);
        direper=findViewById(R.id.direccionper_activity);
        emailper=findViewById(R.id.emailper_activity);
        ubiper=findViewById(R.id.ubicacionper_activity);
        imageView=findViewById(R.id.image_activity);

    }
}