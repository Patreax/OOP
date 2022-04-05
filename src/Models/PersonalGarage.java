package Models;

import Models.Cars.Car;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonalGarage implements Serializable {
    private ArrayList<Car> cars = new ArrayList<>();

    public ArrayList<Car> getCars() {
        return cars;
    }
}
