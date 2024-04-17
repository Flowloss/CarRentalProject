-- Inserting a customer
INSERT INTO customer (name, address, email, phone)
VALUES ('John Doe', '123 Main St', 'john.doe@example.com', '123-456-7890'),
        ('John Doe', '123 Main St', 'john.doe@example.com', '123-456-7890');

-- Inserting a car
INSERT INTO car (brand, model, registrationNumber, pricePerDay, booked)
VALUES ('Toyota', 'Camry', 'ABC123', 50.00, true);

-- Inserting an order
INSERT INTO booking (orderDate, customer_id, car_id)
VALUES (NOW(), 1, 1);


