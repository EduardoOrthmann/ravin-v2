CREATE TABLE employees
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
    marital_status     VARCHAR(255),
    education_level    VARCHAR(255),
    position           VARCHAR(255)                NOT NULL,
    admission_date     DATE                        NOT NULL,
    resignation_date   DATE,
    is_available       BOOLEAN                     NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (id)
);

ALTER TABLE employees
    ADD CONSTRAINT uc_employees_cpf UNIQUE (cpf);

ALTER TABLE employees
    ADD CONSTRAINT FK_EMPLOYEES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE employees
    ALTER COLUMN is_active SET DEFAULT true;

ALTER TABLE employees
    ALTER COLUMN admission_date SET DEFAULT CURRENT_DATE;