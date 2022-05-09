package Models.Users;

import Models.Auctions.Auction;
import Models.Bid;
import Models.Databases.*;
import Models.PersonalGarage;
import Models.Wallet;
import Models.WishList;
import Project.Main.Main;

import java.io.IOException;

/**
 * Objects of this class can see only the auctions that are no marked as premium
 */
public class StandardUser extends Customer {

    public StandardUser(String userName, String password) {
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
                    if (a.isPremium)
                        return null;
                    message = a.receiveBid(newBid, a);
                    return message;
                }
            }
        } else
            message = "Auction with given Id not found";
        return message;
    }

    public PersonalGarage getGarage() {
        return garage;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public WishList getWishList() {
        return wishList;
    }
}
