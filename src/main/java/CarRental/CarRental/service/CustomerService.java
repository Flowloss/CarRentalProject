package CarRental.CarRental.service;

import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface{
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        if(customerRepository.existsById(id)){
            Optional<Customer> customer = customerRepository.findById(id);
            if(customer.isPresent()){
                return customer.get();
            }
        }
        return null; // need to set exception handling

    }

    @Override
    public Customer addCustomer(Customer customer) {
       if(customer.getId()== 0){
           customerRepository.save(customer);

       }
       return null; // exception handling
    }

    @Override
    public Customer upDateCustomer(Customer customer, int id) {
        Optional<Customer>customerToUpdate = customerRepository.findById(id);{
            if (customerToUpdate.isPresent()){

            }
            return customerRepository.save(customerToUpdate.get());
        }
       // exceptions

    }

    @Override
    public boolean deleteCustomer(int id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
