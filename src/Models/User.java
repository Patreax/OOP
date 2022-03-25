package Models;

import java.io.*;
import java.util.Scanner;

public class User implements Serializable {
    private long userId;
    protected String userName;
    private String passWord;
    protected long lastId;
    File lastUserIdFile = new File("src/Project/Files/lastUserIdFile.txt");

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
            FileWriter writer = new FileWriter(lastUserIdFile);
            writer.write(Long.toString(lastId));
            writer.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
    }

    private long getLastId(){
        String lastId = "0";
        try {
            Scanner reader = new Scanner(lastUserIdFile);
            lastId = reader.nextLine();
            lastId.replaceAll("\n", "");
            reader.close();
        } catch (IOException e){
            System.out.println("Error has occurred");
        }
        this.lastId = Long.parseLong(lastId);
        return Long.parseLong(lastId);
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }

    public File getLastUserIdFile() {
        return lastUserIdFile;
    }

    public void setLastUserIdFile(File lastUserIdFile) {
        this.lastUserIdFile = lastUserIdFile;
    }
}
