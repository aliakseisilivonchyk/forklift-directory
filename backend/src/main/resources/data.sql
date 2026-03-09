INSERT INTO app_user(first_name, second_name, last_name) VALUES ('Иванов', 'И.', 'И.');

INSERT INTO forklift(brand, number, carrying_capacity, app_user_id) VALUES ('Амкадор', '45-65 PH-1', 2.5, 1);
INSERT INTO forklift(brand, number, carrying_capacity, app_user_id) VALUES ('Амкадор 2', '45-65 PH-2', 3.5, 1);
INSERT INTO forklift(brand, number, carrying_capacity, app_user_id) VALUES ('Амкадор 3', '45-65 PH-3', 4.5, 1);
INSERT INTO forklift(brand, number, carrying_capacity, app_user_id) VALUES ('Амкадор 4', '45-65 PH-4', 5.5, 1);

INSERT INTO malfunction(start_timestamp, end_timestamp, description, forklift_id)
VALUES ('2024-04-16 14:12', '2024-04-16 15:47', 'сломался', 1);

INSERT INTO malfunction(start_timestamp, end_timestamp, description, forklift_id)
VALUES ('2024-04-17 14:12', '2024-04-17 15:47', 'сломался снова', 1);

INSERT INTO malfunction(start_timestamp, end_timestamp, description, forklift_id)
VALUES ('2024-04-18 14:12', '2024-04-18 15:47', 'сломался другой', 2);