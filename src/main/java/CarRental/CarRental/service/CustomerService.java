package CarRental.CarRental.service;

import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerService implements CustomerServiceInterface {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer addCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, int id) {
        Optional<Customer> customerToUpdate = customerRepository.findById(id);
        if (customerToUpdate.isPresent()) {
            Customer updatedCustomer = customerToUpdate.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setId(customer.getId());
            updatedCustomer.setPhone(customer.getPhone());
            return customerRepository.save(updatedCustomer);
        }
        return null; // Or throw an exception if customer not found
    }

    @Override
    public boolean deleteCustomer(int id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
