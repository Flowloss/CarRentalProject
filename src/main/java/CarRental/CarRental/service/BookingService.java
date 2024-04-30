package CarRental.CarRental.service;

import CarRental.CarRental.exceptions.BookingAlreadyCanceledException;
import CarRental.CarRental.exceptions.ResourceNotFoundException;
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

    public List<Bookings> getAllOrders() {
        return bookingRepository.findAll();
    }

    public void deleteOrder(int id) {
        Optional<Bookings> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Order", "BookingId", id);
        }
    }


    public void ordercar(Bookings newBooking, Customer customer, Car car) {

        newBooking.setCustomer(customer);
        newBooking.setCar(car);
        newBooking.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));
        newBooking.setActive(true);

        if (car.isBooked()) {
            throw new ResourceNotFoundException("Car", "id", car.getId());
        }

        Bookings previousBooking = bookingRepository.findTopByCarOrderByOrderDateDesc(car);
        if (previousBooking != null && previousBooking.isActive()) {
            throw new IllegalStateException("Previous booking for the car was not canceled");
        }

        car.setBooked(true);

        bookingRepository.save(newBooking);
        carRepository.save(car);
    }

    public List<Bookings> getMyOrders(int customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    public void cancelBooking(int bookingId) {
        Optional<Bookings> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            Bookings booking = optionalBooking.get();
            if (booking.isActive()) {
                booking.setActive(false);
                bookingRepository.save(booking);

                Car car = booking.getCar();
                car.setBooked(false);
                carRepository.save(car);

            } else {
                throw new BookingAlreadyCanceledException("CancelOrder", "BookingId", bookingId);
            }
        } else {
            throw new ResourceNotFoundException("CancelOrder", "BookingId", bookingId);
        }
    }



}
