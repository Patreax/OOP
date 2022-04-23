package Models;

import Models.Auctions.Auction;

import java.util.ArrayList;

public interface Observer {
    ArrayList<String> news = new ArrayList<>();
    void update(Auction auction);
}
