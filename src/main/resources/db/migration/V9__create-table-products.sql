CREATE TABLE products
(
    id                 UUID                        NOT NULL,
    name               VARCHAR(255)                NOT NULL,
    description        VARCHAR(1000),
    category           VARCHAR(255)                NOT NULL,
    code               VARCHAR(20)                 NOT NULL,
    cost_price         DOUBLE PRECISION            NOT NULL,
    sale_price         DOUBLE PRECISION            NOT NULL,
    time_to_prepare    TIME WITHOUT TIME ZONE,
    is_active          BOOLEAN                     NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    created_by         VARCHAR(255)                NOT NULL,
    last_modified_by   VARCHAR(255),
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT uc_products_code UNIQUE (code);

ALTER TABLE products
    ALTER COLUMN is_active SET DEFAULT true;