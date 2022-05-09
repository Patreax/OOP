package Models;

import Models.Users.Admin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The object of this class is assigned to each {@link Observer} and holds string messages
 */
public class News implements Serializable {
    private ArrayList<String> news = new ArrayList<>();
    private int newsId;

    public News() {
//        this.news = new ArrayList<>();
        this.newsId = Admin.id;
    }

    public ArrayList<String> getNews() {
        return news;
    }

    public int getNewsId() {
        return newsId;
    }
}
