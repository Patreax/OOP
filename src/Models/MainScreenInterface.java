package Models;

import Models.Auctions.AuctionManager;
import Models.Databases.*;
import Models.Users.Admin;
import Models.Users.User;
import Project.Main.Main;

import java.io.IOException;

/**
 * This interface prescribes the methods of a main screen UIs
 */
public interface MainScreenInterface {
    void displayData();

    void clear();

    default void logOut(User currentUser) throws IOException {
        // Saving all the data
        Serializator serializator = new Serializator();
        serializator.saveData(Database.DatabaseType.User);
        serializator.saveData(Database.DatabaseType.Auction);
        serializator.saveData(Database.DatabaseType.News);
        if (!(currentUser instanceof Admin)) {
            serializator.saveData(Database.DatabaseType.Wallet);
            serializator.saveData(Database.DatabaseType.Garage);
            serializator.saveData(Database.DatabaseType.Wishlist);
        }
        // Setting current user to null
        DatabaseOfUsers.currentUser = null;
        // Clearing the user and auction database
        DatabaseOfUsers.registeredUsers.clear();
        DatabaseOfAuctions.auctions.clear();
        DatabaseOfWallets.wallets.clear();
        DatabaseOfGarages.garages.clear();
        DatabaseOfWishLists.wishLists.clear();
        DatabaseOfNews.newsArrayList.clear();
        AuctionManager auctionManager = AuctionManager.getInstance();
        auctionManager.observers.clear();

        // Loading first screen
        Main main = new Main();
        main.changeScene("/GUI/LogInScreen.fxml");
    }
}
