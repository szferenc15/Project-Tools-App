-- User data:
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (1, 'Kertész', 'Kászon', '../../../../resources/pictures/user_man_1.png', 'kaszon', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'kaszon@gmail.com', '06302003000', 'Budapest', '1990-01-01', true);
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (2, 'Polozgai', 'Máté', '../../../../resources/pictures/user_man_1.png', 'mate', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'mate@gmail.com', '06303004000', 'Budapest', '1990-01-02', true);
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (3, 'Szebenyi', 'Gergely', '../../../../resources/pictures/user_man_1.png', 'geri', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'geri@gmail.com', '06304005000', 'Budapest', '1990-01-03', true);
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (4, 'Szendrei', 'Ferenc', '../../../../resources/pictures/user_man_1.png', 'feri', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'feri@gmail.com', '06305006000', 'Budapest', '1990-01-04', true);

-- SportCategory data:
INSERT INTO sport_category(id, category) 
        VALUES (1, 'Hegymászás');
INSERT INTO sport_category(id, category) 
        VALUES (2, 'Futás');
        
-- Event data:
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (1, 'kaszon', 'Hegymászás', 'Spirituális hegymászás', 'Japán', 'Kitayama', 'Fuji', 0, '2018-06-07', '09:00:00', '13:00:00', 
                2, 'férfi', 'A Japánban található szent hegy, a Fuji megmászása.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (2, 'geri', 'Futás', 'Flúgos futam', 'Kalifornia', 'San Bernardino County', 'Joshua Tree Nemzeti Park', 0, '2018-06-05', '13:00:00', '18:00:00', 
                2, 'férfi', 'Lábnap a Joshua Tree Nemzeti Parkban.');
        
-- Comment data:
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (1, 'Azt mondják elég jó onnan a kilátás. Hamarosan megtudjuk!', 1, 1);
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (2, 'Japán... érdekes hely, ideje felfedezni!', 1, 2);
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (3, 'Huh, ezt már nagyon várom!', 2, 3);
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (4, 'Végre egy kis edzés! 5 óra futás!', 2, 4);

-- Event-User join table data:
INSERT INTO event_user(event_id, user_id) 
        VALUES (1, 1);
INSERT INTO event_user(event_id, user_id) 
        VALUES (1, 2);
INSERT INTO event_user(event_id, user_id) 
        VALUES (2, 3);
INSERT INTO event_user(event_id, user_id) 
        VALUES (2, 4);