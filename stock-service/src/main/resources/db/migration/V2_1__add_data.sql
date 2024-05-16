CREATE TABLE IF NOT EXISTS pharmacist (
    id bigint NOT NULL PRIMARY KEY,
    license_code varchar(50),
    first_name varchar(50),
    last_name varchar(50),
    phone varchar(50),
    pharmacist_approved_flag boolean DEFAULT FALSE,
    user_fk varchar(100),
    pharmacy_fk bigint,
    FOREIGN KEY (pharmacy_fk) REFERENCES pharmacy (id)
);