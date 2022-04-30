package Models;

import Models.Users.Admin;

import java.util.ArrayList;

/**
 * The object of this class is assigned to each {@link Observer} and holds string messages
 */
public class News {
    private ArrayList<String> news;
    private int newsId;

    public News() {
        this.news = new ArrayList<>();
        this.newsId = Admin.id;
    }
}
