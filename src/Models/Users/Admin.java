package Models.Users;

import Models.*;
import Models.Auctions.Auction;
import Models.Auctions.AuctionType;
import Models.Cars.Car;

import java.io.IOException;
import java.util.ArrayList;

public class Admin extends User implements Observer {

    public static ArrayList<String> news = new ArrayList<>();

    public Admin(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.userId = getLastId() + 1L;
        storeLastId(lastId);
    }


    public static void createAuction(Car newCar, boolean isPremium, AuctionType auctionType) throws IOException {
        Auction newAuction = new Auction(newCar, auctionType);
        newAuction.isPremium = isPremium;
        Serializator serializator = new Serializator();
        serializator.serialize(newAuction, DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
    }


    @Override
    public void update(Auction auction) {
        String message = "Auction: " + auction.getAuctionId() + ". " + auction.car + " has been sold";
        Admin.news.add(message);
        System.out.println("Message added");
    }
}
