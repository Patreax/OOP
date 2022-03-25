package Models;

public class Bid {
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
