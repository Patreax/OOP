package Models;

import java.io.Serializable;

public class Bid implements Serializable {
    private Customer bidsUser;
    public double amount;


    public Bid(Customer bidsUser, double amount){
        this.bidsUser = bidsUser;
        this.amount = amount;
    }

    public Customer getBidsUser() {
        return bidsUser;
    }
}
