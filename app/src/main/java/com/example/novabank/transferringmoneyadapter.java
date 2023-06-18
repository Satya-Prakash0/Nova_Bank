package com.example.novabank;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class transferringmoneyadapter extends RecyclerView.Adapter<transferringmoneyadapter.viewHolder> {

    ArrayList<MainModel>list;
    private OnItemClickListener onItemClickListener;
    Context context;

    public transferringmoneyadapter(ArrayList<MainModel> list, Context context,OnItemClickListener onItemClickListener) {
        this.list = list;
        this.context = context;
        this.onItemClickListener=onItemClickListener;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_userdetail,parent,false);
        return new transferringmoneyadapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        MainModel model=list.get(position);
        holder.phoneno.setText(model.getPhoneno());
        holder.currbalance.setText(model.currbalance);
        holder.username.setText(model.username);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
