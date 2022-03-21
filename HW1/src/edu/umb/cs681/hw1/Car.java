package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Car {

    private String strMake, strModel;
    private int mileage, year;
    private int price;
    private int dominationRank;

    public Car(String strMake, String strModel, int year, int mileage, int price) {
        this.strMake = strMake;
        this.strModel = strModel;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public int getDominationCount() {
        return dominationRank;
    }
    public String getstrMake() {
        return strMake;
    }
    public String getstrModel() {
        return strModel;
    }

    public int getYear() {
        return year;
    }
    public int getPrice() {
        return price;
    }
    public int getMileage() {
        return mileage;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        for (Car car : cars) {
            if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
                    && (car.getYear() <= this.getYear())) {
                this.dominationRank++;
            }
        }
        this.dominationRank--;
    }

    @Override
    public boolean equals(Object obj) {
        Car car = (Car) obj;
        if(strMake.equals(car.getstrMake()) && strModel.equals(car.getstrModel()) && year==car.getYear() && mileage==car.getMileage() && price==car.getPrice() && dominationRank==car.getDominationCount())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.strMake+" - "+this.strModel+" - "+this.year+" - Mileage:"+this.mileage+" - $"+this.price;
    }

    public static void main(String[] args) {
        List<Car> objCar = new ArrayList<>();
        objCar.add(new Car("Chrylser", "pacifica", 2010, 45, 23000));
        objCar.add(new Car("Audi", "Q5", 2022, 70, 80000));
        objCar.add(new Car("Chrylser", "Jeep", 2019,30 , 20000));
        objCar.add(new Car("Ford", "Escape", 2022, 28, 11000));

        System.out.println("\nCars order by Year:");
        List<Car> orderByYear=objCar.stream().sorted(Comparator.comparingInt(Car::getYear)).collect(Collectors.toList());
        orderByYear.forEach(System.out::println);
        
        System.out.println("\nCars order by Price:");
        List<Car> orderByPrice=objCar.stream().sorted(Comparator.comparingInt(Car::getPrice)).collect(Collectors.toList());
        orderByPrice.forEach(System.out::println);
        
        System.out.println("\nCars order by Mileage:");
        List<Car> orderByMileage=objCar.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(Collectors.toList());
        orderByMileage.forEach(System.out::println);

        System.out.println("\nCars order by Domination rank:");
        List<Car> orderByDomination=objCar.stream().sorted(Comparator.comparingInt(Car::getDominationCount)).collect(Collectors.toList());
        orderByDomination.forEach(System.out::println);
    }
}