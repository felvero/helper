CREATE TABLE IF NOT EXISTS trainer (
                                        id SERIAL PRIMARY KEY,
                                        first_name VARCHAR(255),
                                        last_name VARCHAR(255),
                                        email VARCHAR(255) UNIQUE,
                                        password VARCHAR(255),
                                        phone_number VARCHAR(20),
                                        year_of_birth INTEGER,
                                        actual_gym VARCHAR(255),
                                        date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS trainee (
                                       id SERIAL PRIMARY KEY,
                                       trainer_id INTEGER REFERENCES trainer(id),
                                       first_name VARCHAR(255),
                                       last_name VARCHAR(255),
                                       email VARCHAR(255) UNIQUE,
                                       password VARCHAR(255),
                                       phone_number VARCHAR(20),
                                       year_of_birth INTEGER,
                                       actual_gym VARCHAR(255),
                                       date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS exercise (
                                        id SERIAL PRIMARY KEY,
                                        trainer_id INTEGER REFERENCES trainer(id),
                                        trainee_id INTEGER REFERENCES trainee(id),
                                        series1 INTEGER,
                                        series2 INTEGER,
                                        series3 INTEGER,
                                        series4 INTEGER,
                                        series5 INTEGER,
                                        series6 INTEGER,
                                        series7 INTEGER,
                                        series8 INTEGER,
                                        series9 INTEGER,
                                        series10 INTEGER,
                                        maximum_weight INTEGER,
                                        date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        comment VARCHAR(1000)
);
