package Models;

import java.io.Serializable;

public class Wallet implements Serializable {
    private double currency = 0;
    private double bids = 0;            // Virtual currency used in auctions
    private final double exchangeConstant = 0.75;

    public Wallet(){
//        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
//        this.currency = currentCustomer.getWallet().getCurrency();
//        this.bids = currentCustomer.getWallet().getBids();
    }

    public void exchange(double currency){
        this.bids += currency * exchangeConstant;
        this.currency -= currency;
    }

    public void addCurrency(double currency){
        this.currency += currency;
    }

    public double getCurrency() {
        return currency;
    }

    public double getBids() {
        return bids;
    }
}
