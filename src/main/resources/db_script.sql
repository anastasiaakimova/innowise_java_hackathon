CREATE TABLE person
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255)
);

CREATE TABLE currency
(
    id     BIGSERIAL PRIMARY KEY,
    symbol VARCHAR(255)
);

CREATE TABLE currency_rate_history
(
    id          BIGSERIAL PRIMARY KEY,
    currency_id BIGINT,
    price       NUMERIC(28, 16),
    last_update TIMESTAMP,
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);

CREATE TABLE person_currency
(
    id          BIGSERIAL PRIMARY KEY,
    person_id   BIGINT,
    currency_id BIGINT,
    percentage  DOUBLE PRECISION,
    FOREIGN KEY (person_id) REFERENCES person (id),
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);