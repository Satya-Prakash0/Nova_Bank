package com.example.novabank;

public class MainModel {
    String username,phoneno,currbalance,accountNo,emailid,ifsccode;

    public MainModel(String username, String phoneno, String currbalance, String accountNo, String emailid, String ifsccode) {
        this.username = username;
        this.phoneno = phoneno;
        this.currbalance = currbalance;
        this.accountNo = accountNo;
        this.emailid = emailid;
        this.ifsccode = ifsccode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getCurrbalance() {
        return currbalance;
    }

    public void setCurrbalance(String currbalance) {
        this.currbalance = currbalance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }
}
