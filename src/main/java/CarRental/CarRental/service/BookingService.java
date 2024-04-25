package CarRental.CarRental.service;

import CarRental.CarRental.exceptions.BookingAlreadyCanceledException;
import CarRental.CarRental.exceptions.ResourceNotFoundException;
import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.model.Customer;
import CarRental.CarRental.repositories.BookingRepository;
import CarRental.CarRental.repositories.CarRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface {

    private static final Logger logger = LogManager.getLogger("changes");

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Bookings> getAllOrders() {
        try {
            return bookingRepository.findAll();
        } catch (Exception ex) {
            logger.error("Failed to get all orders", ex);
            throw ex; // Rethrow the exception to the caller
        }
    }

    public void deleteOrder(int id) {
        try {
            Optional<Bookings> optionalBooking = bookingRepository.findById(id);
            if (optionalBooking.isPresent()) {
                bookingRepository.deleteById(id);
                logger.info("Deleted order with ID: {}", id);
            } else {
                throw new ResourceNotFoundException("Order", "BookingId", id);
            }
        } catch (Exception ex) {
            logger.error("Failed to delete order with ID: {}", id, ex);
            throw ex; // Rethrow the exception to the caller
        }
    }


    public void ordercar(Bookings newBooking, Customer customer, Car car) {
        try {
            newBooking.setCustomer(customer);
            newBooking.setCar(car);
            newBooking.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));
            newBooking.setActive(true);
            car.setBooked(true);
            bookingRepository.save(newBooking);
            carRepository.save(car);
            logger.info("Booking created successfully: {}", newBooking);
        } catch (Exception ex) {
            logger.error("Failed to create booking", ex);
            throw ex; // Rethrow the exception to the caller
        }
    }

    public List<Bookings> getMyOrders(int customerId) {
        try {
            return bookingRepository.findByCustomerId(customerId);
        } catch (Exception ex) {
            logger.error("Failed to get orders for customer with ID: {}", customerId, ex);
            throw ex; // Rethrow the exception to the caller
        }
    }

    public void cancelBooking(int bookingId) {
        try {
            Optional<Bookings> optionalBooking = bookingRepository.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Bookings booking = optionalBooking.get();
                if (booking.isActive()) {
                    booking.setActive(false);
                    bookingRepository.save(booking);
                    logger.info("Canceled booking with ID: {}", bookingId);
                } else {
                    throw new BookingAlreadyCanceledException("CancelOrder", "BookingId", bookingId);
                }
            } else {
                throw new ResourceNotFoundException("CancelOrder", "BookingId", bookingId);
            }
        } catch (Exception ex) {
            logger.error("Failed to cancel booking with ID: {}", bookingId, ex);
            throw ex; // Rethrow the exception to the caller
        }
    }


}
