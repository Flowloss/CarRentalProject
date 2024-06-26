package CarRental.CarRental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import CarRental.CarRental.model.Customer;

@Repository
@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
