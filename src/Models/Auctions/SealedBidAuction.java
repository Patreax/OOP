package Models.Auctions;

import Controllers.MainScreenController;
import Models.Bid;
import Models.Databases.DatabaseOfAuctions;
import Models.Users.Customer;

import java.io.Serializable;

/**
 * The <code>SealedBidAuction</code> class is part of strategy design pattern
 * It represents a type of auction where three customers can bid any amount of money that is higher than the price of the auction
 * The winner of the auction is the customer that has bid the highest amount
 *
 * @see Auction
 */
public class SealedBidAuction implements AuctionType, Serializable {
    AuctionManager auctionManager = AuctionManager.getInstance();
    String message = "";

    @Override
    public String receiveBid(Bid bid, Auction auction) {

        if (bid.getAmount() < auction.getMinimumPrice()) {
            message = "You must bid at least " + auction.getMinimumPrice();
            return message;
        }

        boolean isFull = true;
        for (int i = 0; i < auction.getMaxBids(); i++) {
            if (auction.getPlacedBids()[i] != null && auction.getPlacedBids()[i].getBidsUser().getUserName().equals(bid.getBidsUser().getUserName())) {
                System.out.println("User has already made a bid");
                MainScreenController.mainScreenControllerInstance.textArea.appendText("You have already made a bid");
                message = "You have already made a bid";
                return message;
            }
            if (auction.getPlacedBids()[i] == null) {
                auction.getPlacedBids()[i] = bid;
                auction.numberOfBids++;
                isFull = false;
                if (i == 2)
                    isFull = true;
                System.out.println("Bid with amount " + auction.getPlacedBids()[i].getAmount() + " has been placed");
                MainScreenController.mainScreenControllerInstance.textArea.appendText("Bid with amount " + auction.getPlacedBids()[i].getAmount() + " has been placed");
                message = "Bid with amount " + auction.getPlacedBids()[i].getAmount() + " has been placed";

//                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
//                Date date = new Date(System.currentTimeMillis());
//
//                try{
//                    FileWriter fw = new FileWriter("src/Project/Files/history.txt", true);
//                    BufferedWriter bw = new BufferedWriter(fw);
//                    String note = "Auction " + auction.getAuctionId() + ": " + bid.getAmount() + " has been placed by "
//                            + DatabaseOfUsers.currentUser.getUserName() + " at " + formatter.format(date);
//                    bw.write(note);
//                    bw.newLine();
//                    bw.close();
//                } catch (IOException e){
//                    e.printStackTrace();
//                }

                auction.storeToHistory(bid, auction);

                break;
            }
        }
        if (isFull) {
            System.out.println("No more spaces for bids");
            MainScreenController.mainScreenControllerInstance.textArea.appendText("No more spaces for bids");
            double max = auction.getPlacedBids()[0].getAmount();
            Bid winningBid = null;
            for (Bid b : auction.getPlacedBids()) {
                if (b.getAmount() >= max) {
                    max = b.getAmount();
                    winningBid = b;
                }
            }
            if (winningBid != null) {
                Customer winner = (Customer) winningBid.getBidsUser();
                auctionManager.notifyObserver(auction);
                winner.getGarage().getCars().add(auction.car);        //  Giving car to the customer
                winner.getWallet().setBids(winner.getWallet().getBids() - winningBid.getAmount());   // taking the money
                MainScreenController.mainScreenControllerInstance.userCurrencyLabel.setText(Double.toString(winner.getWallet().getBids()));
//                System.out.println("Customer " + winningBid.getBidsUser().getUserName() + " won the car " + auction.car.model);
            }
            DatabaseOfAuctions.auctions.remove(auction);
        }
        return message;
    }
}

