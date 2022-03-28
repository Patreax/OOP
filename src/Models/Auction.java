package Models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Auction implements Serializable {
    public long auctionId;
    private long lastAuctionId;
    private final int maxBids = 3;
    private int numberOfBids;

    private Bid[] placedBids;

    //public DatabaseOfAuctions databaseOfAuctions;

    public Car car;

    File lastAuctionIdFile = new File("src/Project/Files/lastAuctionIdFile.txt");

    public Auction(Car car){
        this.car = car;
        placedBids = new Bid[maxBids];
        this.numberOfBids = 0;
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

    public void receiveBid(Bid bid, Auction auction){
        boolean isFull = true;
        for(int i=0; i<maxBids; i++){
            if(this.placedBids[i] != null && this.placedBids[i].getBidsUser().getUserName().equals(bid.getBidsUser().getUserName())){
                System.out.println("User has already made a bid");
                return;
            }
            if(this.placedBids[i] == null){
                placedBids[i] = bid;
                this.numberOfBids++;
                isFull = false;
                if(i == 2)
                    isFull = true;
                System.out.println("Bid with amount " + placedBids[i].amount + " has been placed");
                break;
            }
        }
        if(isFull){
            System.out.println("No more spaces for bids");
            double max = placedBids[0].amount;
            Bid winningBid = null;
            for(Bid b : placedBids){
                if(b.amount >= max){
                    max = b.amount;
                    winningBid = b;
                }
            }
            if (winningBid != null){
                StandardUser winner = (StandardUser) winningBid.getBidsUser();
                winner.getGarage().getCars().add(auction.car);        //  Giving car to the customer
                System.out.println("Customer " + winningBid.getBidsUser().getUserName() + " won the car " + auction.car.model);
            }
            DatabaseOfAuctions.auctions.remove(this);
        }
    }

    public void giveCar(){

    }

    public int getMaxBids() {
        return maxBids;
    }

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public static void main(String[] args) {
//        Car c1 = new Car("Audi", "A7");
//        Car c2 = new Car("Hyundai", "I30");
//        Auction a1 = new Auction(c1);
//        Auction a2 = new Auction(c2);
//        System.out.println("A1 id: " + a1.auctionId + " Brand: " + a1.car.brand + " Model: " + a1.car.model);
//        System.out.println("A2 id: " + a2.auctionId + " Brand: " + a2.car.brand + " Model: " + a2.car.model);

        DatabaseOfAuctions database = new DatabaseOfAuctions();

        Car car1 = new Car("Hyundai", "i30");
        Car car2 = new Car("Audi", "A7");
        Admin admin = new Admin("Meno", "Heslo");

        try{
            admin.createAuction(car1);
            admin.createAuction(car2);
        } catch (IOException e){
            e.printStackTrace();
        }


        database.displayAuctions();

        Customer customer = new Customer("Patrik", "Heslo");
        Customer customer1 = new Customer("Jack", "Heslo");
        customer1.placeBid(111, 1);
        customer.placeBid(111, 10);
        customer1.placeBid(111, 100);
        customer1.placeBid( 106, 1000);
        customer.placeBid( 106, 50000);
        customer1.placeBid( 106, 10000);

        System.out.println();

    }
}
