package CarRental.CarRental.controller;


import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.CarRepository;
import CarRental.CarRental.repositories.CustomerRepository;
import CarRental.CarRental.service.BookingService;
import CarRental.CarRental.service.BookingServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private static final Logger log = LogManager.getLogger(BookingController.class);


    @Autowired
    private BookingServiceInterface bookingServiceInterface;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/orders")
    public ResponseEntity<List<Bookings>> listOrders(){
        List<Bookings> orders = bookingServiceInterface.getAllOrders();
        log.info("Retrieved {} orders", orders.size());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/deleteorder/{bookingId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int bookingId) {
        bookingService.deleteOrder(bookingId);
        log.info("Deleted order with ID: {}", bookingId);
        return ResponseEntity.ok("Order raderad.");
    }

    @PostMapping("/ordercar")
    public ResponseEntity<String> saveBooking(@RequestBody BookingRequest bookingRequest) {
        Customer customer = customerRepository.findById(bookingRequest.getCustomerId()).orElse(null);
        Car car = carRepository.findById(bookingRequest.getCarId()).orElse(null);

        if (customer == null || car == null) {
            log.error("Kund eller bil hittades inte för bokning: {}", bookingRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kund eller bil hittades inte.");
        }

        Bookings newBooking = new Bookings();
        newBooking.setCustomer(customer);
        newBooking.setCar(car);

        bookingService.ordercar(newBooking, customer, car);
        log.info("Ny bokning skapad: {}", newBooking);
        return ResponseEntity.ok("Bokning sparad.");
    }

    @GetMapping("/myorders/{customerId}")
    public ResponseEntity<List<Bookings>> getMyOrders(@PathVariable int customerId) {
        List<Bookings> myOrders = bookingService.getMyOrders(customerId);
        log.info("Retrieved {} orders for customer with ID: {}", myOrders.size(), customerId);
        return ResponseEntity.ok(myOrders);
    }

    @PutMapping("/cancelorder/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        bookingService.cancelBooking(bookingId);
        log.info("Canceled booking with ID: {}", bookingId);
        return ResponseEntity.ok("Bokning avbruten.");
    }
}

