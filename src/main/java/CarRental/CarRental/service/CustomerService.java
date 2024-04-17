package CarRental.CarRental.service;

import CarRental.CarRental.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    List<Customer>getCustomers();
    Customer getCustomer(int id);
    Customer addCustomer(Customer customer);
    Customer upDateCustomer(Customer customer, int id);

    boolean deleteCustomer(int id);
}
