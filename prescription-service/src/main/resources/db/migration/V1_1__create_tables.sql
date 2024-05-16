CREATE SEQUENCE IF NOT EXISTS prescription_seq;

CREATE TABLE IF NOT EXISTS measurement_unit_catalog (
    id bigint NOT NULL PRIMARY KEY,
    name varchar(50),
    abbreviation varchar(10)
);

CREATE TABLE IF NOT EXISTS prescription_status_catalog (
    id bigint NOT NULL PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE IF NOT EXISTS patient (
    id bigint NOT NULL PRIMARY KEY,
    first_name varchar(50),
    last_name varchar(50),
    birth_date timestamp,
    insurance_policy_nr varchar(20),
    address varchar(100),
    user_fk bigint
);

CREATE TABLE IF NOT EXISTS doctor (
    id bigint NOT NULL PRIMARY KEY,
    code varchar(20),
    first_name varchar(50),
    last_name varchar(50),
    phone varchar(20),
    doctor_approved_flag boolean,
    user_fk bigint
);

CREATE TABLE IF NOT EXISTS prescription (
    id bigint NOT NULL PRIMARY KEY,
    number bigint,
    series varchar(20),
    created_date timestamp,
    cui varchar(20),
    validity_days int,
    doctor_fk bigint,
    constraint fk_doctor foreign key (doctor_fk) references doctor(id),
    patient_fk bigint,
    constraint fk_patient foreign key (patient_fk) references patient(id),
    status_fk bigint,
    constraint fk_status foreign key (status_fk) references prescription_status_catalog(id)
);

CREATE TABLE IF NOT EXISTS prescription_drug (
    id bigint NOT NULL PRIMARY KEY,
    name varchar(50),
    active_substance varchar(50),
    administration_method text,
    description text,
    dosage double precision,
    prescription_fk bigint,
    constraint fk_prescription foreign key (prescription_fk) references prescription(id),
    measurement_unit_fk bigint,
    constraint fk_measurement_unit foreign key (measurement_unit_fk) references measurement_unit_catalog(id)
);