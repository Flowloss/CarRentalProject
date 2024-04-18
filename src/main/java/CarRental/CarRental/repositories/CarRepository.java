package CarRental.CarRental.repositories;

import CarRental.CarRental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    // Anpassade metoder kan läggas till här om det behövs
}
