-- User data:
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (1, 'Kertész', 'Kászon', '../../../../resources/pictures/user_man_1.png', 'kaszon', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'kaszon@gmail.com', '06302003000', 'Budapest', '1990-01-01', true);
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (2, 'Polozgai', 'Máté', '../../../../resources/pictures/user_man_1.png', 'mate', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'mate@gmail.com', '06303004000', 'Budapest', '1990-01-02', true);
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (3, 'Szebenyi', 'Gergely', '../../../../resources/pictures/user_man_1.png', 'geri', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'geri@gmail.com', '06304005000', 'Budapest', '1990-01-03', true);
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (4, 'Szendrei', 'Ferenc', '../../../../resources/pictures/user_man_1.png', 'feri', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'feri@gmail.com', '06305006000', 'Budapest', '1990-01-04', true);
INSERT INTO user(id, first_name, last_name, picture_url, username, password, email, phone_number, city, birth_date, is_male) 
        VALUES (5, 'Hae', 'Soo', '../../../../resources/pictures/user_woman_1.png', 'soo', '$2a$10$AiPhjjKGpkixPA8LNtsGZOGkLX6eojo7rUkZSgiUZSAAuXtmSzciS', 'soo@gmail.com', '06208292016', 'Budapest', '1990-01-05', false);

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
        VALUES (7, 'Rali'); 
INSERT INTO sport_category(id, category) 
        VALUES (8, 'Harcművészet'); 
INSERT INTO sport_category(id, category) 
        VALUES (9, 'Labdajáték');
INSERT INTO sport_category(id, category) 
        VALUES (10, 'Légi sport');
INSERT INTO sport_category(id, category) 
        VALUES (11, 'Síelés');

-- Event data:
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (1, 'kaszon', 'Hegymászás', 'Spirituális Hegymászás', 'Japán', 'Kitayama', 'Fuji', 0, '2018-06-07', '09:00:00', '13:00:00', 
                2, 'férfi', 'A Japánban található szent hegy, a Fuji megmászása.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (2, 'geri', 'Futás', 'Flúgos Futam', 'USA (Kalifornia)', 'San Bernardino County', 'Joshua Tree Nemzeti Park',
                0, '2018-07-05', '13:00:00', '18:00:00', 2, 'férfi', 'Lábnap a Joshua Tree Nemzeti Parkban.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (3, 'mate', 'Tenisz', 'Tízórai Tomboló Tenisz', 'USA (Florida)', 'Longboat Key', '220 Sands Point Road, Longboat Key Klub üdülő',
                1000, '2018-07-10', '10:00:00', '12:00:00', 2, 'férfi', 'A Longboat Key üdülő ámulatba ejtő 500 férőhelyes teniszpályáján való megütközés.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (4, 'feri', 'Íjászat', 'Tradicionális Íjászat (Gungdo)', 'Korea', 'Szöül', 'Mok-dong (Omokkjo állomás)',
                0, '2018-08-01', '08:00:00', '11:00:00', 2, 'férfi', 'A hihetetlen népszerű koreai tradicionális íjászat, azaz Gungdo gyakorlása a Mok-dong-ban található íjász klubban.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (5, 'kaszon', 'Vadászat', 'Végítéleti Vadászat', 'Oroszország (Szibéria)', 'Magadan', 'Kolima-hegyvidék',
                0, '2018-08-25', '05:00:00', '22:00:00', 2, 'férfi', 'Oroszország ázsiai részén, Északkelet-Szibériában a rettenetes Kolima-hegyvidék körüli medve vadászat.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (6, 'mate', 'Szörfözés', 'Extrém Egzotikus Szörfverseny', 'USA (Hawaii)', 'Honolulu', 'Waikiki tengerpart',
                300, '2018-08-30', '13:00:00', '19:00:00', 2, 'férfi', 'Oahu szigetén található Honolulu Waikiki tengerparján megrendezett veszélyes cápakergető szörfverseny.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (7, 'feri', 'Rali', 'Reaktív Fáraó-Rali', 'Senegal', 'Dakar', 'Luxor',
                1250, '2018-08-05', '08:00:00', '20:00:00', 3, 'férfi', 'A híres Fáraó-ralin való részvétel Luxor állomástól kezdődően.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (8, 'kaszon', 'Harcművészet', 'Shaolin Kung Fu', 'USA (Nevada)', 'Las Vegas', 'Alexis Park üdülőhely',
                800, '2018-08-10', '10:30:00', '18:30:00', 3, 'férfi', 'Shaoling Kung Fu verseny az előkelő Alexis üdülőhelyen.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (9, 'mate', 'Labdajáték', 'Királyi Kin-Ball', 'Kanada', 'Charny', 'Omnikin Inc.',
                1100, '2018-08-15', '14:00:00', '15:30:00', 3, 'férfi', 'Kin-Ball meccs az Omnikin cég sportközpontjában');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (10, 'geri', 'Légi sport', 'Baromi Lehúzó Bungee Jumping', 'Kína', 'Macau', 'Macau Torony',
                950, '2018-08-15', '15:00:00', '16:00:00', 3, 'férfi', 'Macau Torony a világ legmagasabb bungee jumping-jának eszement kipróbálása.');
INSERT INTO event(id, organizer, category, name, country, city, locale, price, date_of_event, start, finish, headcount, audience, description) 
        VALUES (11, 'geri', 'Síelés', 'SportToolok Száguldó Síelése', 'Franciaország (Alpok)', 'Avoriaz', 'Avoriaz síparadicsom',
                500, '2018-12-15', '09:00:00', '20:00:00', 5, 'vegyes', 'A SportToolok közös nagyszabású síelése az avoriazi síparadicsomban.');

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
        VALUES (13, 'Dakhla-ig 362 km-t kell megtenni, készüljetek!', 7, 4); -- Reaktív Fáraó-Rali - feri
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (14, 'Találkozunk a célban!', 7, 1); -- Reaktív Fáraó-Rali - kaszon
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (15, 'Porvédőket el ne felejtsétek, sok lesz a por mögöttem!', 7, 3); -- Reaktív Fáraó-Rali - geri

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (16, 'Lesz Kung Fu/Wushu/Tai Chi, úgy hogy van választék.', 8, 1); -- Shaolin Kung Fu - kaszon
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (17, 'Két mérföld a reptérről, váó!', 8, 2); -- Shaolin Kung Fu - mate
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (18, '3.6 csillagos az üdülő, ennek ellenére jól néz ki.', 8, 3); -- Shaolin Kung Fu - geri

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (19, 'Fogadok ilyet még nem láttatok! 1.22 méter átmérőjű a labda!', 9, 2); -- Királyi Kin-Ball - mate
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (20, 'Remek! Még nem játszottam.', 9, 4); -- Királyi Kin-Ball - feri
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (21, 'Még én sem játszottam, habár 1986 óta játszák.', 9, 1); -- Királyi Kin-Ball - kaszon

INSERT INTO comment(id, message, event_id, user_id)
        VALUES (22, 'Zacsikat hozzatok! A világ legmagasabb tornyáról van szó, ahol van ilyen! (764 láb magas.)', 10, 3); -- Baromi Lehúzó Bungee Jumping - geri
INSERT INTO comment(id, message, event_id, user_id)
        VALUES (23, 'Egy óráig ezt akarod csinálni??', 10, 2); -- Baromi Lehúzó Bungee Jumping - mate
INSERT INTO comment(id, message, event_id, user_id)
        VALUES (24, '~6 másodperc míg visszapattanunk?!', 10, 4); -- Baromi Lehúzó Bungee Jumping - feri

INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (25, 'Ugye tetszik? Avoriaz ebben az évben már most háromszoros díjnyertes síparadicsom! ', 11, 3); -- SportToolok száguldó síelése - geri
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (26, 'Mit szóltok egy versenyhez?! 650 km-es a pálya!', 11, 1); -- SportToolok száguldó síelése - kaszon
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (27, 'Oltárian néz ki! Alig várom!', 11, 2); -- SportToolok száguldó síelése - mate
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (28, 'Átlag 8 méteres hó?? Hűha!', 11, 4); -- SportToolok száguldó síelése - feri
INSERT INTO comment(id, message, event_id, user_id) 
        VALUES (29, '나는 스키를 좋아한다!', 11, 5); -- SportToolok száguldó síelése - soo

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
        VALUES (7, 4); -- Reaktív Fáraó-Rali - feri
INSERT INTO event_user(event_id, user_id) 
        VALUES (7, 1); -- Reaktív Fáraó-Rali - kaszon
INSERT INTO event_user(event_id, user_id) 
        VALUES (7, 3); -- Reaktív Fáraó-Rali - geri

INSERT INTO event_user(event_id, user_id) 
        VALUES (8, 1); -- Shaolin Kung Fu - kaszon
INSERT INTO event_user(event_id, user_id) 
        VALUES (8, 2); -- Shaolin Kung Fu - mate
INSERT INTO event_user(event_id, user_id) 
        VALUES (8, 3); -- Shaolin Kung Fu - geri

INSERT INTO event_user(event_id, user_id) 
        VALUES (9, 2); -- Királyi Kin-Ball - mate
INSERT INTO event_user(event_id, user_id) 
        VALUES (9, 4); -- Királyi Kin-Ball - feri
INSERT INTO event_user(event_id, user_id) 
        VALUES (9, 1); -- Királyi Kin-Ball - kaszon

INSERT INTO event_user(event_id, user_id) 
        VALUES (10, 3); -- Baromi Lehúzó Bungee Jumping - geri
INSERT INTO event_user(event_id, user_id) 
        VALUES (10, 2); -- Baromi Lehúzó Bungee Jumping - mate
INSERT INTO event_user(event_id, user_id) 
        VALUES (10, 4); -- Baromi Lehúzó Bungee Jumping - feri

INSERT INTO event_user(event_id, user_id) 
        VALUES (11, 3); -- SportToolok száguldó síelése - geri
INSERT INTO event_user(event_id, user_id) 
        VALUES (11, 1); -- SportToolok száguldó síelése - kaszon
INSERT INTO event_user(event_id, user_id) 
        VALUES (11, 2); -- SportToolok száguldó síelése - mate
INSERT INTO event_user(event_id, user_id) 
        VALUES (11, 4); -- SportToolok száguldó síelése - feri
INSERT INTO event_user(event_id, user_id) 
        VALUES (11, 5); -- SportToolok száguldó síelése - soo