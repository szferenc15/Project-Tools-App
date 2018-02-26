-- User data:
INSERT INTO user(id, first_name, last_name, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (1, 'Kertesz', 'Kaszon', 'kaszon', 'Dr123$,.-', 'kaszon@gmail.com', '06302003000', 'Budapest', '1990-01-01', true);
INSERT INTO user(id, first_name, last_name, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (2, 'Polozgai', 'Mate', 'mate', 'Dr123$,.-', 'mate@gmail.com', '06303004000', 'Budapest', '1990-01-02', true);
INSERT INTO user(id, first_name, last_name, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (3, 'Szebenyi', 'Gergely', 'geri', 'Dr123$,.-', 'geri@gmail.com', '06304005000', 'Budapest', '1990-01-03', true);
INSERT INTO user(id, first_name, last_name, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (4, 'Szendrei', 'Ferenc', 'feri', 'Dr123$,.-', 'feri@gmail.com', '06305006000', 'Budapest', '1990-01-04', true);

-- Event data:
INSERT INTO event(id, organizer, name, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (1, 'kaszon', 'Spiritualis hegymaszas', 'Japan, Kitayama, Fuji', 0, '2018-06-07', '09:00:00', '13:00:00', 
                2, 'ferfi', 'A Japanban levo szent hegy, a Fuji megmaszasa.');
INSERT INTO event(id, organizer, name, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (2, 'geri', 'Flugos futam', 'Kalifornia, Joshua Tree Nemzeti Park', 0, '2018-06-05', '13:00:00', '18:00:00', 
                2, 'ferfi', 'Labnap a Joshua Tree Nemzeti Parkban.');
        
-- Comment data:
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (1, 'Azt mondjak eleg jo onnan a kilatas. Hamarosan megtudjuk!', 1, 1);
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (2, 'Japan... erdekes hely, ideje felfedezni!', 1, 2);
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (3, 'Huh, ezt mar nagyon varom!', 2, 3);
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (4, 'Vegre egy kis edzes! 5 ora futas!', 2, 4);

-- SportCategory data:
INSERT INTO sport_category(id, category) 
        VALUES (1, 'Hegymaszas');
INSERT INTO sport_category(id, category) 
        VALUES (2, 'Futas');

-- EventSportCategory data:
INSERT INTO event_sport_category(id, category, event_id) 
        VALUES (1, 'Hegymaszas', 1);
INSERT INTO event_sport_category(id, category, event_id) 
        VALUES (2, 'Futas', 2);

-- User-Event join table data:
INSERT INTO user_event(user_id, event_id) 
        VALUES (1, 1);
INSERT INTO user_event(user_id, event_id) 
        VALUES (2, 1);
INSERT INTO user_event(user_id, event_id) 
        VALUES (3, 2);
INSERT INTO user_event(user_id, event_id) 
        VALUES (4, 2);