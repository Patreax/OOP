package Models.Auctions;

import Models.Bid;

public interface AuctionType {
    String receiveBid(Bid bid, Auction auction);
}
