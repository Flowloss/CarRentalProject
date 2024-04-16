package CarRental.CarRental.service;

import CarRental.CarRental.model.Bookings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingServiceInterface {

    List<Bookings> getAllOrders();
    void saveOrder(Bookings newOrder);
    void deleteOrder(int id);
    Bookings getOrderById(int id);
}
