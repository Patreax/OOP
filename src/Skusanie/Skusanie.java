package Skusanie;

import Models.User;


import java.io.*;
import java.util.*;

public class Skusanie {

    public File objects = new File("src/Skusanie/serializacia.txt");
    public ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Skusanie skusanie = new Skusanie();

        skusanie.storeObject(skusanie.objects, new User("Meno1", "Heslo1"));
        skusanie.storeObject(skusanie.objects, new User("Meno2", "Heslo2"));
        skusanie.storeObject(skusanie.objects, new User("Meno3", "Heslo3"));

        skusanie.loadObject(skusanie.objects);

        Skusanie1 test = new Skusanie1();
        test.user = new User("Test1", "Heslo");
        test.displayInformation();
    }

    public void storeObject(File file, User user) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        users.add(user);
        out.writeObject(users);
        out.close();
    }
    public void loadObject(File file) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        for(User user : (ArrayList<User>)in.readObject()){
            System.out.println("Name: " + user.getUserName());
            System.out.println("Password: " + user.getPassWord());
        }
        in.close();
    }
}
