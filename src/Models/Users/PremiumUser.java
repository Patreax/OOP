package Models.Users;

import Models.Auctions.Auction;
import Models.Bid;
import Models.Databases.*;
import Project.Main.Main;

import java.io.IOException;
import java.io.Serializable;

/**
 * Objects of this class can see all the auctions
 */
public class PremiumUser extends Customer implements Serializable {


    public PremiumUser(String userName, String password) {
        super(userName, password);

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

        Customer.id++;
        this.walletId = id;
        this.garageId = id;
        this.wishListId = id;

    }

    public void logIn(User user) throws IOException {
        DatabaseOfUsers.currentUser = user;
        DatabaseOfWallets.assignWallet();
        DatabaseOfGarages.assignGarage();
        DatabaseOfWishLists.assignWishList();

        Main.mainInstance.changeScene("/GUI/MainScreen.fxml");
    }

    public String placeBid(int auctionId, double amount) {
        String message = "";
        Bid newBid = new Bid(this, amount);
        if (DatabaseOfAuctions.auctions.size() != 0) {
            for (Auction a : DatabaseOfAuctions.auctions) {
                if (a.getAuctionId() == auctionId) {
                    message = a.receiveBid(newBid, a);
                    return message;
                }
            }
        } else
            message = "Auction with given Id not found";
        return message;
    }

}
