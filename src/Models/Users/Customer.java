package Models.Users;


import Models.*;
import Models.Auctions.Auction;
import Models.Databases.DatabaseOfAuctions;

public class Customer extends User {
    protected Wallet wallet;
    protected PersonalGarage garage;

    public static int id = 0;
    protected int walletId;
    protected int garageId;



    public Customer(String userName, String password){
        super(userName, password);
//        this.wallet = new Wallet();
//        this.garage = new PersonalGarage();

        id++;
        this.walletId = id;
        this.garageId = id;
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

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public PersonalGarage getGarage() {
        return garage;
    }

    public void setGarage(PersonalGarage garage) {
        this.garage = garage;
    }

    public int getWalletId() {
        return walletId;
    }

    public int getGarageId() {
        return garageId;
    }
}
