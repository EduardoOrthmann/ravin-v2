CREATE TABLE customers
(
    id                 UUID                        NOT NULL,
    name               VARCHAR(255)                NOT NULL,
    phone_number       CHAR(20),
    birth_date         DATE                        NOT NULL,
    cpf                CHAR(14)                    NOT NULL,
    is_active          BOOLEAN                     NOT NULL,
    user_id            UUID                        NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    created_by         VARCHAR(255)                NOT NULL,
    last_modified_by   VARCHAR(255),
    country            VARCHAR(255),
    state              VARCHAR(255),
    city               VARCHAR(255),
    zip_code           VARCHAR(255),
    neighborhood       VARCHAR(255),
    street             VARCHAR(255),
    number             VARCHAR(255),
    complement         VARCHAR(255),
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

ALTER TABLE customers
    ADD CONSTRAINT uc_customers_cpf UNIQUE (cpf);

ALTER TABLE customers
    ADD CONSTRAINT FK_CUSTOMERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);