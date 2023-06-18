package com.example.novabank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUserActivity extends RecyclerView.Adapter<AdapterUserActivity.viewHolder> {
    ArrayList<MainModel>arrayList;
    Context context;

    AdapterUserActivity(ArrayList<MainModel>arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_userdetail,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUserActivity.viewHolder holder, int position) {
         MainModel model=arrayList.get(position);
         holder.phoneno.setText(model.getPhoneno());
         holder.currbalance.setText(model.currbalance);
         holder.username.setText(model.username);

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context, UsersDetailActivity.class);
                 MainModel mainModel=arrayList.get(position);
                 intent.putExtra("chosen_position",String.valueOf(position));
                 intent.putExtra("username", mainModel.getUsername());
                 intent.putExtra("phoneno", mainModel.getPhoneno());
                 intent.putExtra("currbalance", mainModel.getCurrbalance());
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView username, currbalance, phoneno;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            currbalance = itemView.findViewById(R.id.currbalance);
            phoneno = itemView.findViewById(R.id.phoneno);
        }
    }
}
