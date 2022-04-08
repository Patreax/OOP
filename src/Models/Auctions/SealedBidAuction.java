package Models.Auctions;

import Models.Bid;
import Models.Databases.DatabaseOfAuctions;
import Models.Users.StandardUser;

import java.io.Serializable;

public class SealedBidAuction implements AuctionType, Serializable {
    AuctionManager auctionManager = AuctionManager.getInstance();

    @Override
    public void receiveBid(Bid bid, Auction auction) {

        boolean isFull = true;
        for(int i=0; i<auction.getMaxBids(); i++){
            if(auction.getPlacedBids()[i] != null && auction.getPlacedBids()[i].getBidsUser().getUserName().equals(bid.getBidsUser().getUserName())){
                System.out.println("User has already made a bid");
                return;
            }
            if(auction.getPlacedBids()[i] == null){
                auction.getPlacedBids()[i] = bid;
                auction.numberOfBids++;
                isFull = false;
                if(i == 2)
                    isFull = true;
                System.out.println("Bid with amount " + auction.getPlacedBids()[i].amount + " has been placed");
                break;
            }
        }
        if(isFull){
            System.out.println("No more spaces for bids");
            double max = auction.getPlacedBids()[0].amount;
            Bid winningBid = null;
            for(Bid b : auction.getPlacedBids()){
                if(b.amount >= max){
                    max = b.amount;
                    winningBid = b;
                }
            }
            if (winningBid != null){
                StandardUser winner = (StandardUser) winningBid.getBidsUser();
                winner.getGarage().getCars().add(auction.car);        //  Giving car to the customer
                System.out.println("Customer " + winningBid.getBidsUser().getUserName() + " won the car " + auction.car.model);
            }
            DatabaseOfAuctions.auctions.remove(auction);
        }
    }
}

