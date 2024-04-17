package CarRental.CarRental.controller;


    public class BookingRequest {
        private int customerId;
        private int carId;

        // Getters and setters
        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }
    }
