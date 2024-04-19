package CarRental.CarRental.model;


import jakarta.persistence.*;

        @Entity
        public class Car {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private int id;

            private String brand;
            private String model;
            private String registrationNumber;
            private double pricePerDay;
            private boolean booked;

            // Getters and setters

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;

            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getRegistrationNumber() {
                return registrationNumber;
            }

            public void setRegistrationNumber(String registrationNumber) {
                this.registrationNumber = registrationNumber;
            }

            public double getPricePerDay() {
                return pricePerDay;
            }

            public void setPricePerDay(double pricePerDay) {
                this.pricePerDay = pricePerDay;
            }


            public boolean isBooked() {
                return booked;
            }

            public void setBooked(boolean booked) {
                this.booked = booked;
            }
<<<<<<< HEAD
        }
=======

        }

<<<<<<< HEAD

        }

>>>>>>> 153c52c09b511e2c5f4bb0df4aac83bb96d077e7
=======
>>>>>>> 30acaa7944509dbbb01cf8b274cc0bda99ecc5d4
