package com.example.novabank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_BALANCE = "balance";
    private static final String COLUMN_CARD = "card";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PIN = "pin";

    private static final String TRANSACTION_TABLE_NAME = "transactions";
    private static final String COLUMN_TRANSACTION_ID = "transaction_id";
    private static final String COLUMN_SENDER_USERNAME = "sender_username";
    private static final String COLUMN_RECEIVER_USERNAME = "receiver_username";
    private static final String COLUMN_TRANSACTION_AMOUNT = "amount";

    Context context;

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_BALANCE + " TEXT, " +
                COLUMN_CARD + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PIN + " TEXT)";

        db.execSQL(createTableQuery);

        String createTransactionTableQuery = "CREATE TABLE " + TRANSACTION_TABLE_NAME + " (" +
                COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SENDER_USERNAME + " TEXT, " +
                COLUMN_RECEIVER_USERNAME + " TEXT, " +
                COLUMN_TRANSACTION_AMOUNT + " TEXT)";

        db.execSQL(createTransactionTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TABLE_NAME);
        onCreate(db);
    }

    public void insertUser(MainModel user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getUsername());
        values.put(COLUMN_PHONE, user.getPhoneno());
        values.put(COLUMN_BALANCE, user.getCurrbalance());
        values.put(COLUMN_CARD, user.getAccountNo());
        values.put(COLUMN_EMAIL, user.getEmailid());
        values.put(COLUMN_PIN, user.getIfsccode());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void insertTransaction(String senderUsername, String receiverUsername, String amount) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SENDER_USERNAME, senderUsername);
        values.put(COLUMN_RECEIVER_USERNAME, receiverUsername);
        values.put(COLUMN_TRANSACTION_AMOUNT, amount);

        db.insert(TRANSACTION_TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<MainModel> getAllUsers() {
        ArrayList<MainModel> userList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                COLUMN_NAME,
                COLUMN_PHONE,
                COLUMN_BALANCE,
                COLUMN_CARD,
                COLUMN_EMAIL,
                COLUMN_PIN
        };

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE));
                String balance = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BALANCE));
                String card = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CARD));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
                String pin = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIN));

                MainModel user = new MainModel(name, phone, balance, card, email, pin);
                userList.add(user);

            }

            cursor.close();
        }

        db.close();

        return userList;
    }

    public ArrayList<TransactionModel> getAllTransactions() {
        ArrayList<TransactionModel> transactionList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                COLUMN_TRANSACTION_ID,
                COLUMN_SENDER_USERNAME,
                COLUMN_RECEIVER_USERNAME,
                COLUMN_TRANSACTION_AMOUNT
        };

        Cursor cursor = db.query(TRANSACTION_TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int transactionId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_ID));
                String senderUsername = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENDER_USERNAME));
                String receiverUsername = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECEIVER_USERNAME));
                String amount = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_AMOUNT));

                TransactionModel transaction = new TransactionModel(transactionId, senderUsername, receiverUsername, amount);
                transactionList.add(transaction);
            }

            cursor.close();
        }

        db.close();

        return transactionList;
    }

    public void updateUserAmount(String userName, String newAmount) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BALANCE, newAmount);

        // Update the user's amount based on their name
        db.update(TABLE_NAME, values, COLUMN_NAME + " = ?", new String[]{userName});

        db.close();
    }
}
