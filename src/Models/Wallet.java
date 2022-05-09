package Models;

import Models.Exceptions.TooMuchCurrencyException;
import Models.Users.Customer;

import java.io.Serializable;

/**
 * Objects of this class are assigned to each {@link Customer} and hold information about given customer's currency and bids (special currency)
 */
public class Wallet implements Serializable {
    private double currency = 0;
    private double bids = 0;                         // Virtual currency used in auctions
    private final double exchangeConstant = 0.75;
    private int walletId;

    public Wallet() {
        this.walletId = Customer.id;
    }

    /**
     * Exchanges currency to bids
     *
     * @param currency
     * @throws TooMuchCurrencyException
     */
    public void exchange(double currency) throws TooMuchCurrencyException {
        if (currency > this.currency)
            throw new TooMuchCurrencyException();

        this.bids += currency * exchangeConstant;
        this.currency -= currency;
    }

    public void addCurrency(double currency) {
        this.currency += currency;
    }

    public double getCurrency() {
        return currency;
    }

    public double getBids() {
        return bids;
    }

    public void setBids(double bids) {
        this.bids = bids;
    }

    public int getWalletId() {
        return walletId;
    }
}
