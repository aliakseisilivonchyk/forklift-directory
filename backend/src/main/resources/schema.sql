DROP TABLE IF EXISTS malfunction;
DROP TABLE IF EXISTS forklift;
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR,
    second_name VARCHAR,
    last_name VARCHAR
);

CREATE TABLE forklift (
    id SERIAL PRIMARY KEY,
    brand VARCHAR,
    number VARCHAR,
    carrying_capacity NUMERIC(10, 2),
    is_active BOOLEAN DEFAULT TRUE,
    update_timestamp TIMESTAMP DEFAULT NOW(),
    app_user_id INTEGER REFERENCES app_user(id)
);

CREATE TABLE malfunction (
    id SERIAL PRIMARY KEY,
    start_timestamp TIMESTAMP DEFAULT NOW(),
    end_timestamp TIMESTAMP,
    description TEXT,
    forklift_id INTEGER REFERENCES forklift(id)
);