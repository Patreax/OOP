package Models;

import Models.Auctions.Auction;

import java.util.ArrayList;

public interface Observer {
    void update(Auction auction);
}
