package Models;

import Models.Auctions.Auction;

public interface Observer {
    void update(Auction auction);
}
