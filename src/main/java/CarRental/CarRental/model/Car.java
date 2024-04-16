package CarRental.CarRental.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
