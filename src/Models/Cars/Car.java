package Models.Cars;

import java.io.Serializable;

public class Car implements Serializable {
    protected String brand;
    protected String model;
    protected double price;
    protected int year;

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

//    public Car(String brand, String model, double price, int year, int kilometers, FUEL fuel, String engine, String power, String transmission, String color){
//        this.brand = brand;
//        this.model = model;
//        this.price = price;
//        this.year = year;
//        this.kilometers = kilometers;
//        this.fuel = fuel;
//        this.engine = engine;
//        this.power = power;
//        this.transmission = transmission;
//        this.color = color;
//    }

    public Car(String brand, String model, double price, int year){
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
    }

    public Car(){

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
}
