INSERT INTO customer (name, address, email, phone)
VALUES
('John Doe', '123 Main St', 'john.doe@example.com', '123-456-7890'),
('Jane Smith', '456 Elm St', 'jane.smith@example.com', '987-654-3210'),
('Alice Johnson', '789 Oak St', 'alice.johnson@example.com', '555-555-5555'),
('Bob Brown', '321 Pine St', 'bob.brown@example.com', '777-777-7777'),
('Emily Davis', '654 Birch St', 'emily.davis@example.com', '999-999-9999');

INSERT INTO cars (brand, model, registrationNumber, pricePerDay, booked)
VALUES
('Toyota', 'Camry', 'ABC123', 50.00, true),
('Honda', 'Accord', 'DEF456', 60.00, false),
('Ford', 'Focus', 'GHI789', 45.00, false),
('Chevrolet', 'Malibu', 'JKL012', 55.00, true),
('Nissan', 'Altima', 'MNO345', 65.00, false);

INSERT INTO booking (orderDate, customer_id, car_id, active)
VALUES
(NOW(), 1, 1, true),
(NOW(), 2, 2, true),
(NOW(), 3, 4, true);



