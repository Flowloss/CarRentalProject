package CarRental.CarRental.repositories;

import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer> {
    List<Bookings> findByCustomerId(int customerId);
    Bookings findTopByCarOrderByOrderDateDesc(Car car);
}
