package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class AuctionManager implements Subject, Serializable {

    private Auction auction;

    private ArrayList<Observer> observers;

    private static AuctionManager single_instance = null;

    public AuctionManager(){
        this.observers = new ArrayList<>();
    }

    public static AuctionManager getInstance(){
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
    public void notifyObserver() {
        for(Observer observer : observers){
            observer.update(auction);
        }
    }

    public void setAuction(Auction auction){
        this.auction = auction;
        notifyObserver();
    }
}
