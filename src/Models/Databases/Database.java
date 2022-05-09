package Models.Databases;

import java.io.IOException;

/**
 * The <code>Database</code> is a super class of all the databases
 */
public abstract class Database {

    public static Database[] databases = {new DatabaseOfAuctions(), new DatabaseOfGarages(), new DatabaseOfUsers(), new DatabaseOfWallets(), new DatabaseOfWishLists(), new DatabaseOfNews()};

    public enum DatabaseType {
        User,
        Auction,
        Wallet,
        Garage,
        Wishlist,
        News
    }

    /**
     * The <code>loadObjects()</code> method is responsible for loading all the serialized objects belonging to each database
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadObjects() throws IOException, ClassNotFoundException {

    }
}
