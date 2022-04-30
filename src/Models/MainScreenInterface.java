package Models;

import Models.Auctions.AuctionManager;
import Models.Databases.*;
import Models.Users.Admin;
import Models.Users.User;
import Project.sample.Main;

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
//        serializator.saveData(DatabaseOfUsers.registeredUsers, DatabaseOfUsers.userData);
//        serializator.saveData(DatabaseOfAuctions.auctions, DatabaseOfAuctions.auctionData);
        serializator.saveData(Database.DatabaseType.User);
        serializator.saveData(Database.DatabaseType.Auction);
        if (!(currentUser instanceof Admin)) {
//            serializator.saveData(DatabaseOfWallets.wallets, DatabaseOfWallets.walletData);
//            serializator.saveData(DatabaseOfGarages.garages, DatabaseOfGarages.garageData);
            serializator.saveData(Database.DatabaseType.Wallet);
            serializator.saveData(Database.DatabaseType.Garage);
        }
        // Setting current user to null
        DatabaseOfUsers.currentUser = null;
        // Clearing the user and auction database
        DatabaseOfUsers.registeredUsers.clear();
        DatabaseOfAuctions.auctions.clear();
        DatabaseOfWallets.wallets.clear();
        DatabaseOfGarages.garages.clear();
//        AuctionManager.observers.clear();
        AuctionManager auctionManager = AuctionManager.getInstance();
        auctionManager.observers.clear();

        // Loading first screen
        Main main = new Main();
        main.changeScene("/GUI/sample.fxml");
    }
}
