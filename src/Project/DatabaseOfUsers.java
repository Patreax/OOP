package Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class DatabaseOfUsers {
    public ArrayList<User> registeredUsers = new ArrayList<User>();
    public ArrayList<User> activeUsers = new ArrayList<User>();
    public File userData = new File("src/Project/Files/userData.txt");


    public void storeData(String userName, String passWord){
        try {

            userName.replaceAll("\\s","");
            passWord.replaceAll("\\s","");
            BufferedWriter writer = new BufferedWriter(new FileWriter(userData, true));
            writer.append(userName + " " + passWord + "\n");
            writer.close();
            System.out.println("Data has been stored");
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }





}
