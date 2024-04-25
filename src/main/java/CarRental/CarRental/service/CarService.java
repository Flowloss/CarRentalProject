package CarRental.CarRental.service;

import CarRental.CarRental.exceptions.ResourceNotFoundException;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getCar() {
        return carRepository.findAll();
    }

    @Override
    public Car getCar(int id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carRepository.existsById(id)));
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car, int id) {
        Optional<Car> carToUpdate = carRepository.findById(id);
        if (carToUpdate.isPresent()) {
            Car updatedCar = carToUpdate.get();
            updatedCar.setBrand(car.getBrand());
            updatedCar.setModel(car.getModel());
            updatedCar.setRegistrationNumber(car.getRegistrationNumber());
            updatedCar.setRegistrationNumber(car.getRegistrationNumber());
            return carRepository.save(updatedCar);
        }
        throw new ResourceNotFoundException("Car", "id", car.getId());
    }

    @Override
    public boolean deleteCar(int id) {
        try {
            if (carRepository.existsById(id)) {
                carRepository.deleteById(id);
                return true;
            }
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        throw new ResourceNotFoundException("Car", "id", id);
    }
}
