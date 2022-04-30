package Models.Databases;


import Models.Users.Customer;
import Models.Wallet;

import java.io.*;
import java.util.ArrayList;

/**
 * The <code>DatabaseOfWallets</code> class is responsible for storing all the {@link Wallet} assigned to {@link Customer}
 */
public class DatabaseOfWallets extends Database {
    public static ArrayList<Wallet> wallets = new ArrayList<>();
    public static File walletData = new File("src/Project/Files/walletData.txt");

    /**
     * This method assigns the {@link Wallet} to it's {@link Customer} based on their ID
     */
    public static void assignWallet() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        for (Wallet wallet : wallets) {
            if (currentCustomer.getWalletId() == wallet.getWalletId()) {
                currentCustomer.setWallet(wallet);
                break;
            }
        }
    }

    public static void storeObject(Wallet wallet) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(walletData));
        wallets.add(wallet);
        out.writeObject(wallets);
        out.close();
    }


    public void loadObjects() throws IOException, ClassNotFoundException {
        if (walletData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(walletData));
        for (Wallet wallet : (ArrayList<Wallet>) in.readObject()) {
            wallets.add(wallet);
        }
        in.close();
    }
}
