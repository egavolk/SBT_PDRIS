CREATE TABLE IF NOT EXISTS weather (
    date VARCHAR (255) NOT NULL,
    city VARCHAR (255) NOT NULL,
    avg_temp_c FLOAT8 NOT NULL,
    max_wind_kph FLOAT8 NOT NULL,
    condition VARCHAR (255) NOT NULL,
    PRIMARY KEY(date, city)
);