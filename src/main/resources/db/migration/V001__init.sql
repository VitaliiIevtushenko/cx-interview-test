CREATE TABLE vehicles (
  vehicle_id SERIAL NOT NULL,
  name VARCHAR NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,

  CONSTRAINT vehicles_pk PRIMARY KEY (vehicle_id)
);

CREATE TABLE orders (
  order_id SERIAL NOT NULL,
  vehicle_id INT NOT NULL,
  date TIMESTAMP NOT NULL,

  CONSTRAINT orders_pk PRIMARY KEY (order_id),

  CONSTRAINT fk_orders_vehicles FOREIGN KEY (vehicle_id)
    REFERENCES vehicles (vehicle_id)
    ON UPDATE CASCADE ON DELETE RESTRICT
);