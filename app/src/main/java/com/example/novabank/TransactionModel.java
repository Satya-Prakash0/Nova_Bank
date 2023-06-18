package com.example.novabank;

public class TransactionModel {
    private int transactionId;
    private String senderusername;
    private String receiverusername;
    private String amount;


    public TransactionModel(int transactionId, String senderusername, String receiverusername, String amount) {
        this.transactionId = transactionId;
        this.senderusername = senderusername;
        this.receiverusername = receiverusername;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getSenderusername() {
        return senderusername;
    }

    public void setSenderusername(String senderusername) {
        this.senderusername = senderusername;
    }

    public String getReceiverusername() {
        return receiverusername;
    }

    public void setReceiverusername(String receiverusername) {
        this.receiverusername = receiverusername;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

