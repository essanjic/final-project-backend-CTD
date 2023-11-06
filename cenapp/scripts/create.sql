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
