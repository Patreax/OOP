package Models.Users;

import Models.Databases.DatabaseOfGarages;
import Models.Databases.DatabaseOfUsers;
import Models.Databases.DatabaseOfWallets;
import Models.PersonalGarage;
import Models.Wallet;
import Project.sample.Main;

import java.io.IOException;
import java.io.Serializable;

public class PremiumUser extends Customer implements Serializable {

//    private PersonalGarage garage = new PersonalGarage();

    public PremiumUser(String userName, String password){
        super(userName, password);

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

//        this.userId = ++userIdCounter;

        Customer.id++;
        this.walletId = id;
        this.garageId = id;
//        this.wallet = new Wallet();
    }

    public void logIn(User user) throws IOException {
        DatabaseOfUsers.currentUser = user;
        DatabaseOfWallets.assignWallet();
        DatabaseOfGarages.assignGarage();

        Main.mainInstance.changeScene("/GUI/MainScreen.fxml");
    }
}
