-- V1__create_payment_table.sql

CREATE TABLE payments (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    customer_cpf VARCHAR(11) NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    payment_amount DECIMAL(19,2) NOT NULL
);