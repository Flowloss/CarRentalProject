package CarRental.CarRental.controller;

import CarRental.CarRental.model.Customer;
import CarRental.CarRental.service.CustomerServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerServiceInterface customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        logger.info("Fetching all customers");
        List<Customer> customers = customerService.getCustomers();
        logger.info("Returning {} customers", customers.size());
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addNewCustomer(@Validated @RequestBody Customer customer) {
        logger.info("Adding new customer: {}", customer);
        Customer addedCustomer = customerService.addCustomer(customer);
        logger.info("Customer added successfully: {}", addedCustomer);
        return ResponseEntity.ok(addedCustomer);
    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<Customer> upDateCustomer(@Validated @RequestBody Customer customer, @PathVariable int id) {
        logger.info("Updating customer with ID: {}", id);
        Customer updatedCustomer = customerService.updateCustomer(customer, id);
        if (updatedCustomer != null) {
            logger.info("Customer updated successfully: {}", updatedCustomer);
        } else {
            logger.warn("Customer with ID {} not found", id);
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable int id) {
        logger.info("Deleting customer with ID: {}", id);
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            logger.info("Customer with ID {} deleted successfully", id);
        } else {
            logger.warn("Customer with ID {} not found", id);
        }
        return ResponseEntity.ok(deleted);
    }
}