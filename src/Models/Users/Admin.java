package Models.Users;

import Models.Auctions.Auction;
import Models.Auctions.AuctionType;
import Models.Cars.Car;
import Models.Databases.Database;
import Models.Databases.DatabaseOfNews;
import Models.Databases.DatabaseOfUsers;
import Models.News;
import Models.Observer;
import Models.Serializator;
import Project.Main.Main;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Objects of this class are able to create auctions, see the statistics and the news
 */
public class Admin extends User implements Observer {
//    public ArrayList<String> news = new ArrayList<>();

    public static int id = 0;
    private int newsId;

    private News news;

//    public StringBuilder messages = new StringBuilder();
    private ArrayList<String> messages;

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;

//        this.news = new ArrayList<>();
        this.messages = new ArrayList<>();

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

        id++;
        this.newsId = id;

    }

    public void logIn(User user) throws IOException {
        DatabaseOfUsers.currentUser = user;
        DatabaseOfNews.assignNews();

        Main.mainInstance.changeScene("/GUI/AdminMainScreen.fxml");
    }

    public static void createAuction(Car newCar, boolean isPremium, AuctionType auctionType, double minimumPrice) throws IOException {
        Auction newAuction = new Auction(newCar, auctionType, minimumPrice);
        newAuction.isPremium = isPremium;
        Serializator serializator = new Serializator();
        serializator.serialize(newAuction);
    }


    @Override
    public void update(Auction auction) {
        String message = "Auction: " + auction.getAuctionId() + ". " + auction.car.getBrand() + " " + auction.car.getModel() + " has been sold";
        this.news.getNews().add(message);
        messages.add(message);
    }

    public int getNewsId() {
        return newsId;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }
}
