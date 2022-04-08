package Models.Auctions;

import Models.Observer;
import Models.Subject;

import java.io.Serializable;
import java.util.ArrayList;

public class AuctionManager implements Subject, Serializable {

    private Auction auction;

    private ArrayList<Observer> observers;

    private static AuctionManager single_instance = null;

    public AuctionManager(){
        this.observers = new ArrayList<>();
    }

    public static AuctionManager getInstance(){     // Singleton
        if (single_instance == null)
            single_instance = new AuctionManager();

        return single_instance;
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer deletedObserver) {
        observers.remove(deletedObserver);
    }

    @Override
    public void notifyObserver(Auction auction) {
        for(Observer observer : observers){
            observer.update(auction);
        }
    }

//    public void setAuction(Auction auction){
//        this.auction = auction;
//        notifyObserver();
//    }
}
