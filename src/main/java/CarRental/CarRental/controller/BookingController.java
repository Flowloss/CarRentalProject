package CarRental.CarRental.controller;


import CarRental.CarRental.model.Bookings;
import CarRental.CarRental.service.BookingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class BookingController {

    // kudner: orderCar, cancelOrder, myorders, Admin: deleteOrder, orders

    @Autowired
    private BookingServiceInterface orderServiceInterface;

    @GetMapping("/orders")
    public ResponseEntity<List<Bookings>> listOrders(){
        List<Bookings> orders = orderServiceInterface.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
