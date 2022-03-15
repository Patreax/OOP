package Project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
    public long userId;
    public String userName;
    private String passWord;
    private long lastId;
    File lastIdFile = new File("src/Project/Files/lastIdFile.txt");

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
        this.userId = getLastId() + 1L;
        storeLastId(lastId);
    }
    public User(){

    }


    private void storeLastId(long lastId){
        lastId +=1;
        try{
            FileWriter writer = new FileWriter(lastIdFile);
            writer.write(Long.toString(lastId));
            writer.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
    }

    private long getLastId(){
        String lastId = "0";
        try {
            Scanner reader = new Scanner(lastIdFile);
            lastId = reader.nextLine();
            lastId.replaceAll("\n", "");
            reader.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
        this.lastId = Long.parseLong(lastId);
        return Long.parseLong(lastId);
    }
}
