package Models.Auctions;

import Controllers.MainScreenController;
import Models.Bid;
import Models.Databases.DatabaseOfAuctions;
import Models.Databases.DatabaseOfUsers;
import Models.Users.Customer;

import java.io.Serializable;

public class AbsoluteAuction implements AuctionType, Serializable {
    AuctionManager auctionManager = AuctionManager.getInstance();

    @Override
    public void receiveBid(Bid bid, Auction auction) {
        for(int i=0; i<auction.getMaxBids(); i++) {
            if (auction.getPlacedBids()[i] != null && auction.getPlacedBids()[i].getBidsUser().getUserId() == bid.getBidsUser().getUserId()) {
                System.out.println("User has already made a bid");
                MainScreenController.mainScreenControllerInstance.textArea.appendText("You have already made a bid");
                return;
            }
            if(auction.getPlacedBids()[i] == null){
                if(bid.amount > auction.highestBid){
                    auction.getPlacedBids()[i] = bid;
                    auction.highestBid = bid.amount;
                    auction.numberOfBids++;
                    System.out.println("Bid with amount " + auction.getPlacedBids()[i].amount + " has been placed");
                    MainScreenController.mainScreenControllerInstance.textArea.appendText("Bid with amount " + auction.getPlacedBids()[i].amount + " has been placed");
                    break;
                }
                else MainScreenController.mainScreenControllerInstance.textArea.appendText("You must bid more than " + auction.highestBid);
                    //System.out.println("You must bid more than " + auction.highestBid);
            }
        }
        if(auction.numberOfBids == auction.getMaxBids()){
            System.out.println("No more spaces for bids");
            MainScreenController.mainScreenControllerInstance.textArea.appendText("No more spaces for bids");
            Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
            currentCustomer.getGarage().getCars().add(auction.car);        //  Giving car to the customer
            currentCustomer.getWallet().setBids(currentCustomer.getWallet().getBids() - auction.highestBid); // taking the money
            MainScreenController.mainScreenControllerInstance.userCurrencyLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));
//            System.out.println("Customer " + currentCustomer + " won the car " + auction.car.model);
            auctionManager.setAuction(auction);
            DatabaseOfAuctions.auctions.remove(auction);
        }
    }
}

