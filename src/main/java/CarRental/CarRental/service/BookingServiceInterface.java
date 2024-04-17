package CarRental.CarRental.service;

import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.model.Car;
import CarRental.CarRental.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingServiceInterface {

    List<Bookings> getBookingsByCarId(int carId);
    List<Bookings> getAllOrders();
    void saveBooking(Bookings newBooking, Customer customer, Car car);
    void deleteOrder(int id);
    Bookings getOrderById(int id);
}
