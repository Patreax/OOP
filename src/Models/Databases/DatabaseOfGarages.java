package Models.Databases;

import Models.Auctions.Auction;
import Models.PersonalGarage;
import Models.Users.Customer;
import Models.Users.User;
import Models.Wallet;

import java.io.*;
import java.util.ArrayList;

public class DatabaseOfGarages {
    public static ArrayList<PersonalGarage> garages = new ArrayList<>();
    public static File garageData = new File("src/Project/Files/garageData.txt");

    public static void assignGarage(){
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        for(PersonalGarage garage : garages){
            if(currentCustomer.getGarageId() == garage.getGarageId()){
                currentCustomer.setGarage(garage);
                break;
            }
        }
    }

    public static void storeObject(PersonalGarage garage) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(garageData));
        garages.add(garage);
        out.writeObject(garages);
        out.close();
    }

    public static void loadObjects() throws IOException, ClassNotFoundException{
        if(garageData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(garageData));
        for(PersonalGarage garage : (ArrayList<PersonalGarage>)in.readObject()){
            garages.add(garage);
        }
        in.close();
    }
}
