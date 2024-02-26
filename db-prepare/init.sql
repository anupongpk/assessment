-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE lottery (
    id SERIAL PRIMARY KEY,
    ticket VARCHAR(6) NOT NULL,
    price INT NOT NULL,
    amount INT NOT NULL
);


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL
);


CREATE TABLE user_ticket (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    ticket_id INT NOT NULL REFERENCES lottery(id) ON DELETE CASCADE,
    quantity INT NOT NULL,
    price INT NOT NULL
);

-- Insert initial data
INSERT INTO lottery(ticket, price, amount) VALUES
    ('100121', 80, 1),
    ('101022', 80, 1),
    ('231312', 80, 1),
    ('219112', 80, 1);

INSERT INTO users(id, username) VALUES
    (1234567890, 'user1'),
    (1234567891, 'user2'),
    (1000000001, 'admin');

INSERT INTO user_ticket(user_id, ticket_id, quantity, price) VALUES
    (1234567890, 1, 1, 80),
    (1234567890, 2, 1, 80),
    (1234567891, 4, 1, 80),
    (1234567891, 3, 1, 80),
    (1234567890, 3, 1, 80);