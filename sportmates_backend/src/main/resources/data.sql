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
INSERT INTO sport_category(id, category) 
        VALUES (3, 'Tenisz');
INSERT INTO sport_category(id, category) 
        VALUES (4, 'Íjászat');
INSERT INTO sport_category(id, category) 
        VALUES (5, 'Vadászat');
INSERT INTO sport_category(id, category) 
        VALUES (6, 'Szörfözés');
INSERT INTO sport_category(id, category) 
        VALUES (7, 'Síelés');
        
-- Event data:
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (1, 'kaszon', 'Hegymászás', 'Spirituális hegymászás', 'Japán', 'Kitayama', 'Fuji', 0, '2018-06-07', '09:00:00', '13:00:00', 
                2, 'férfi', 'A Japánban található szent hegy, a Fuji megmászása.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (2, 'geri', 'Futás', 'Flúgos futam', 'USA (Kalifornia)', 'San Bernardino County', 'Joshua Tree Nemzeti Park', 0, '2018-07-05', '13:00:00', '18:00:00', 
                2, 'férfi', 'Lábnap a Joshua Tree Nemzeti Parkban.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (3, 'mate', 'Tenisz', 'Taroló tenisz', 'USA (Florida)', 'Longboat Key', '220 Sands Point Road, Longboat Key Klub üdülő',
                1000, '2018-07-20', '09:00:00', '12:00:00', 2, 'férfi', 'A Longboat Key üdülő ámulatba ejtő 500 férőhelyes teniszpályáján való megütközés.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (4, 'feri', 'Íjászat', 'Tradicionális íjászat (gungdo)', 'Korea', 'Szöül', 'Mok-dong (Omokkjo állomás)',
                0, '2018-08-10', '08:00:00', '11:00:00', 2, 'férfi', 'A hihetetlen népszerű koreai tradicionális íjászat, azaz gungdo gyakorlása a Mok-dong-ban található íjász klubban.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (5, 'kaszon', 'Vadászat', 'Végítéleti vadászat', 'Oroszország (Szibéria)', 'Magadan', 'Kolima-hegyvidék',
                0, '2018-08-25', '05:00:00', '22:00:00', 2, 'férfi', 'Oroszország ázsiai részén, Északkelet-Szibériában a rettenetes Kolima-hegyvidék körüli medve vadászat.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (6, 'mate', 'Szörfözés', 'Extrém egzotikus szörfverseny', 'USA (Hawaii)', 'Honolulu', 'Waikiki tengerpart',
                300, '2018-08-30', '13:00:00', '19:00:00', 2, 'férfi', 'Oahu szigetén található Honolulu Waikiki tengerparján megrendezett veszélyes cápakergető szörfverseny.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (7, 'geri', 'Síelés', 'SportToolok száguldó síelése', 'Franciaország (Alpok)', 'Avoriaz', 'Avoriaz síparadicsom',
                500, '2018-12-15', '08:00:00', '20:00:00', 4, 'férfi', 'A SportToolok közös nagyszabású síelése az avoriazi síparadicsomban.');

-- Comment data:
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (1, 'Azt mondják elég jó onnan a kilátás. Hamarosan megtudjuk!', 1, 1); -- Hegymászás - kaszon
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (2, 'Japán egy érdekes hely, ideje felfedezni!', 1, 2); -- Hegymászás - mate

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (3, 'Ezt már nagyon várom! Hosszú futásnak nézünk elébe!', 2, 3); -- Flúgos futam - geri
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (4, '5 óra futás a kaktuszok között! Nem semmi!', 2, 4); -- Flúgos futam - feri

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (5, 'Longboat Key üdülő, ugye látod?!', 3, 2); -- Taroló tenisz - mate
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (6, '3 órás tennis? Na és mi lesz a látnivalókkal?', 3, 4); -- Taroló tenisz - feri

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (7, 'Azt mondják ez a klub kezdőknek van!', 4, 4); -- Tradicionális íjászat (gungdo) - feri
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (8, 'Mondanám, hogy versenyezzünk, de ha kezdőknek van...', 4, 1); -- Tradicionális íjászat (gungdo) - kaszon

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (9, 'Remélem felkészültél, húzós lesz!', 5, 1); -- Végítéleti vadászat - kaszon
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (10, 'Hova kerültem?!', 5, 3); -- Végítéleti vadászat - geri

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (11, 'Ez egy verseny lesz! (Cápa veszély, hahaha!)', 6, 2); -- Extrém egzotikus szörfverseny - mate
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (12, 'Vagy úgy! Találkozunk a deszkán!!', 6, 1); -- Extrém egzotikus szörfverseny - kaszon

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (13, 'Ugye tetszik? Avoriaz ebben az évben már most háromszoros díjnyertes síparadicsom! ', 7, 3); -- SportToolok száguldó síelése - geri
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (14, 'Mit szóltok egy versenyhez?! 650 km-es a pálya!', 7, 1); -- SportToolok száguldó síelése - kaszon
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (15, 'Oltárian néz ki! Alig várom!', 7, 2); -- SportToolok száguldó síelése - mate
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (16, 'Átlag 8 méteres hó?? Hűha!', 7, 4); -- SportToolok száguldó síelése - feri

-- Event-User join table data:
INSERT INTO event_user(event_id, user_id) 
        VALUES (1, 1); -- Hegymászás - kaszon
INSERT INTO event_user(event_id, user_id) 
        VALUES (1, 2); -- Hegymászás - mate

INSERT INTO event_user(event_id, user_id) 
        VALUES (2, 3); -- Flúgos futam - geri
INSERT INTO event_user(event_id, user_id) 
        VALUES (2, 4); -- Flúgos futam - feri

INSERT INTO event_user(event_id, user_id) 
        VALUES (3, 2); -- Taroló tenisz - mate
INSERT INTO event_user(event_id, user_id) 
        VALUES (3, 4); -- Taroló tenisz - feri

INSERT INTO event_user(event_id, user_id) 
        VALUES (4, 4); -- Tradicionális íjászat (gungdo) - feri
INSERT INTO event_user(event_id, user_id) 
        VALUES (4, 1); -- Tradicionális íjászat (gungdo) - kaszon

INSERT INTO event_user(event_id, user_id) 
        VALUES (5, 1); -- Végítéleti vadászat - kaszon
INSERT INTO event_user(event_id, user_id) 
        VALUES (5, 3); -- Végítéleti vadászat - geri

INSERT INTO event_user(event_id, user_id) 
        VALUES (6, 2); -- Extrém egzotikus szörfverseny - mate
INSERT INTO event_user(event_id, user_id) 
        VALUES (6, 1); -- Extrém egzotikus szörfverseny - kaszon

INSERT INTO event_user(event_id, user_id) 
        VALUES (7, 3); -- SportToolok száguldó síelése - geri
INSERT INTO event_user(event_id, user_id) 
        VALUES (7, 1); -- SportToolok száguldó síelése - kaszon
INSERT INTO event_user(event_id, user_id) 
        VALUES (7, 2); -- SportToolok száguldó síelése - mate
INSERT INTO event_user(event_id, user_id) 
        VALUES (7, 4); -- SportToolok száguldó síelése - feri
