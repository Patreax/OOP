package Models.Auctions;

import Models.Bid;
import Models.Cars.Car;
import Models.Databases.DatabaseOfUsers;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The <code>Auction</code> represents one of the core classes
 * It encompasses all the necessary attributes of each auction
 */
public class Auction implements Serializable {

    public Boolean isPremium;
    public int numberOfBids;
    private double minimumPrice = 0;
    public double highestBid = 0;

    private long auctionId;
    private long lastAuctionId;
    private final int maxBids = 3;

    private int numberOfWishLists;

    private Bid[] placedBids;

    public Car car;

    File lastAuctionIdFile = new File("src/Project/Files/lastAuctionIdFile.txt");

    public AuctionType auctionType;

    public Auction(Car car, AuctionType auctionType, double minimumPrice) {
        this.car = car;
        placedBids = new Bid[maxBids];
        this.numberOfBids = 0;
        this.isPremium = false;
        this.minimumPrice = minimumPrice;
        this.numberOfWishLists = 0;
        this.auctionId = getLastId() + 1L;
        storeLastId(lastAuctionId);

        this.auctionType = auctionType;
    }

    public Auction() {

    }

    /**
     * Stores information about auction and a bid whenever the bid is placed
     *
     * @param bid
     * @param auction
     */
    protected void storeToHistory(Bid bid, Auction auction) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        try {
            FileWriter fw = new FileWriter("src/Project/Files/history.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            String note = "Auction " + auction.getAuctionId() + ": " + bid.getAmount() + " has been placed by "
                    + DatabaseOfUsers.currentUser.getUserName() + " at " + formatter.format(date);
            bw.write(note);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void storeLastId(long lastId) {
        lastId += 1;
        try {
            FileWriter writer = new FileWriter(lastAuctionIdFile);
            writer.write(Long.toString(lastId));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error has occurred");
        }
    }

    private long getLastId() {
        String lastId = "0";
        try {
            Scanner reader = new Scanner(lastAuctionIdFile);
            lastId = reader.nextLine();
            lastId.replaceAll("\n", "");
            reader.close();
        } catch (IOException e) {
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

    /**
     * Part of a strategy design pattern
     * It decides, how should the bid be received by a particular auction
     *
     * @param bid
     * @param auction
     * @return
     */
    public String receiveBid(Bid bid, Auction auction) {
        String message = auctionType.receiveBid(bid, auction);
        return message;
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

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public int getNumberOfWishLists() {
        return numberOfWishLists;
    }

    public void setNumberOfWishLists(int numberOfWishLists) {
        this.numberOfWishLists = numberOfWishLists;
    }
}
