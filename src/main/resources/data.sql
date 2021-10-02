INSERT INTO user(id, email, first_name, last_name, password, image_link) VALUES (1,'jon','jon','Boss','$2a$12$UA3deYUynO5LMwxnlRq6uOVvSUMHWdQ0QyCqcD9ycoAlheaes5utq',NULL),
(2,'tod','tod','worker','$2a$12$UA3deYUynO5LMwxnlRq6uOVvSUMHWdQ0QyCqcD9ycoAlheaes5utq',NULL);
INSERT INTO role(id, name) VALUES (1,'ROLE_WORKER'),(2,'ROLE_BOSS');
INSERT INTO users_roles( user_id, role_id) VALUES (1,2),(2,1);
INSERT INTO cat_item(id, name, price, quantity)
VALUES (1,'mili',200,5),
(2, 'snoopy',400.76,6),
(3, 'cally',450.43,3),
(4, 'dazy',300.87,8),
 (5,'mimi',780.34,9),
 (6,'amy',390.76,6),
(7,'canny',500.87,7),
 (8,'billi',560.23,2),
 (9,'mizi',480.12,3),
(10,'kitty',600.76,4);