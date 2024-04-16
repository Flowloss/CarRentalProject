package CarRental.CarRental.model;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long orderNumber;


    @OneToOne
    private Customer customer;

    @OneToOne
    private Car car;




    public Order() {
    }
    public Order(int id, Long orderNumber) {
        this.id = id;
        this.orderNumber = orderNumber;
    }

    private Long generateOrderNumber() {
        Random random = new Random();
        long min = 100000000000L;
        long max = 999999999999L;
        return min + ((long) (random.nextDouble() * (max - min)));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }


}
