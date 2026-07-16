--liquibase formatted sql
--changeset CarApi:1


CREATE TABLE car_brands (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE engines (
    id BIGSERIAL PRIMARY KEY,
    producer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    horsepower INTEGER NOT NULL,
    torque INTEGER NOT NULL,
    engine_number VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE owners (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    e_mail VARCHAR(255) NOT NULL,
    pesel VARCHAR(11) NOT NULL UNIQUE
);

CREATE TABLE cars (
    id BIGSERIAL PRIMARY KEY,
    producer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year_of_production INTEGER NOT NULL,
    registration_number VARCHAR(10) NOT NULL UNIQUE,
    vin_number VARCHAR(17) NOT NULL UNIQUE,
    color VARCHAR(255) NOT NULL,

    engine_fk BIGINT,
    owner_fk BIGINT,

    CONSTRAINT fk_cars_engine
        FOREIGN KEY (engine_fk)
        REFERENCES engines(id),

    CONSTRAINT fk_cars_owner
        FOREIGN KEY (owner_fk)
        REFERENCES owners(id)
);

