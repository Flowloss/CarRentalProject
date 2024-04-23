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
public class CustomerController {
    @Autowired
    CustomerServiceInterface customerService;

    @GetMapping("/api/v1/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("/api/v1/addCustomer")
    public ResponseEntity<Customer> addNewCustomer(@Validated @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @PutMapping("/api/v1/updateCustomer/{id}")
    public ResponseEntity<Customer> upDateCustomer(@Validated @RequestBody Customer customer, @PathVariable int id) {
        return ResponseEntity.ok(customerService.updateCustomer(customer, id));
    }

    @DeleteMapping("/api/v1/deleteCustomer/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}