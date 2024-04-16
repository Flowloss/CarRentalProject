package CarRental.CarRental.service;

import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface {

    @Autowired
    private BookingRepository orderRepository;


    @Override
    public List<Bookings> getAllOrders() {
        return orderRepository.findAll();
    }


    //Gör klart när Car och Customer är klara
    @Override
    public void saveOrder(Bookings newOrder) {

    }


    @Override
    public void deleteOrder(int id) {

    }

    public Bookings getOrderById(int id) {
        Optional<Bookings> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }
}
