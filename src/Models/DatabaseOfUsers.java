package Models;

//import Project.User;

import java.io.*;
import java.util.ArrayList;

public class DatabaseOfUsers {
    public static ArrayList<User> registeredUsers = new ArrayList<User>();
    public ArrayList<User> activeUsers = new ArrayList<User>();
    public static File userData = new File("src/Project/Files/userData.txt");

    public static User currentUser;

    public void storeData(long userId, String userName, String passWord){
        try {
            userName.replaceAll("\\s","");
            passWord.replaceAll("\\s","");
            BufferedWriter writer = new BufferedWriter(new FileWriter(userData, true));
            writer.append(userId + " " + userName + " " + passWord + "\n");
            writer.close();
            System.out.println("Data has been stored");
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void storeObject(User user) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.userData));
        registeredUsers.add(user);
        out.writeObject(registeredUsers);
        out.close();
    }

    public void getObject(String userName, String userPassword) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(userData));
        for(User user : (ArrayList<User>)in.readObject()){
            System.out.println("Name: " + user.getUserName());
            System.out.println("Password: " + user.getPassword());
        }
        in.close();

    }

    public static void loadObjects() throws IOException, ClassNotFoundException{
        if(userData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(userData));
        for(User user : (ArrayList<User>)in.readObject()){
            registeredUsers.add(user);
        }
        in.close();
    }



}
