package Models;

//import Models.Customer;

public class StandardUser extends Customer {

    private PersonalGarage garage = new PersonalGarage();
//    private Wallet wallet;

    public StandardUser(String userName, String password){
        super(userName, password);

        this.userId = getLastId() + 1L;
        storeLastId(lastId);

        this.wallet = new Wallet();
    }

    public PersonalGarage getGarage() {
        return garage;
    }
    public Wallet getWallet() {
        return wallet;
    }
}
