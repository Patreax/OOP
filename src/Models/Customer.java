package Models;

//import Project.User;

import java.util.ArrayList;

public class Customer extends User {
    protected Wallet wallet;
    protected DatabaseOfAuctions databaseOfAuctions;
    public PersonalGarage garage = new PersonalGarage();

    public Customer(String name){
        this.userName = name;
    }
    public Customer(){

    }

    public void placeBid(ArrayList<Auction> auctions, int auctionId, double amount){
        Bid newBid = new Bid(this, amount);
        if(auctions.size() != 0){
            for (Auction a : auctions){
                if (a.auctionId == auctionId){
                    a.receiveBid(newBid, a);
                    return;
                }

            }
        } else
            System.out.println("Auction with given Id not found");
    }

}
