package Models.Users;


import Models.*;
import Models.Auctions.Auction;

public class Customer extends User {
    protected Wallet wallet;
    protected PersonalGarage garage;

    public Customer(String userName, String password){
        super(userName, password);
//        this.wallet = new Wallet();
        this.garage = new PersonalGarage();
    }
    public Customer(){

    }

    public void placeBid(int auctionId, double amount){
        Bid newBid = new Bid(this, amount);
        if(DatabaseOfAuctions.auctions.size() != 0){
            for (Auction a : DatabaseOfAuctions.auctions){
                if (a.getAuctionId() == auctionId){
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
