package CarRental.CarRental.controller;

import CarRental.CarRental.Exceptions.ResourceNotFoundException;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.CustomerRepository;
import CarRental.CarRental.service.CustomerService;
import CarRental.CarRental.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer) {

        if (customer.validate()) {

            //return ResponseEntity.ok(customerService.addCustomer(customer));
            Customer addedcustomer = customerService.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedcustomer);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable int id) {
        return ResponseEntity.ok(customerService.updateCustomer(customer, id));
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
