ALTER TABLE customers
    DROP CONSTRAINT FK_CUSTOMERS_ON_USER;

ALTER TABLE customers
    ADD CONSTRAINT FK_CUSTOMERS_ON_USER
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE;