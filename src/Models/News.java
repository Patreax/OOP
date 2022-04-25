package Models;

import Models.Users.Admin;

import java.util.ArrayList;

public class News {
    private ArrayList<String> news;
    private int newsId;

    public News(){
        this.news = new ArrayList<>();
        this.newsId = Admin.id;
    }
}
