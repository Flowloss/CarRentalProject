package CarRental.CarRental.controller;


import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.CarRepository;
import CarRental.CarRental.repositories.CustomerRepository;
import CarRental.CarRental.service.BookingService;
import CarRental.CarRental.service.BookingServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private static final Logger logger = Logger.getLogger(BookingController.class);


    @Autowired
    private BookingServiceInterface bookingServiceInterface;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/orders")
    public ResponseEntity<List<Bookings>> listOrders() {
        logger.info("Fetching all orders");
        List<Bookings> orders = bookingService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/deleteorder/{bookingId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int bookingId) {
        logger.info("Deleting order with ID: {}");
        bookingService.deleteOrder(bookingId);
        logger.info("Order deleted successfully");
        return ResponseEntity.ok("Order deleted.");
    }

    @PostMapping("/ordercar")
    public ResponseEntity<String> saveBooking(@RequestBody BookingRequest bookingRequest) {
        logger.info("Processing booking request");
        Customer customer = customerRepository.findById(bookingRequest.getCustomerId()).orElse(null);
        Car car = carRepository.findById(bookingRequest.getCarId()).orElse(null);

        if (customer == null || car == null) {
            logger.warn("Invalid customer or car ID provided in booking request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer or car not found.");
        }

        Bookings newBooking = new Bookings();
        newBooking.setCustomer(customer);
        newBooking.setCar(car);

        bookingService.ordercar(newBooking, customer, car);
        logger.info("Booking created successfully");
        return ResponseEntity.ok("Booking saved.");
    }

    @GetMapping("/myorders/{customerId}")
    public ResponseEntity<List<Bookings>> getMyOrders(@PathVariable int customerId) {
        logger.info("Fetching orders for customer with ID: {}");
        List<Bookings> myOrders = bookingService.getMyOrders(customerId);
        return ResponseEntity.ok(myOrders);
    }

    @PutMapping("/cancelorder/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        logger.info("Canceling booking with ID: {}");
        bookingService.cancelBooking(bookingId);
        logger.info("Booking canceled successfully");
        return ResponseEntity.ok("Booking canceled.");
    }
}

