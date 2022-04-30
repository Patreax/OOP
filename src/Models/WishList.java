package Models;

import Models.Auctions.Auction;
import Models.Users.Customer;

import java.io.Serializable;
import java.util.ArrayList;

public class WishList implements Serializable {
    private int wishListId;
    public ArrayList<Auction> wishListAuctions;

    public WishList() {
        this.wishListAuctions = new ArrayList<>();
        this.wishListId = Customer.id;
    }

    public int getWishListId() {
        return wishListId;
    }
}
