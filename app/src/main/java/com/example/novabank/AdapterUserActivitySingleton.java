package com.example.novabank;

public class AdapterUserActivitySingleton {
    private static AdapterUserActivity instance;

    // Private constructor to prevent instantiation from outside
    private AdapterUserActivitySingleton() {
        // Empty constructor
    }

    // Static method to set the AdapterUserActivity instance
    public static void setInstance(AdapterUserActivity adapterUserActivity) {
        instance = adapterUserActivity;
    }

    // Getter method to retrieve the AdapterUserActivity instance
    public static AdapterUserActivity getInstance() {
        return instance;
    }
}


