package Models.Auctions;

import Models.Bid;
import Models.Cars.Car;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Auction implements Serializable {

    public Boolean isPremium = false;
    public int numberOfBids;
    public double highestBid = 0;

    private long auctionId;
    private long lastAuctionId;
    private final int maxBids = 3;


    private Bid[] placedBids;

    public Car car;

    File lastAuctionIdFile = new File("src/Project/Files/lastAuctionIdFile.txt");

    public AuctionType auctionType;

    public Auction(Car car, AuctionType auctionType){
        this.car = car;
        placedBids = new Bid[maxBids];
        this.numberOfBids = 0;
        this.auctionId = getLastId() + 1L;
        storeLastId(lastAuctionId);

        this.auctionType = auctionType;
    }

    public Auction(){

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

//    public void receiveBid(Bid bid, Auction auction){
//        boolean isFull = true;
//        for(int i=0; i<maxBids; i++){
//            if(this.placedBids[i] != null && this.placedBids[i].getBidsUser().getUserName().equals(bid.getBidsUser().getUserName())){
//                System.out.println("User has already made a bid");
//                return;
//            }
//            if(this.placedBids[i] == null){
//                placedBids[i] = bid;
//                this.numberOfBids++;
//                isFull = false;
//                if(i == 2)
//                    isFull = true;
//                System.out.println("Bid with amount " + placedBids[i].amount + " has been placed");
//                break;
//            }
//        }
//        if(isFull){
//            System.out.println("No more spaces for bids");
//            double max = placedBids[0].amount;
//            Bid winningBid = null;
//            for(Bid b : placedBids){
//                if(b.amount >= max){
//                    max = b.amount;
//                    winningBid = b;
//                }
//            }
//            if (winningBid != null){
//                StandardUser winner = (StandardUser) winningBid.getBidsUser();
//                winner.getGarage().getCars().add(auction.car);        //  Giving car to the customer
//                System.out.println("Customer " + winningBid.getBidsUser().getUserName() + " won the car " + auction.car.model);
//            }
//            DatabaseOfAuctions.auctions.remove(this);
//        }
//    }

    public void receiveBid(Bid bid, Auction auction){
        auctionType.receiveBid(bid, auction);
    }

    public long getAuctionId() {
        return auctionId;
    }

    public int getMaxBids() {
        return maxBids;
    }

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public Bid[] getPlacedBids() {
        return placedBids;
    }
}
