package Models;

import java.io.Serializable;

public class Car implements Serializable {
    public String brand;
    public String model;
    public double price;
    public int year;
    private int kilometers;
    private FUEL fuel;

    private enum FUEL {
        Gas,
        Diesel,
        Electricity
    }
    private String engine;
    private String power;
    private String transmission;
    private String color;

    public Car(String brand, String model, double price, int year, int kilometers, FUEL fuel, String engine, String power, String transmission, String color){
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

    public Car(String brand, String model, double price, int year){
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
    }

    public Car(String brand, String model){
        this.brand = brand;
        this.model = model;
    }
}
