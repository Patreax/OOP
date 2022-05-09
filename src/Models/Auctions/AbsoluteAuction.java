package Models.Auctions;

import Controllers.MainScreenController;
import Models.Bid;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Models.Users.Customer;

import java.io.Serializable;

/**
 * The <code>AbsoluteAuction</code> class is part of strategy design pattern
 * It represents a type of auction where each customer must bid more than the previous customer
 *
 * @see Auction
 */
public class AbsoluteAuction implements AuctionType, Serializable {
    AuctionManager auctionManager = AuctionManager.getInstance();
    String message = "";

    @Override
    public String receiveBid(Bid bid, Auction auction) {

        if (bid.getAmount() < auction.getMinimumPrice()) {
            message = "You must bid at least " + auction.getMinimumPrice();
            return message;
        }

        for (int i = 0; i < auction.getMaxBids(); i++) {
            if (auction.getPlacedBids()[i] != null && auction.getPlacedBids()[i].getBidsUser().getUserId() == bid.getBidsUser().getUserId()) {
                message = "You have already made a bid";
                return message;
            }
            if (auction.getPlacedBids()[i] == null) {
                if (bid.getAmount() > auction.highestBid) {
                    auction.getPlacedBids()[i] = bid;
                    auction.highestBid = bid.getAmount();
                    auction.numberOfBids++;
                    message = "Bid with amount " + auction.getPlacedBids()[i].getAmount() + " has been placed";

                    auction.storeToHistory(bid, auction);

                    break;
                } else
                    message = "You must bid more than " + auction.highestBid;
            }
        }
        if (auction.numberOfBids == auction.getMaxBids()) {
            Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
            currentCustomer.getGarage().getCars().add(auction.car);        //  Giving car to the customer
            currentCustomer.getWallet().setBids(currentCustomer.getWallet().getBids() - auction.highestBid); // taking the money
            MainScreenController.mainScreenControllerInstance.userCurrencyLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));
            auctionManager.notifyObserver(auction);
            DatabaseOfAuctions.auctions.remove(auction);
        }
        return message;
    }
}

