package Models;

import Models.Users.Customer;

import java.io.Serializable;

public class Bid implements Serializable {
    private Customer bidsUser;
    private double amount;


    public Bid(Customer bidsUser, double amount){
        this.bidsUser = bidsUser;
        this.amount = amount;
    }

    public Customer getBidsUser() {
        return bidsUser;
    }

    public double getAmount() {
        return amount;
    }
}
