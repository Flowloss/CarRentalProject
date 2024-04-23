package CarRental.CarRental.controller;

import CarRental.CarRental.model.Customer;
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
    CustomerServiceInterface customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping(value = "/addCustomer")

    public ResponseEntity<Customer> addNewCustomer(@Validated @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
        //customer =customerService.addCustomer(customer);
       //return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@Validated @RequestBody Customer customer, @PathVariable int id) {
        return ResponseEntity.ok(customerService.updateCustomer(customer, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
