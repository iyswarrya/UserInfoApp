package com.learn.model;

public class BankInfo {
    private String accountNumber;
    private String routingNumber;
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getRoutingNumber() {
        return routingNumber;
    }
    
    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }
    
    public void validate() {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }
        if (routingNumber == null || routingNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Routing number cannot be empty");
        }
    }
} 