package com.prodev.collpoll_task.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prodev.collpoll_task.R;
import com.prodev.collpoll_task.SecondActivity;
import com.prodev.collpoll_task.model.model;

import java.util.ArrayList;

public class recycler_adp extends RecyclerView.Adapter<recycler_adp.viewholder> {
    private ArrayList<model> arry = new ArrayList<>();
    private Context mContext;


    public recycler_adp(ArrayList<model> arry, Context mContext){
        this.arry =arry;
        this.mContext=mContext;
    }
    public recycler_adp(Context mContext){
        this.mContext=mContext;
    }

    public void updateList(ArrayList<model> arry){
        this.arry=arry;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v2 = inflater.inflate(R.layout.item_recycle, viewGroup, false);
        viewholder vh = new viewholder(v2);
        return vh;

    }


    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, final int i) {

        viewholder.Name.setText(arry.get(i).getName());
        viewholder.Birthyear.setText(arry.get(i).getBirthyear());
        viewholder.Height.setText(arry.get(i).getHeight());
        viewholder.Gender.setText(arry.get(i).getGender());
        viewholder.Film_count.setText(arry.get(i).getFilm_count());
        viewholder.Species_count.setText(arry.get(i).getSpecies_count());
        viewholder.Vehicles_count.setText(arry.get(i).getVehicles_count());
        viewholder.Starships_count.setText(arry.get(i).getStarships_count());
        viewholder.CreatedAt.setText(arry.get(i).getCreatedAt());
        viewholder.EditedAt.setText(arry.get(i).getEditedAt());


        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 =new Intent(v.getContext(),SecondActivity.class);
                i2.putExtra("number",arry.get(i).getUrl());
                v.getContext().startActivity(i2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arry.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView Name, Birthyear, Height, Gender, Film_count, Species_count,
                Vehicles_count, Starships_count, CreatedAt ,EditedAt;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tx1);
            Birthyear = itemView.findViewById(R.id.tx2);
            Height = itemView.findViewById(R.id.tx3);
            Gender = itemView.findViewById(R.id.tx4);
            Film_count = itemView.findViewById(R.id.tx5);
            Species_count = itemView.findViewById(R.id.tx6);
            Vehicles_count = itemView.findViewById(R.id.tx7);
            Starships_count = itemView.findViewById(R.id.tx8);
            CreatedAt = itemView.findViewById(R.id.tx9);
            EditedAt = itemView.findViewById(R.id.tx10);
        }



    }

}
