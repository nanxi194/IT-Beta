CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    privacy VARCHAR (50) NOT NULL
);



CREATE TABLE IF NOT EXISTS projects(
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    display BLOB
);

CREATE TABLE IF NOT EXISTS files(
    id INT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(250) NOT NULL,
    file_type VARCHAR(250) NOT NULL,
    data BLOB NOT NULL,
    user VARCHAR(250) NOT NULL,
    description TEXT,
    KEY project_id (project_id),
    CONSTRAINT `files_fk` FOREIGN KEY (project_id) REFERENCES projects (project_id)
);