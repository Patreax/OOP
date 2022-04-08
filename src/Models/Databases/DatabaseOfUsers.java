package Models.Databases;

//import Project.User;

import Models.Auctions.AuctionManager;
import Models.Observer;
import Models.Users.Admin;
import Models.Users.User;

import java.io.*;
import java.util.ArrayList;

public class DatabaseOfUsers extends Database{
    public static ArrayList<User> registeredUsers = new ArrayList<User>();
//    public ArrayList<User> activeUsers = new ArrayList<User>();
    public static File userData = new File("src/Project/Files/userData.txt");

    public static User currentUser;

    public static void storeObject(User user) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userData));
        registeredUsers.add(user);
        out.writeObject(registeredUsers);
        out.close();
    }

    public static void loadObjects() throws IOException, ClassNotFoundException{
        AuctionManager auctionManager = AuctionManager.getInstance();
        if(userData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(userData));
        for(User user : (ArrayList<User>)in.readObject()){
            registeredUsers.add(user);
            if(user instanceof Admin)
                auctionManager.register((Observer) user);
        }
        in.close();
    }
}
