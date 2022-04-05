package Models;

import Models.Auctions.Auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DatabaseOfAuctions {
    public static ArrayList<Auction> auctions = new ArrayList<>();
    public static File auctionData = new File("src/Project/Files/auctionData.txt");

    public static void loadObjects() throws IOException, ClassNotFoundException{
        if(auctionData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(auctionData));
        for(Auction auction : (ArrayList<Auction>)in.readObject()){
            auctions.add(auction);
        }
        in.close();
    }
}
