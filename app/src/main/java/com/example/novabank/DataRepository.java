package com.example.novabank;

import java.util.ArrayList;

public class DataRepository {
    private static DataRepository instance;
    private ArrayList<MainModel> arrayList;

    private DataRepository() {
        // Private constructor to enforce singleton pattern
        arrayList = new ArrayList<>();
    }

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public ArrayList<MainModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<MainModel> arrayList) {
        this.arrayList = arrayList;
    }
}

