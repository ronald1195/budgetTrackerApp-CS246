package com.example.ronaldmunoz.myapplication;
public enum paymentFrequency {
    WEEKLY("weekly"),
    BIWEEKLY("bi-weekly"),
    MONTHLY("monthly");

    private String paymentFreq;

    private paymentFrequency(String paymentFreq){
        this.paymentFreq = paymentFreq;
    }

    @Override public String toString(){
        return paymentFreq;
    }
}
