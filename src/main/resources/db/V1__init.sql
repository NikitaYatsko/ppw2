CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL,
                       age INT NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL
);
