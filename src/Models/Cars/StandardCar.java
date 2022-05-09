package Models.Cars;

public class StandardCar extends Car {
    public StandardCar(String brand, String model, double price, int year, int kilometers, String fuel, String engine, String power, String transmission, String color) {
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
}
