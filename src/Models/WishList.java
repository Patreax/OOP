package Models;

import Models.Auctions.Auction;
import Models.Users.Customer;

import java.io.Serializable;
import java.util.ArrayList;

public class WishList<O> implements Serializable {
    public ArrayList<O> wishList;
    private int wishListId;

    public WishList() {
        this.wishList = new ArrayList<>();
        this.wishListId = Customer.id;
    }

    public int getWishListId() {
        return wishListId;
    }
}
