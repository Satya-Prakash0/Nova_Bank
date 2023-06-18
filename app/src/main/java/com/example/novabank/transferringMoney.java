package com.example.novabank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class transferringMoney extends AppCompatActivity implements transferringmoneyadapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ArrayList<MainModel>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferring_money);

        //action bar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Transferring Money to User");

        //adapter
        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewUser);
        DataRepository dataRepository = DataRepository.getInstance();
        list = dataRepository.getArrayList();

        transferringmoneyadapter transferringmoneyadapter=new transferringmoneyadapter(list,this,this);
        recyclerView.setAdapter(transferringmoneyadapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(transferringMoney.this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onItemClick(int position) {
        MainModel clickedModel = list.get(position);
        Intent intent = getIntent();
        String amount = intent.getStringExtra("amount");
        String userchoosepos=intent.getStringExtra("userchoosepos");
        String originalamt = clickedModel.getCurrbalance();

        //update the receiver amount
        int transferamt = Integer.parseInt(amount) + Integer.parseInt(originalamt);
        clickedModel.setCurrbalance(String.valueOf(transferamt));
        Toast.makeText(this, "Transfer Successful", Toast.LENGTH_SHORT).show();

        //updating the sender amount
        MainModel updatemodel=list.get(Integer.parseInt(userchoosepos));
        int updateamt=Integer.parseInt(updatemodel.getCurrbalance()) - Integer.parseInt(amount);
        updatemodel.setCurrbalance(String.valueOf(updateamt));

        // Update the sender's amount in the database
        MyDbHelper dbHelper = new MyDbHelper(transferringMoney.this);
        dbHelper.updateUserAmount(updatemodel.getUsername(), String.valueOf(updateamt));

        // Update the recipient's amount in the database
        dbHelper.updateUserAmount(clickedModel.getUsername(), clickedModel.getCurrbalance());


        // Retrieve the AdapterUserActivity instance using the getter
        AdapterUserActivity adapterUserActivity = AdapterUserActivitySingleton.getInstance();
        adapterUserActivity.notifyDataSetChanged();

        //transaction update
        dbHelper.insertTransaction(updatemodel.getUsername(), clickedModel.getUsername(),amount);
//        dbHelper.close();

        // Finish the current activity
        finish();
    }

}