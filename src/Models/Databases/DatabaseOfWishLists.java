package Models.Databases;

import Models.Users.Customer;
import Models.WishList;

import java.io.*;
import java.util.ArrayList;

public class DatabaseOfWishLists extends Database {
    public static ArrayList<WishList> wishLists = new ArrayList<>();
    public static File wishListData = new File("src/Project/Files/wishListData.txt");

    public static void storeObject(WishList wishList) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(wishListData));
        wishLists.add(wishList);
        out.writeObject(wishLists);
        out.close();
    }

    public static void assignWishList() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        for (WishList wishList : wishLists) {
            if (currentCustomer.getWishListId() == wishList.getWishListId()) {
                currentCustomer.setWishList(wishList);
                break;
            }
        }
    }

    public void loadObjects() throws IOException, ClassNotFoundException {
        if (wishListData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(wishListData));
        for (WishList wishList : (ArrayList<WishList>) in.readObject()) {
            wishLists.add(wishList);
        }
        in.close();
    }
}
