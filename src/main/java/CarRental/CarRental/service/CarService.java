package CarRental.CarRental.service;

import CarRental.CarRental.model.Car;
import CarRental.CarRental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) {
        // Lägg till eventuell affärslogik här innan du sparar bilen
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car car) {
        // Här kan du lägga till validering eller annan logik innan du uppdaterar bilen
        Car existingCar = carRepository.findById(Math.toIntExact(id)).orElse(null);
        if (existingCar != null) {
            // Uppdatera befintlig bil med information från den inkommande bilen
            existingCar.setBrand(car.getBrand());
            existingCar.setModel(car.getModel());
            existingCar.setRegistrationNumber(car.getRegistrationNumber());
            existingCar.setPricePerDay(car.getPricePerDay());
            existingCar.setBooked(car.isBooked());
            return carRepository.save(existingCar);
        } else {
            return null; // Bil med angivet id hittades inte
        }
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(Math.toIntExact(id));
    }
}

