package CarRental.CarRental.model;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "booking")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long orderNumber;

    @Column(name = "booking_date")
    private String bookingDate;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    // Constructors, getters, and setters
    // Constructors, getters, and setters
    // Constructors, getters, and setters

    // Constructors
    public Bookings() {
        this.orderNumber = generateOrderNumber();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    // Method to generate a random order number
    private Long generateOrderNumber() {
        Random random = new Random();
        long min = 100000000000L;
        long max = 999999999999L;
        return min + ((long) (random.nextDouble() * (max - min)));
    }
}

