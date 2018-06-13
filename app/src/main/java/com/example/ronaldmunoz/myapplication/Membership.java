package com.example.ronaldmunoz.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Membership {
    String userName;
    String userEmail;
    Date paymentDueDate;
    String frequency;
    String comments;

    // Default constructor for Membership class
    public Membership(){
        userName = null;
        userEmail = null;
        paymentDueDate = null;
        comments = null;
        frequency = null;
    }

    // Non-default constructor for Membership class
    public Membership(String name, String email, Date dueDate, String freq , String comment){
        userName = name;
        userEmail = email;
        paymentDueDate = dueDate;
        frequency = freq;
        comments = comment;
    }

    // Non-default constructor 2
    public Membership(String name, Date dueDate, String freq ){
        userName = name;
        userEmail = null;
        paymentDueDate = dueDate;
        frequency = freq;
        comments = null;
    }


    // Some Getters
    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public String getFrequency() {
        return frequency;
    }


    public void getMembershipInfo(){
        System.out.print("Name on membership:  " + getUserName()
                        +"\nEmail:             " + getUserName()
                        +"\nPayment is due the " + dateToString(paymentDueDate) + " of every " + getFrequency()
                        +"\nComments:          " + displayComments());
    }

    //  Function useful to handle empty comments.
    public String displayComments(){
        String emptyCommentMessage = "There is no comments for this membership";

        if(comments != null){
            return comments;
        }
        else{
            return emptyCommentMessage;
        }
    }

    // Function just to convert a date to a string.
    public String dateToString(Date indate)
    {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd");
        /*you can also use DateFormat reference instead of SimpleDateFormat
         * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
         */
        try{
            dateString = sdfr.format( indate );
        }catch (Exception ex ){
            System.out.println(ex);
        }
        return dateString;
    }

}
