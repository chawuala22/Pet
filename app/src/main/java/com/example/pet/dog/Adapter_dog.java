package com.example.pet.dog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pet.MainActivity;
import com.example.pet.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_dog extends FirebaseRecyclerAdapter<Adatos_dog,Adapter_dog.myviewholder> {



    public Adapter_dog(FirebaseRecyclerOptions<Adatos_dog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(final myviewholder myviewholder, final int i, final Adatos_dog adatos_dog) {


        if (adatos_dog.getPetSpinner().equalsIgnoreCase("Perro")){


            myviewholder.name.setText(adatos_dog.getNpet());
            myviewholder.edad.setText(adatos_dog.getApet());
            Glide.with(myviewholder.img.getContext()).load(adatos_dog.getUrlimage()).into(myviewholder.img);


            myviewholder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DialogPlus dialog = DialogPlus.newDialog(myviewholder.img.getContext())
                            .setContentHolder(new ViewHolder(R.layout.activity_view_dog))
                            .setExpanded(true,1700)
                            .create();

                     final View myview = dialog.getHolderView();

                     final TextView namepet1 = myview.findViewById(R.id.nombrepet_activity);
                    TextView edadpet1 = myview.findViewById(R.id.edadpet_activity);
                    TextView descripet1 = myview.findViewById(R.id.descpetpet_activity);
                    TextView nombreper1 = myview.findViewById(R.id.nombreper_activity);
                    TextView direcper1 = myview.findViewById(R.id.direccionper_activity);
                    TextView emailper1 = myview.findViewById(R.id.emailper_activity);
                    TextView ubiper1 = myview.findViewById(R.id.ubicacionper_activity);
                    final TextView numper1 = myview.findViewById(R.id.numeroper_activity);


                    Button btn1=myview.findViewById(R.id.whatsapp);
                    Button bt2=myview.findViewById(R.id.gmail);

                    namepet1.setText(adatos_dog.getNpet());
                    edadpet1.setText(adatos_dog.getApet());
                    descripet1.setText(adatos_dog.getDescpet());
                    nombreper1.setText(adatos_dog.getNpers());
                    direcper1.setText(adatos_dog.getDirpers());
                    emailper1.setText(adatos_dog.getEpers());
                    ubiper1.setText(adatos_dog.getUbiper());
                    numper1.setText(adatos_dog.getNumcel());

                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(myview.getContext(),"El numero es"+adatos_dog.getNumcel(),Toast.LENGTH_LONG).show();

                        }
                    });

                    bt2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         Toast.makeText(myview.getContext(),"Aquí se te mandaría al correo",Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.show();
                }
            });

        }
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_recycler,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        CircleImageView img;
        TextView name,edad;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img= itemView.findViewById(R.id.image);
            name= itemView.findViewById(R.id.nombrepet);
            edad= itemView.findViewById(R.id.edadpet);
            imageView=itemView.findViewById(R.id.update);

        }
    }
}
