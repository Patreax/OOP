package Models.Users;

import Models.Databases.DatabaseOfGarages;
import Models.Databases.DatabaseOfUsers;
import Models.Databases.DatabaseOfWallets;
import Models.PersonalGarage;
import Models.Wallet;
import Project.sample.Main;

import java.io.IOException;

public class StandardUser extends Customer {

//    private PersonalGarage garage = new PersonalGarage();
//    private Wallet wallet;

    public StandardUser(String userName, String password){
        super(userName, password);

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

//        this.userId = ++userIdCounter;

//        this.wallet = new Wallet();
//        this.garage = new PersonalGarage();

        Customer.id++;
        this.walletId = id;
        this.garageId = id;


    }

    public void logIn(User user) throws IOException {
        DatabaseOfUsers.currentUser = user;
        DatabaseOfWallets.assignWallet();
        DatabaseOfGarages.assignGarage();

        Main.mainInstance.changeScene("/GUI/MainScreen.fxml");
    }

    public PersonalGarage getGarage() {
        return garage;
    }
    public Wallet getWallet() {
        return wallet;
    }
}
