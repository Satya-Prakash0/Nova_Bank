package com.example.novabank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersDetailActivity extends AppCompatActivity {
     Button transferMoney;
     private TextView usernameTextView, phonenoTextView, currbalanceTextView,accountNoTextView,useremailTextView,ifsccodeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_detail);

        //action bar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("User Account Detail");

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String phoneno = intent.getStringExtra("phoneno");
        String currbalance = intent.getStringExtra("currbalance");
        String userchoosepos=intent.getStringExtra("chosen_position");

        //dialog box
        transferMoney=findViewById(R.id.transferMoney);
        transferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTransferDialog(userchoosepos);
            }
        });

        ArrayList<MainModel>list=DataRepository.getInstance().getArrayList();
        MainModel model=list.get(Integer.parseInt(userchoosepos));

        usernameTextView=findViewById(R.id.user_name);
        accountNoTextView=findViewById(R.id.account_Number);
        phonenoTextView=findViewById(R.id.registration_phoneNo);
        currbalanceTextView=findViewById(R.id.curr_money);
        useremailTextView=findViewById(R.id.user_email);
        ifsccodeTextView=findViewById(R.id.ifsccode);

        usernameTextView.setText(username);
        phonenoTextView.setText(phoneno);
        currbalanceTextView.setText(currbalance);
        accountNoTextView.setText(model.getAccountNo());
        useremailTextView.setText(model.getEmailid());
        ifsccodeTextView.setText(model.getIfsccode());

    }
    private void showTransferDialog(String userchoosepos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Enter the amount that you want to transfer:");
        builder.setCancelable(false);

        // Inflate the dialog view
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_transfer, null);
        builder.setView(dialogView);

        // Get the EditText from the dialog view
        EditText amountEditText = dialogView.findViewById(R.id.amountEditText);

        // Set the positive button with a click listener
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the entered amount
                String amount = amountEditText.getText().toString().trim();

                // Start the new activity or perform any other action
                Intent intent = new Intent(UsersDetailActivity.this, transferringMoney.class);
                intent.putExtra("amount", amount);
                intent.putExtra("userchoosepos",userchoosepos);

                ArrayList<MainModel>list=DataRepository.getInstance().getArrayList();
                MainModel model=list.get(Integer.parseInt(userchoosepos));
                String userbalance= model.getCurrbalance();

                if(Integer.parseInt(amount)>Integer.parseInt(userbalance)){
                    WarningAlertDialog();
                }
                else{
                    int amt=Integer.parseInt(userbalance)-Integer.parseInt(amount);
                    currbalanceTextView.setText(String.valueOf(amt));
                    startActivity(intent);
                }
            }
        });

        // Set the negative button with a click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Close the dialog
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void WarningAlertDialog() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(UsersDetailActivity.this);
        builder2.setTitle("Warning");
        builder2.setMessage("Insufficient Balance");
        builder2.setIcon(R.drawable.baseline_warning_amber_24);

        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle positive button click
                dialog.dismiss();
            }
        });
        AlertDialog dialog2 = builder2.create();
        dialog2.show();
    }

}