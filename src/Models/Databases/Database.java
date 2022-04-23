package Models.Databases;

import java.io.IOException;

public abstract class Database {

    public static Database[] databases = {new DatabaseOfAuctions(), new DatabaseOfGarages(), new DatabaseOfUsers(), new DatabaseOfWallets()};

    public void loadObjects() throws IOException, ClassNotFoundException{

    }

}
