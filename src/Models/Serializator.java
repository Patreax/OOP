package Models;

import Models.Auctions.Auction;
import Models.Databases.*;
import Models.Users.User;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for serializing and saving the data
 * Generic approach allows this class to serialize multiple types of objects
 *
 * @param <O>
 */
public class Serializator<O> {
//    public void serialize(O object, ArrayList<O> arrayList, File file) throws IOException{
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
//        arrayList.add(object);
//        out.writeObject(arrayList);
//        out.close();
//    }

    /**
     * This method takes an object as a parameter and serialises every object respectively
     *
     * @param object
     * @throws IOException
     */
    public void serialize(O object) throws IOException {

        if (object instanceof User) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DatabaseOfUsers.userData));
            DatabaseOfUsers.registeredUsers.add((User) object);
            out.writeObject(DatabaseOfUsers.registeredUsers);
            out.close();
        } else if (object instanceof Auction) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DatabaseOfAuctions.auctionData));
            DatabaseOfAuctions.auctions.add((Auction) object);
            out.writeObject(DatabaseOfAuctions.auctionData);
            out.close();
        } else if (object instanceof Wallet) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DatabaseOfWallets.walletData));
            DatabaseOfWallets.wallets.add((Wallet) object);
            out.writeObject(DatabaseOfWallets.wallets);
            out.close();
        } else if (object instanceof PersonalGarage) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DatabaseOfGarages.garageData));
            DatabaseOfGarages.garages.add((PersonalGarage) object);
            out.writeObject(DatabaseOfGarages.garages);
            out.close();
        }
    }

//    public void saveData(ArrayList<O> arrayList, File file) throws IOException{
//        PrintWriter writer = new PrintWriter(file);
//        writer.print("");
//        writer.close();
//
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
//        out.writeObject(arrayList);
//        out.close();
//    }

    /**
     * This method saves all data contained in given database
     *
     * @param databaseType
     * @throws IOException
     */
    public void saveData(Database.DatabaseType databaseType) throws IOException {
        File file = null;
        ArrayList arrayList = null;
        switch (databaseType) {
            case User:
                file = DatabaseOfUsers.userData;
                arrayList = DatabaseOfUsers.registeredUsers;
                break;
            case Auction:
                file = DatabaseOfAuctions.auctionData;
                arrayList = DatabaseOfAuctions.auctions;
                break;
            case Wallet:
                file = DatabaseOfWallets.walletData;
                arrayList = DatabaseOfWallets.wallets;
                break;
            case Garage:
                file = DatabaseOfGarages.garageData;
                arrayList = DatabaseOfGarages.garages;
                break;
            default:
                break;
        }


        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(arrayList);
        out.close();
    }
}
