package Models;

//import Project.User;

import java.util.ArrayList;

public class Customer extends User {
    protected Wallet wallet;
    protected DatabaseOfAuctions databaseOfAuctions;

    public void placeBid(ArrayList<Auction> auctions, int auctionId, double amount){
        Bid newBid = new Bid(this, amount);
        if(auctions.size() != 0){
            for (Auction a : auctions){
                if (a.auctionId == auctionId){
                    a.receiveBid(newBid);
                    return;
                }
                else
                    System.out.println("Auction with given Id not found");
            }
        }
    }

}
