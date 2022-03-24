package Models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Auction {
    public long auctionId;
    private long lastAuctionId;
    private final int maxBids = 3;

    private Bid[] placedBids;

    //public DatabaseOfAuctions databaseOfAuctions;

    public Car car;

    File lastAuctionIdFile = new File("src/Project/Files/lastAuctionIdFile.txt");

    public Auction(Car car){
        this.car = car;
        placedBids = new Bid[maxBids];
        // Function for generating id needed
        this.auctionId = getLastId() + 1L;
        storeLastId(lastAuctionId);
    }

    private void storeLastId(long lastId){
        lastId +=1;
        try{
            FileWriter writer = new FileWriter(lastAuctionIdFile);
            writer.write(Long.toString(lastId));
            writer.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
    }

    private long getLastId(){
        String lastId = "0";
        try {
            Scanner reader = new Scanner(lastAuctionIdFile);
            lastId = reader.nextLine();
            lastId.replaceAll("\n", "");
            reader.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
        this.lastAuctionId = Long.parseLong(lastId);
        return Long.parseLong(lastId);
    }

    public void receiveBid(Bid bid){
        boolean isFull = true;
        for(int i=0; i<maxBids; i++){
            if(this.placedBids[i] == null){
                placedBids[i] = bid;
                isFull = false;
                System.out.println("Bid with amount " + placedBids[i].amount + " has been placed");
                break;
            }
        }
        if(isFull)
            System.out.println("No more spaces for bids");
    }

    public static void main(String[] args) {
//        Car c1 = new Car("Audi", "A7");
//        Car c2 = new Car("Hyundai", "I30");
//        Auction a1 = new Auction(c1);
//        Auction a2 = new Auction(c2);
//        System.out.println("A1 id: " + a1.auctionId + " Brand: " + a1.car.brand + " Model: " + a1.car.model);
//        System.out.println("A2 id: " + a2.auctionId + " Brand: " + a2.car.brand + " Model: " + a2.car.model);

        DatabaseOfAuctions database = new DatabaseOfAuctions();

        Car car1 = new Car("Hyundai", "I30");
        Car car2 = new Car("Audi", "A7");
        Admin admin = new Admin();
//        admin.newCar = car1;
        admin.createAuction(database.auctions, car1);
        admin.createAuction(database.auctions, car2);

        database.displayAuctions();

        Customer customer = new Customer();
        customer.placeBid(database.auctions, 64, 1);
        customer.placeBid(database.auctions, 64, 10);
        customer.placeBid(database.auctions, 64, 100);
        customer.placeBid(database.auctions, 64, 1000);

    }
}
