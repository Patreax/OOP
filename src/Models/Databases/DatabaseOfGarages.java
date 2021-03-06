package Models.Databases;

import Models.PersonalGarage;
import Models.Users.Customer;

import java.io.*;
import java.util.ArrayList;

/**
 * The <code>DatabaseOfGarages</code> class is responsible for storing all the {@link PersonalGarage} assigned to {@link Customer}
 */
public class DatabaseOfGarages extends Database {
    public static ArrayList<PersonalGarage> garages = new ArrayList<>();
    public static File garageData = new File("src/Project/Files/garageData.txt");

    /**
     * This method assigns the {@link PersonalGarage} to it's {@link Customer} based on their ID
     */
    public static void assignGarage() {
        Customer currentCustomer = (Customer) DatabaseOfUsers.currentUser;
        for (PersonalGarage garage : garages) {
            if (currentCustomer.getGarageId() == garage.getGarageId()) {
                currentCustomer.setGarage(garage);
                break;
            }
        }
    }

    public static void storeObject(PersonalGarage garage) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(garageData));
        garages.add(garage);
        out.writeObject(garages);
        out.close();
    }

    public void loadObjects() throws IOException, ClassNotFoundException {
        if (garageData.length() == 0)
            return;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(garageData));
        for (PersonalGarage garage : (ArrayList<PersonalGarage>) in.readObject()) {
            garages.add(garage);
        }
        in.close();
    }
}
