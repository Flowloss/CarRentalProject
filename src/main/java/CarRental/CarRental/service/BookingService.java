package CarRental.CarRental.service;

import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.BookingRepository;
import CarRental.CarRental.repositories.CarRepository;
import CarRental.CarRental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Bookings> getBookingsByCarId(int carId) {
        return null;
    }

    @Override
    public List<Bookings> getAllOrders() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteOrder(int id) {
        Optional<Bookings> optionalBookings = bookingRepository.findById(id);
        if (optionalBookings.isPresent()) {
            bookingRepository.deleteById(id);
        }
    }

    public Bookings getOrderById(int id) {
        Optional<Bookings> optionalOrder = bookingRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    public void saveBooking(Bookings newBooking, Customer customer, Car car) {
        // Set the customer and car for the new booking
        newBooking.setCustomer(customer);
        newBooking.setCar(car);

        // Set the order date to the current date and time
        newBooking.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));

        // Save the booking
        bookingRepository.save(newBooking);
    }
}
