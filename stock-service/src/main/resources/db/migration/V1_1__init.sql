CREATE SEQUENCE IF NOT EXISTS stock_seq;

CREATE TABLE IF NOT EXISTS drug_form_catalog (
    id bigint NOT NULL PRIMARY KEY,
    name varchar(50),
    abbreviation varchar(10)
);

CREATE TABLE IF NOT EXISTS drug_category_catalog (
    id bigint NOT NULL PRIMARY KEY,
    name varchar(50),
    parent_fk bigint,
    constraint fk_parent foreign key (parent_fk) references drug_category_catalog(id)
);

CREATE TABLE IF NOT EXISTS drug_stock(
    id bigint NOT NULL PRIMARY KEY,
    name varchar(50),
    splitting int,
    dose_concentration int,
    producer varchar(50),
    country varchar(50),
    price double precision,
    quantity double precision,
    description text,
    form_fk bigint,
    constraint fk_drug_form foreign key (form_fk) references drug_form_catalog(id),
    category_fk bigint,
    constraint fk_drug_category foreign key (category_fk) references drug_category_catalog(id)
);

CREATE TABLE IF NOT EXISTS pharmacy(
    id bigint NOT NULL PRIMARY KEY,
    name varchar(50),
    address varchar(100),
    phone varchar(20)
);

CREATE TABLE IF NOT EXISTS pharmacy_drug_stock(
    drug_stock_fk bigint,
    constraint fk_drug_stock foreign key (drug_stock_fk) references drug_stock(id),
    pharmacy_fk bigint,
    constraint fk_pharmacy foreign key (pharmacy_fk) references pharmacy(id),
    primary key (drug_stock_fk, pharmacy_fk)
);