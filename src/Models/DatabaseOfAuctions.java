package Models;

import java.io.File;
import java.util.ArrayList;

public class DatabaseOfAuctions {
    public ArrayList<Auction> auctions = new ArrayList<>();
    File auctionData = new File("src/Project/Files/auctionData.txt");

    public void displayAuctions(){
        for(Auction a : auctions){
            System.out.println("Auction id: " + a.auctionId + "\t Brand: " + a.car.brand + "\t Model: " + a.car.model);
        }
//        for(int i=0; i<auctions.size(); i++){
//            System.out.println("Auction id: " + auctions.get(i).auctionId + " Brand: " + auctions.get(i).car.brand + " Model: " + auctions.get(i).car.model);
//        }
    }
}
