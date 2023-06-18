package com.example.novabank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    ArrayList<MainModel>arrayList;
    public AdapterUserActivity adapterUserActivity;
    private FloatingActionButton addaccountbtn,refreshacntbtn;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String DATA_INSERTED_KEY = "DataInserted";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        //action bar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Banking System");

        //Adapter and Recycler view creation
        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewuser);
        addaccountbtn=findViewById(R.id.addaccountbtn);
        refreshacntbtn=findViewById(R.id.refreshaccnt);

        ;
        // Create an instance of MyDbHelper
        MyDbHelper dbHelper = new MyDbHelper(UsersActivity.this);
//        ArrayList<TransactionModel>transactionModelArrayList=dbHelper.getAllTransactions();
//        for (TransactionModel it:transactionModelArrayList) {
//            Toast.makeText(this, it.getSenderusername(), Toast.LENGTH_SHORT).show();
//        }

// Insert data into the database

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDataInserted = prefs.getBoolean(DATA_INSERTED_KEY, false);

        if (!isDataInserted) {
            dbHelper.insertUser(new MainModel("ram sigha roy", "9883736219", "1000", "2034******123", "example@google.com", "234567"));
            dbHelper.insertUser(new MainModel("John Doe", "9876543210", "500", "5678******456", "john.doe@example.com", "876543"));
            dbHelper.insertUser(new MainModel("Jane Smith", "9876543211", "750", "9876******789", "jane.smith@example.com", "987654"));
            dbHelper.insertUser(new MainModel("David Johnson", "9876543212", "2000", "6543******210", "david.johnson@example.com", "456789"));
            dbHelper.insertUser(new MainModel("Emily Davis", "9876543213", "1500", "7890******543", "emily.davis@example.com", "987654"));
            dbHelper.insertUser(new MainModel("Michael Wilson", "9876543214", "3000", "3456******876", "michael.wilson@example.com", "123456"));
            dbHelper.insertUser(new MainModel("Sophia Thomas", "9876543215", "1800", "8765******210", "sophia.thomas@example.com", "654321"));
            dbHelper.insertUser(new MainModel("Oliver Martinez", "9876543216", "2500", "5432******543", "oliver.martinez@example.com", "987654"));
            dbHelper.insertUser(new MainModel("Isabella Anderson", "9876543217", "1200", "2109******876", "isabella.anderson@example.com", "345678"));
            dbHelper.insertUser(new MainModel("William Thompson", "9876543218", "3500", "4321******987", "william.thompson@example.com", "567890"));

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(DATA_INSERTED_KEY, true);
            editor.apply();
        }
// Retrieve all users from the database
        ArrayList<MainModel> userList = dbHelper.getAllUsers();
        DataRepository dataRepository = DataRepository.getInstance();
        dataRepository.setArrayList(userList);
        arrayList = dataRepository.getArrayList();
//        arrayList.clear();
//        insertDatainList(arrayList);

        adapterUserActivity=new AdapterUserActivity(arrayList,this);
        recyclerView.setAdapter(adapterUserActivity);

//      Set the AdapterUserActivity instance using the setter
        AdapterUserActivitySingleton.setInstance(adapterUserActivity);
        // Retrieve the AdapterUserActivity instance using the getter
        // AdapterUserActivity adapterUserActivity = AdapterUserActivitySingleton.getInstance();



//
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(UsersActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //floating action add account
        addaccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UsersActivity.this,"New Acc. Added",Toast.LENGTH_SHORT).show();
                arrayList.add(new MainModel("Emma Garcia", "9876543219", "2200", "1098******654", "emma.garcia@example.com", "901234"));
                adapterUserActivity.notifyDataSetChanged();
            }
        });

        refreshacntbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterUserActivity.notifyDataSetChanged();
            }
        });

        refreshacntbtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view, "Refresh Your Account", Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private void insertDatainList(ArrayList<MainModel> arrayList) {

        MainModel model1 = new MainModel("Richi Michael", "9883736219", "1000", "2034******123", "example@google.com", "234567");
        arrayList.add(model1);

        MainModel model2 = new MainModel("John Doe", "9876543210", "500", "5678******456", "john.doe@example.com", "876543");
        arrayList.add(model2);

        MainModel model3 = new MainModel("Jane Smith", "9876543211", "750", "9876******789", "jane.smith@example.com", "987654");
        arrayList.add(model3);

        MainModel model4 = new MainModel("David Johnson", "9876543212", "2000", "6543******210", "david.johnson@example.com", "456789");
        arrayList.add(model4);

        MainModel model5 = new MainModel("Emily Davis", "9876543213", "1500", "7890******543", "emily.davis@example.com", "987654");
        arrayList.add(model5);

        MainModel model6 = new MainModel("Michael Wilson", "9876543214", "3000", "3456******876", "michael.wilson@example.com", "123456");
        arrayList.add(model6);

        MainModel model7 = new MainModel("Sophia Thomas", "9876543215", "1800", "8765******210", "sophia.thomas@example.com", "654321");
        arrayList.add(model7);

        MainModel model8 = new MainModel("Oliver Martinez", "9876543216", "2500", "5432******543", "oliver.martinez@example.com", "987654");
        arrayList.add(model8);

        MainModel model9 = new MainModel("Isabella Anderson", "9876543217", "1200", "2109******876", "isabella.anderson@example.com", "345678");
        arrayList.add(model9);

        MainModel model10 = new MainModel("William Thompson", "9876543218", "3500", "4321******987", "william.thompson@example.com", "567890");
        arrayList.add(model10);

    }

    //fragments for showing transaction
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.alltransaction) {
            // Handle click event and navigate to the new fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Replace "YourNewFragment" with the actual name of your new fragment class
//            transactionfrag fragment = new transactionfrag();
//
//            fragmentTransaction.replace(R.id.container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}