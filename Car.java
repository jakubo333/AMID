package com.example.laboop;

public class Car {



    private String brand;
    private String model;
    private int productionYear;


    public Car(String brand, String model, int productionYear) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }


    public String getCarDetails() {
        return "Samochód: " + brand + " " + model +
                ", Rok:" + productionYear;
    }
}
