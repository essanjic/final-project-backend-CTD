use cenapp;

INSERT INTO role (id, name)
VALUES (1, "USER"), (2, "ADMINISTRATOR");

INSERT INTO country (id, name, abbreviation)
VALUES
    (1, "Uruguay", "UY"),
    (2, "Colombia", "CO"),
    (3, "Argentina", "AR"),
    (4, "Estados Unidos", "US"),
    (5, "Canadá", "CA"),
    (6, "Reino Unido", "EN");

INSERT INTO category(id,description,name,url)
VALUES(1, "Soy una descripcion", "soy un nombre", "www.soyprueba.com");


INSERT INTO city (id, name, abbreviation, country_id)
VALUES
    (1, "Salto", "SAT" , 3),
    (2, "Medellin", "MDE", 2),
    (3, "Buenos Aires", "BUE", 3),
    (4, "Montevideo", "MNT", 1),
    (5, "Bogotá D.C", "BOG", 2),
    (6, "Nueva York", "NYC", 4),
    (7, "Los Ángeles", "LAC", 4),
    (8, "Chicago", "CHI", 4),
    (9, "Houston", "HOU", 4),
    (10, "Miami", "MIA", 4),
    (11, "Toronto", "TOR", 5),
    (12, "Londres", "LON", 6);

INSERT INTO restaurant (id, name, description, short_description, address, `zone/street`, rating, admin_id, day_disponibility, parking, live_music, events, terraza, active, area, average_score, latitude, longitude, cancellation_policies, hse_policies, site_policies, category_id, city_id)
VALUES (1, "Prueba 1", "Hola soy yo askjdlaksjdlaksj naskdnasknd", "Hola soy ...", "Calle 123", "Zona prueba", 4.5, 3, '{
    "monday": true,
    "tuesday": true,
    "wednesday": true,
    "thursday": true,
    "friday": true,
    "saturday": true,
    "sunday": false
  }',
        true,
        true,
        true,
        false,
        1,
        "10 m2",
        4.5,  -- Cambié AVG rating a 4.5, ya que AVG rating no es válido aquí
        "prueba1",
        "Prueba2",
        "soy una prueba de cancelación y políticas",
        "hse_policies prueba",
        "site_policies prueba",
        1,
        1);
