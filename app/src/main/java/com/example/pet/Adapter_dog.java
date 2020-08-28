package com.example.pet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_dog extends FirebaseRecyclerAdapter<Adatos_dog,Adapter_dog.myviewholder> {

    public Adapter_dog(FirebaseRecyclerOptions<Adatos_dog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(myviewholder myviewholder, int i, Adatos_dog adatos_dog) {

        myviewholder.name.setText(adatos_dog.getNpet());
        myviewholder.edad.setText(adatos_dog.getApet());
        myviewholder.desc.setText(adatos_dog.getDescpet());
        Glide.with(myviewholder.img.getContext()).load(adatos_dog.getUrlimage()).into(myviewholder.img);


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_recycler,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,edad,desc;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.image);
            name= itemView.findViewById(R.id.nombrepet);
            edad= itemView.findViewById(R.id.edadpet);
            desc= itemView.findViewById(R.id.descpetpet);


        }
    }
}
