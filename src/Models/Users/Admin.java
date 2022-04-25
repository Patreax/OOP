package Models.Users;

import Models.*;
import Models.Auctions.Auction;
import Models.Auctions.AuctionType;
import Models.Cars.Car;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Project.sample.Main;

import java.io.IOException;
import java.util.ArrayList;

public class Admin extends User implements Observer {
//    public ArrayList<String> news = new ArrayList<>();
    public ArrayList<String> news;

//    private News news;
    public static int id = 0;
    private int newsId;
    public Admin(String userName, String password){
        this.userName = userName;
        this.password = password;

        this.news = new ArrayList<>();

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

        id++;
        this.newsId = id;
    }

    public void logIn(User user) throws IOException {
        DatabaseOfUsers.currentUser = user;

        Main.mainInstance.changeScene("/GUI/AdminMainScreen.fxml");
    }

    public static void createAuction(Car newCar, boolean isPremium, AuctionType auctionType, double minimumPrice) throws IOException {
        Auction newAuction = new Auction(newCar, auctionType, minimumPrice);
        newAuction.isPremium = isPremium;
        Serializator serializator = new Serializator();
//        serializator.serialize(newAuction, DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
        serializator.serialize(newAuction);
    }


    @Override
    public void update(Auction auction) {
        String message = "Auction: " + auction.getAuctionId() + ". " + auction.car.getBrand() + " " + auction.car.getModel()  + " has been sold";
        this.news.add(message);
        System.out.println(message);
    }
}
