package Models.Auctions;

import Models.Bid;

/**
 * Part of strategy design pattern
 * Every type of auction has a specific way of receiving the bid
 */
public interface AuctionType {
    String receiveBid(Bid bid, Auction auction);
}
