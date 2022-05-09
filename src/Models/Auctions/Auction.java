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
