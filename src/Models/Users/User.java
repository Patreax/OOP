package Models.Users;

import Models.PersonalGarage;

import java.io.*;
import java.util.Scanner;

public class User implements Serializable {
    protected long userId;
    protected String userName;
    protected String password;

    protected static long lastId;
    public static File lastUserIdFile = new File("src/Project/Files/lastUserIdFile.txt");

    protected static long userIdCounter = 0;

//    protected PersonalGarage garage = new PersonalGarage();


    public User(String userName, String password){
        this.userName = userName;
        this.password = password;

//        this.userId = getLastId() + 1L;
//        storeLastId(lastId);


//        this.userId = ++userIdCounter;
    }
    public User(){

    }

    public void logIn(User user) throws IOException {

    }


    protected static void storeLastId(long lastId){
        lastId +=1;
        try{
            FileWriter writer = new FileWriter(lastUserIdFile);
            writer.write(Long.toString(lastId));
            writer.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
    }

    protected static long getLastId(){
        String lastId = "0";
        try {
            Scanner reader = new Scanner(lastUserIdFile);
            lastId = reader.nextLine();
            lastId.replaceAll("\n", "");
            reader.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
        User.lastId = Long.parseLong(lastId);
        return Long.parseLong(lastId);
    }


    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
