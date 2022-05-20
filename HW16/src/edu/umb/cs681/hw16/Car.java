package edu.umb.cs681.hw16;
import java.util.ArrayList;
import java.util.List;
public class Car {

    private String strMake, strModel;
    private int mileage, year,price, dominationCount;

    public Car(String strMake, String strModel, int year, int mileage, int price) {
        this.strMake = strMake;
        this.strModel = strModel;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public int getDominationCount() {
        return dominationCount;
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

    public void setDominationCount(List<Car> cars) {
        for (Car car : cars) {
            if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
                    && (car.getYear() <= this.getYear())) {
                this.dominationCount++;
            }
        }
        this.dominationCount--;
    }

    @Override
    public boolean equals(Object obj) {
        Car car = (Car) obj;
        if(strMake.equals(car.getstrMake()) && strModel.equals(car.getstrModel()) && year==car.getYear() && mileage==car.getMileage() && price==car.getPrice() && dominationCount==car.getDominationCount())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.strMake+" - "+this.strModel+" - "+this.year+" - Mileage:"+this.mileage+" - $"+this.price;
    }

    public static void main(String[] args) {
    	List<Car> obj = new ArrayList<>();
    	obj.add(new Car("Chrylser", "pacifica", 2010, 45, 23000));
        obj.add(new Car("Audi", "Q5", 2022, 70, 80000));
        obj.add(new Car("Chrylser", "Jeep", 2019,30 , 20000));
        obj.add(new Car("Ford", "Escape", 2022, 28, 11000));

       
        
        int maxMileage = obj.stream().parallel().map((Car car) -> car.getMileage())
				 .reduce(0, (result, carMileage) -> {
					 if (result == 0)				return carMileage;
					 else if (carMileage < result)	return carMileage;
					 else							return result;}	);
        System.out.println("The Minimum Car Mileage is: " + maxMileage);
        
        int maxPrice = obj.stream().parallel().map((Car car) -> car.getPrice())
               .reduce(0, (result, carPrice) -> {
				   	if (result == 0)			return carPrice;
					else if (carPrice > result)	return carPrice;
					else						return result;}	);
        System.out.println("The Maximum Car Price is: $" + maxPrice);
       
        Integer avgPrice = obj.stream().parallel().map((Car car) -> car.getPrice())
                .reduce(0, (result,carPrice) -> result+carPrice, (finalResult, intermediateResult) -> finalResult)/obj.size();
        System.out.println("The Average price of all Cars is: $" + avgPrice);
        
        Integer carMakerNum = obj.stream().parallel().map( (Car car)-> car.getstrMake() )
                .reduce(0,(result,carMake)-> ++result,
                        (finalResult,intermediateResult)-> finalResult + intermediateResult);
        System.out.println("The Total number of Car Makers is: "+carMakerNum);

        Integer carModelNum = obj.stream().parallel().map( (Car car)-> car.getstrModel() )
                .reduce(0,(result,carModel)-> ++result,
                        (finalResult,intermediateResult)-> finalResult + intermediateResult);
        System.out.println("The Total number of Car Models are: "+carModelNum);
        
    }

}