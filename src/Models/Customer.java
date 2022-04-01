package Models;

//import Project.User;

import java.io.Serializable;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

public class Customer extends User {
//    protected Wallet wallet;              // toto
//    protected Wallet wallet = new Wallet();               // teraz toto
    protected Wallet wallet;
    protected DatabaseOfAuctions databaseOfAuctions;
    protected PersonalGarage garage;

    public Customer(String userName, String password){
        super(userName, password);
//        this.wallet = new Wallet();       // toto
        this.garage = new PersonalGarage();
    }
    public Customer(){

    }

    public void placeBid(int auctionId, double amount){
        Bid newBid = new Bid(this, amount);
        if(DatabaseOfAuctions.auctions.size() != 0){
            for (Auction a : DatabaseOfAuctions.auctions){
                if (a.auctionId == auctionId){
                    a.receiveBid(newBid, a);
                    return;
                }

            }
        } else
            System.out.println("Auction with given Id not found");
    }

    public Wallet getWallet() {
        return wallet;
    }

    public PersonalGarage getGarage() {
        return garage;
    }
}
