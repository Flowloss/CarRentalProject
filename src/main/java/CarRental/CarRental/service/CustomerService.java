package CarRental.CarRental.service;

import CarRental.CarRental.exceptions.ResourceNotFoundException;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerRepository.existsById(id)));
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
            updatedCustomer.setPhone(customer.getPhone());
            return customerRepository.save(updatedCustomer);
        }
        throw new ResourceNotFoundException("Customer","id",customer.getId());
    }

    @Override
    public boolean deleteCustomer(int id) {
        try {
            if(customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
                return true;
            }
        } catch (EmptyResultDataAccessException ex) {
            return false;

        }
        throw new ResourceNotFoundException("Customer", "id", id);
    }
}
