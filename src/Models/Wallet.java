package Models;

public class Wallet {
    private double currency;
    private double bids;            // Virtual currency used in auctions
    private double exchangeConstant = 0.75;

    public Wallet(double currency){
        this.currency = currency;
        this.bids = 0;
    }

    public void exchange(double currency){
        this.bids += currency * exchangeConstant;
    }
}
