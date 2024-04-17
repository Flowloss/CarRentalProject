package CarRental.CarRental.repositories;

import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
