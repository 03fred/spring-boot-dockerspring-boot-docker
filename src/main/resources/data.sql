CREATE TABLE cars(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	model VARCHAR(255),
	brand VARCHAR(255),
	car_license_plate VARCHAR(255),
	color VARCHAR(255),
	car_year INT,
	daily_value DECIMAL(10,2)
);

INSERT INTO cars(model, brand, car_license_plate, car_year, color, daily_value) VALUES (
	'CHEVROLET', 'CELTA', 'ABC-123', 2010, 'PRETO', 100.00);