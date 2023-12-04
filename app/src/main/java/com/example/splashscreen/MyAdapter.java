package com.example.splashscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.age.setText(user.getAge());
        holder.blood_pressure.setText(user.getBlood_pressure());
        holder.cholestrol.setText(user.getCholesterol());
        holder.date.setText(user.getDate());
        holder.diabetes.setText(user.getDiabetes());
        holder.gender.setText(user.getGender());
        holder.smoking_status.setText(user.getSmoking_status());
        holder.predicted_risk.setText(user.getPredicted_risk());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, age, blood_pressure, cholestrol, diabetes, gender,smoking_status,predicted_risk;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tvdate);
            age =  itemView.findViewById(R.id.tvage);
            blood_pressure = itemView.findViewById(R.id.tvbloodpressure);
            cholestrol = itemView.findViewById(R.id.tvcholestrol);
            diabetes = itemView.findViewById(R.id.tvdiabetes);
            gender = itemView.findViewById(R.id.tvgender);
            smoking_status = itemView.findViewById(R.id.tvsmoking_status);
            predicted_risk = itemView.findViewById(R.id.tvpredicted_risk);


        }
    }


}
