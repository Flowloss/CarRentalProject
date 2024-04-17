package CarRental.CarRental.controller;


import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.CarRepository;
import CarRental.CarRental.repositories.CustomerRepository;
import CarRental.CarRental.service.BookingService;
import CarRental.CarRental.service.BookingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    // kunder: orderCar, cancelOrder, myorders, Admin: deleteOrder, orders

    private  BookingServiceInterface bookingServiceInterface;

    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    private  CarRepository carRepository;

    @Autowired
    private BookingService bookingService;



    @GetMapping("/orders")
    public ResponseEntity<List<Bookings>> listOrders(){
        List<Bookings> orders = bookingServiceInterface.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/deleteorder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        bookingService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully.");
    }

    @PostMapping("/ordercar")
    public ResponseEntity<String> saveBooking(@RequestBody BookingRequest bookingRequest) {
        // Retrieve customer and car from the database based on provided IDs
        Customer customer = customerRepository.findById(bookingRequest.getCustomerId()).orElse(null);
        Car car = carRepository.findById(bookingRequest.getCarId()).orElse(null);

        if (customer == null || car == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer or car not found.");
        }

        // Create a new booking
        Bookings newBooking = new Bookings();
        newBooking.setCustomer(customer);
        newBooking.setCar(car);

        // Save the booking
        bookingService.saveBooking(newBooking, customer, car);

        return ResponseEntity.ok("Booking saved successfully.");
    }
}
