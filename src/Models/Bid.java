package Models;

import Models.Users.Customer;

import java.io.Serializable;

/**
 * The object of this class stores information about the amount of currency and the {@link Customer} by whom the bid has bee placed
 */
public class Bid implements Serializable {
    private Customer bidsUser;
    private double amount;


    public Bid(Customer bidsUser, double amount) {
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
