package Models.Users;

import Models.PersonalGarage;
import Models.Wallet;

public class StandardUser extends Customer {

//    private PersonalGarage garage = new PersonalGarage();
//    private Wallet wallet;

    public StandardUser(String userName, String password){
        super(userName, password);

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

        this.wallet = new Wallet();
        this.garage = new PersonalGarage();

        Customer.id++;
        this.walletId = id;
        this.garageId = id;


    }

    public PersonalGarage getGarage() {
        return garage;
    }
    public Wallet getWallet() {
        return wallet;
    }
}
