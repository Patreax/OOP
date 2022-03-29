package Models;

//import Project.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Admin extends User {
//    private DatabaseOfAuctions auctionDatabase = new DatabaseOfAuctions();
//    public Car newCar;
    private DatabaseOfAuctions databaseOfAuctions;

    public Admin(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.userId = getLastId() + 1L;
        storeLastId(lastId);
    }


//    public void createAuction(ArrayList<Auction> auctions, Car newCar){
//        Auction newAuction = new Auction(newCar);
//        auctions.add(newAuction);
//    }

    public static void createAuction(Car newCar, boolean isPremium) throws IOException {
        Auction newAuction = new Auction(newCar);
        newAuction.isPremium = isPremium;
        //auctions.add(newAuction);
        Serializator serializator = new Serializator();
        serializator.serialize(newAuction, DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
    }
}
