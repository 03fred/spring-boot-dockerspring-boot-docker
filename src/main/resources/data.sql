CREATE TABLE cars(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	model VARCHAR(255),
	brand VARCHAR(255),
	car_license_plate VARCHAR(255),
	color VARCHAR(255),
	car_year INT,
	daily_value DECIMAL(10,2)
);

CREATE TABLE people(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	person_name VARCHAR(255),
	phone VARCHAR(50),
	email VARCHAR(255),
	cpf VARCHAR(11)
);

CREATE TABLE car_rental(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	person_id BIGINT,
    car_id BIGINT,
    total_value DECIMAL (10,2),
    initial_date DATE,
    final_date DATE,
    FOREIGN KEY (person_id) REFERENCES people(id),
    FOREIGN KEY (car_id) REFERENCES cars(id)
    
);


INSERT INTO cars(model, brand, car_license_plate, car_year, color, daily_value) VALUES (
	'CHEVROLET', 'CELTA', 'ABC-123', 2010, 'PRETO', 100.00);
	
INSERT INTO people(person_name, email, cpf, phone) VALUES ('NOME TESTE', 'nometeste@email.com', '11125671918', '(11) 98765-44327');

INSERT INTO car_rental(person_id, car_id, total_value, initial_date, final_date) VALUES (
	1, 1, 100, '2025-05-05', '2025-05-05');
	