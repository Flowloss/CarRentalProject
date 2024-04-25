package CarRental.CarRental.service;

import CarRental.CarRental.model.Car;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CarServiceInterface {



        List<Car> getCar();
        Car getCar(int id);
        Car addCar(Car car);

        Car updateCar(Car car, int id);

        boolean deleteCar(int id);


    }

