package Models.Auctions;

import Models.Databases.Database;
import Models.Databases.DatabaseOfNews;
import Models.Databases.DatabaseOfUsers;
import Models.Observer;
import Models.Serializator;
import Models.Subject;
import Models.Users.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The <code>AuctionManager</code> class acts as a subject in observer design pattern
 * It is responsible for registering and notifying the observers when the auction is sold
 *
 * @see Subject
 * @see Observer
 */
public class AuctionManager implements Subject, Serializable {

    private Auction auction;

    public ArrayList<Observer> observers = new ArrayList<>();

    private static AuctionManager single_instance = null;

    /**
     * Singleton
     *
     * @return
     */
    public static AuctionManager getInstance() {     // Singleton
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
        DatabaseOfNews.assignNewsToAdmins();
        for (Observer observer : observers) {
            observer.update(auction);
            System.out.println(observer);
        }
        for(User user : DatabaseOfUsers.registeredUsers){
            if(user instanceof Observer){
                ((Observer) user).update(auction);
            }
        }
//        try {
//            Serializator serializator = new Serializator();
//            serializator.saveData(Database.DatabaseType.User);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

    }
}
