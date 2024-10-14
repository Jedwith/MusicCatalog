CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users (
       id SERIAL PRIMARY KEY,
       role_id INTEGER,
       name VARCHAR (255) NOT NULL,
       login VARCHAR(255) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL,
       FOREIGN KEY (role_id) REFERENCES roles(id) -- Поле role_id связано внешним ключом с полем id в таблице roles
);

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO users (role_id, name, login, password)
VALUES ((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), 'Домрачев Егор','admin', '$2a$12$vcfe1R2DyTYsWhnDKvZQLOTg/NcWfwjUuz2NrDvvN0wY89iL669NO'),
       ((SELECT id FROM roles WHERE name = 'ROLE_USER'), 'Белобородкин Дмитрий','user','$2a$12$rz7WiHX3Nz.wzBVQSvS8/eA/dRMHBkNUAlh2HEUXXhqMdvkhOV3y2'),
       ((SELECT id FROM roles WHERE name = 'ROLE_USER'), 'Шишочкин Даниил','user2','$2a$12$rz7WiHX3Nz.wzBVQSvS8/eA/dRMHBkNUAlh2HEUXXhqMdvkhOV3y2');

CREATE TABLE artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    artist_link VARCHAR(255) NOT NULL
);

CREATE TABLE song (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    time_length DOUBLE PRECISION NOT NULL,
    artist_id INTEGER REFERENCES artists(id),
    genius_link VARCHAR(255) NOT NULL
);

INSERT INTO artists (name, artist_link) VALUES
    ('Artist 1', 'https://artist1link.com'),
    ('Artist 2', 'https://artist2link.com');

INSERT INTO song (title, genre, time_length, artist_id, genius_link) VALUES
    ('Song 1', 'Rock', 3.5, 1, 'https://genius.com/song1'),
    ('Song 2', 'Rap', 4.0, 2, 'https://genius.com/song2'),
    ('Song 3', 'Rock', 5.0, 2, 'https://genius.com/song3'),
    ('Song 4', 'Pop', 6.0, 2, 'https://genius.com/song4'),
    ('Song 5', 'Rap', 6.0, 1, 'https://genius.com/song5'),
    ('Song 6', 'Rock', 6.0, 2, 'https://genius.com/song6'),
    ('Song 7', 'Pop', 6.0, 1, 'https://genius.com/song7'),
    ('Song 8', 'Rock', 6.0, 2, 'https://genius.com/song8'),
    ('Song 9', 'Rap', 6.0, 1, 'https://genius.com/song9'),
    ('Song 10', 'Rap', 6.0, 1, 'https://genius.com/song10'),
    ('Song 11', 'Pop', 6.0, 2, 'https://genius.com/song11'),
    ('Song 12', 'Rock', 6.0, 2, 'https://genius.com/song12');

