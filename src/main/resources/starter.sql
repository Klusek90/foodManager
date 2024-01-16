GRANT SELECT ON users TO your_username;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255) DEFAULT '',
    surname VARCHAR(255) DEFAULT '',
    position VARCHAR(255) DEFAULT '',
    role VARCHAR(255)
    );

INSERT INTO users (username, password, role) VALUES ('user', 'user', 'USER');
INSERT INTO users (username, password, role) VALUES ('admin', 'admin', 'ADMIN');
