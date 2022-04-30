package Models;

import Models.Cars.Car;
import Models.Users.Customer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The object af this class is assigned to each {@link Customer} and holds an array of cars owned by given customer
 */
public class PersonalGarage implements Serializable {
    private ArrayList<Car> cars = new ArrayList<>();
    private int garageId;

    public PersonalGarage() {
        this.garageId = Customer.id;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getGarageId() {
        return garageId;
    }
}
