package Models.Cars;

import Models.Auctions.Auction;

import java.io.Serializable;

/**
 * The <code>Car</code> class is a the main object of the auction
 * It contains all information about the auctioned car
 */
public class Car extends Auction implements Serializable {
    protected String brand;
    protected String model;
    protected double price;
    protected int year;
    protected int kilometers;
    protected String fuel;
    protected String engine;
    protected String power;
    protected String transmission;
    protected String color;

    public Car(String brand, String model, double price, int year, int kilometers, String fuel, String engine, String power, String transmission, String color) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
        this.kilometers = kilometers;
        this.fuel = fuel;
        this.engine = engine;
        this.power = power;
        this.transmission = transmission;
        this.color = color;
    }

    public Car(String brand, String model, double price, int year) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
    }

    public Car() {

    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getFuel() {
        return fuel;
    }

    public String getEngine() {
        return engine;
    }

    public String getPower() {
        return power;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getColor() {
        return color;
    }
}
