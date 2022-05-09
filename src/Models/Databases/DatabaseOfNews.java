package Models.Databases;

import Models.News;
import Models.Users.Admin;
import Models.Users.Customer;
import Models.Users.User;
import Models.Wallet;

import java.io.*;
import java.util.ArrayList;

public class DatabaseOfNews extends Database{
    public static ArrayList<News> newsArrayList = new ArrayList<>();
    public static File newsData = new File("src/Project/Files/newsData.txt");

    /**
     * This method assigns the {@link Wallet} to it's {@link Customer} based on their ID
     */
    public static void assignNews() {
        Admin currentAdmin = (Admin) DatabaseOfUsers.currentUser;
        for (News news : newsArrayList) {
            if (currentAdmin.getNewsId() == news.getNewsId()) {
                currentAdmin.setNews(news);
                break;
            }
        }
    }

    public static void assignNewsToAdmins(){
        for(User user : DatabaseOfUsers.registeredUsers){
            if(user instanceof Admin){
                Admin admin = (Admin) user;
                for(News news : newsArrayList){
                    if(admin.getNewsId() == news.getNewsId()){
                        admin.setNews(news);
                    }
                }
            }
        }
    }
    public static void storeObject(News news) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(newsData));
        newsArrayList.add(news);
        out.writeObject(newsArrayList);
        out.close();
    }


    public void loadObjects() throws IOException, ClassNotFoundException {
        if (newsData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(newsData));
        for (News news : (ArrayList<News>) in.readObject()) {
            newsArrayList.add(news);
        }
        in.close();
    }
}
