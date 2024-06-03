CREATE TABLE customers (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cabs (
    id UUID PRIMARY KEY,
    model VARCHAR NOT NULL,
    color VARCHAR NOT NULL,
    plate_num VARCHAR NOT NULL
);

CREATE TYPE method AS ENUM ('CREDIT_CARD', 'CASH', 'PIX');

CREATE TABLE payments (
    id UUID PRIMARY KEY,
    method method NOT NULL,
    amount MONEY NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE drivers (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    cab_id UUID,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cab_id) REFERENCES cabs(id) ON DELETE CASCADE
);

CREATE TYPE status AS ENUM ('REQUESTED', 'ACCEPTED', 'STARTED', 'COMPLETED', 'CANCELLED');

CREATE TABLE trips(
    id UUID PRIMARY KEY,
    customer_id UUID,
    driver_id UUID,
    payment_id UUID,
    status status NOT NULL,
    source VARCHAR NOT NULL,
    destination VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (payment_id) REFERENCES payments(id),
    FOREIGN KEY (driver_id) REFERENCES drivers(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE ratings (
    id UUID PRIMARY KEY,
    customer_id UUID,
    driver_id UUID,
    trip_id UUID,
    rating INT NOT NULL,
    feedback VARCHAR(255),

    FOREIGN KEY (trip_id) REFERENCES trips(id),
    FOREIGN KEY (driver_id) REFERENCES drivers(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
