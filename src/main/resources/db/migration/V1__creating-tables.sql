CREATE TABLE customers (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    createAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cabs (
    id UUID PRIMARY KEY,
    model VARCHAR NOT NULL,
    color VARCHAR NOT NULL,
    plateNum VARCHAR NOT NULL
);

CREATE TYPE METHOD AS ENUM ('CREDIT_CARD', 'CASH', 'PIX');

CREATE TABLE payments (
    id UUID PRIMARY KEY,
    method METHOD NOT NULL,
    amount MONEY NOT NULL,
    createAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE drivers (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    cabID UUID,
    dateOfBirth DATE NOT NULL,
    createAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cabID) REFERENCES cabs(id) ON DELETE CASCADE
);

CREATE TYPE STATUS AS ENUM ('REQUESTED', 'ACCEPTED', 'STARTED', 'COMPLETED', 'CANCELLED');

CREATE TABLE trips(
    id UUID PRIMARY KEY,
    customerID UUID,
    driverID UUID,
    paymentID UUID,
    status STATUS NOT NULL,
    source VARCHAR NOT NULL,
    destination VARCHAR NOT NULL,
    createAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (paymentID) REFERENCES payments(id),
    FOREIGN KEY (driverID) REFERENCES drivers(id),
    FOREIGN KEY (customerID) REFERENCES customers(id)
);

CREATE TABLE ratings (
    id UUID PRIMARY KEY,
    customerID UUID,
    driverID UUID,
    tripID UUID,
    rating INT NOT NULL,
    feedback VARCHAR(255),

    FOREIGN KEY (tripID) REFERENCES trips(id),
    FOREIGN KEY (driverID) REFERENCES drivers(id),
    FOREIGN KEY (customerID) REFERENCES customers(id)
);