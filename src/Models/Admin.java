package Models;

//import Project.User;

import java.util.ArrayList;

public class Admin extends User {
//    private DatabaseOfAuctions auctionDatabase = new DatabaseOfAuctions();
//    public Car newCar;
    private DatabaseOfAuctions databaseOfAuctions;

//    public void createAuction(ArrayList<Auction> auctions, Car newCar){
//        Auction newAuction = new Auction(newCar);
//        auctions.add(newAuction);
//    }

    public void createAuction(ArrayList<Auction> auctions, Car newCar){
        Auction newAuction = new Auction(newCar);
        auctions.add(newAuction);
    }
}
