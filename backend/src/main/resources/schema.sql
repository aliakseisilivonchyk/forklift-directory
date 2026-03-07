CREATE TABLE utilizer (
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
    utilizer_id INTEGER REFERENCES utilizer(id)
);

CREATE TABLE malfunction (
    id SERIAL PRIMARY KEY,
    start_timestamp TIMESTAMP DEFAULT NOW(),
    end_timestamp TIMESTAMP,
    description TEXT,
    forklift_id INTEGER REFERENCES forklift(id)
);