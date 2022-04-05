package Models.Auctions;

import Models.Bid;

public interface AuctionType {
    void receiveBid(Bid bid, Auction auction);
}
