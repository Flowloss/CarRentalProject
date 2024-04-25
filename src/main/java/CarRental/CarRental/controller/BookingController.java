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

    private static final Logger logger = LogManager.getLogger("changes");


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
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/deleteorder/{bookingId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int bookingId) {
        bookingService.deleteOrder(bookingId);
        return ResponseEntity.ok("Order raderad.");
    }

    @PostMapping("/ordercar")
    public ResponseEntity<String> saveBooking(@RequestBody BookingRequest bookingRequest) {
        Customer customer = customerRepository.findById(bookingRequest.getCustomerId()).orElse(null);
        Car car = carRepository.findById(bookingRequest.getCarId()).orElse(null);

        if (customer == null || car == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kund eller bil hittades inte.");
        }

        Bookings newBooking = new Bookings();
        newBooking.setCustomer(customer);
        newBooking.setCar(car);

        bookingService.ordercar(newBooking, customer, car);
        return ResponseEntity.ok("Bokning sparad.");
    }

    @GetMapping("/myorders/{customerId}")
    public ResponseEntity<List<Bookings>> getMyOrders(@PathVariable int customerId) {
        List<Bookings> myOrders = bookingService.getMyOrders(customerId);
        return ResponseEntity.ok(myOrders);
    }

    @PutMapping("/cancelorder/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Bokning avbruten.");
    }
}

