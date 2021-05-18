CREATE TABLE IF NOT EXISTS weather (
    date VARCHAR (255) NOT NULL,
    city VARCHAR (255) NOT NULL,
    avgTempC FLOAT8 NOT NULL,
    maxWindKph FLOAT8 NOT NULL,
    condition VARCHAR (255) NOT NULL,
    PRIMARY KEY(date, city)
);