package Models;

import Models.Auctions.Auction;

public interface Subject {
    void register(Observer observer);

    void unregister(Observer observer);

    void notifyObserver(Auction auction);
}
