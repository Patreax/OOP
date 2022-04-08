package Models.Users;

import Models.PersonalGarage;
import Models.Wallet;

import java.io.Serializable;

public class PremiumUser extends Customer implements Serializable {

    private PersonalGarage garage = new PersonalGarage();

    public PremiumUser(String userName, String password){
        super(userName, password);

        this.userId = getLastId() + 1L;
        storeLastId(lastId);


        Customer.id++;
        this.walletId = id;
        this.garageId = id;
//        this.wallet = new Wallet();
    }
}
