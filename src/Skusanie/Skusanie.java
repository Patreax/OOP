package Skusanie;

import java.io.*;
import java.util.Scanner;

public class Skusanie {
    public static void main(String[] args) {
        try {
            File file = new File("src/Skusanie/newFile.txt");
//            if(!file.exists())
            //file.createNewFile();
//            FileWriter writer = new FileWriter(file);
//            writer.write("Hello world");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append("Novy riadok\n");
            writer.close();
            Scanner reader = new Scanner(file);
            String riadok = reader.nextLine();
            System.out.println(riadok);
        } catch(IOException e){
            System.out.println("Error has occurred");
        }

    }
}
