package CarRental.CarRental.service;

import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.BookingRepository;
import CarRental.CarRental.repositories.CarRepository;
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

    public void ordercar(Bookings newBooking, Customer customer, Car car) {
        // Set the customer and car for the new booking
        newBooking.setCustomer(customer);
        newBooking.setCar(car);

        // Set the order date to the current date and time
        newBooking.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));

        // Set the booking as active
        newBooking.setActive(true);
        car.setBooked(true);

        // Save the booking
        bookingRepository.save(newBooking);
        carRepository.save(car);
    }

    public List<Bookings> getMyOrders(int customerId) {
        // Retrieve all orders (active and inactive) for the specified customer
        return bookingRepository.findByCustomerId(customerId);
    }

    public void cancelBooking(int bookingId) {
        //avaktivera bilkoppling efter bokning avbryten + customer
        Optional<Bookings> optionalBooking = bookingRepository.findById(bookingId);
        optionalBooking.ifPresent(booking -> {
            booking.setActive(false);
            bookingRepository.save(booking);
        });
    }
}
