package Models;

//import Models.Customer;

public class PremiumUser extends Customer {

    private PersonalGarage garage = new PersonalGarage();
//    private Wallet wallet;

    public PremiumUser(String userName, String password){
        super(userName, password);

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

        this.wallet = new Wallet();
    }
}
