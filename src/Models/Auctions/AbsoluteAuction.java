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
//                System.out.println("User has already made a bid");
//                MainScreenController.mainScreenControllerInstance.textArea.appendText("You have already made a bid");
                message = "You have already made a bid";
                return message;
            }
            if (auction.getPlacedBids()[i] == null) {
                if (bid.getAmount() > auction.highestBid) {
                    auction.getPlacedBids()[i] = bid;
                    auction.highestBid = bid.getAmount();
                    auction.numberOfBids++;
                    message = "Bid with amount " + auction.getPlacedBids()[i].getAmount() + " has been placed";

//                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
//                    Date date = new Date(System.currentTimeMillis());
////                    System.out.println(formatter.format(date));
//
//                    try{
//                        FileWriter fw = new FileWriter("src/Project/Files/history.txt", true);
//                        BufferedWriter bw = new BufferedWriter(fw);
//                        String note = "Auction " + auction.getAuctionId() + ": " + bid.getAmount() + " has been placed by "
//                                + DatabaseOfUsers.currentUser.getUserName() + " at " + formatter.format(date);
//                        bw.write(note);
//                        bw.newLine();
//                        bw.close();
//                    } catch (IOException e){
//                        e.printStackTrace();
//                    }

                    auction.storeToHistory(bid, auction);

                    break;
                } else
                    message = "You must bid more than " + auction.highestBid;
//                    MainScreenController.mainScreenControllerInstance.textArea.appendText("You must bid more than " + auction.highestBid);
                //System.out.println("You must bid more than " + auction.highestBid);
            }
        }
        if (auction.numberOfBids == auction.getMaxBids()) {
//            System.out.println("No more spaces for bids");
//            MainScreenController.mainScreenControllerInstance.textArea.appendText("No more spaces for bids");
            Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
            currentCustomer.getGarage().getCars().add(auction.car);        //  Giving car to the customer
            currentCustomer.getWallet().setBids(currentCustomer.getWallet().getBids() - auction.highestBid); // taking the money
            MainScreenController.mainScreenControllerInstance.userCurrencyLabel.setText(Double.toString(currentCustomer.getWallet().getBids()));
//            System.out.println("Customer " + currentCustomer + " won the car " + auction.car.model);
            auctionManager.notifyObserver(auction);
            DatabaseOfAuctions.auctions.remove(auction);
        }
        return message;
    }
}

