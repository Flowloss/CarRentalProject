-- Inserting a customer
INSERT INTO customer (name, address, email, phone)
VALUES ('John Doe', '123 Main St', 'john.doe@example.com', '123-456-7890');

-- Inserting a car
INSERT INTO car (make, model, registrationNumber, pricePerDay)
VALUES ('Toyota', 'Camry', 'ABC123', 50.00);

-- Inserting an order
INSERT INTO booking (orderNumber, booking_date, customer_id, car_id)
VALUES (1234567890, '2024-04-16', 1, 1);

