CREATE TABLE t_permission
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR(255)
);

CREATE TABLE t_users
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(255),
    full_name VARCHAR(255),
    password  VARCHAR(255)
);

CREATE TABLE t_users_permissions
(
    user_id        BIGINT,
    permissions_id BIGINT
);

CREATE TABLE t_sneakers
(
    id    SERIAL PRIMARY KEY,
    color VARCHAR(255),
    name  VARCHAR(255),
    price INT
);

ALTER TABLE t_users_permissions
    ADD CONSTRAINT fk_t_users_permissions_t_users
        FOREIGN KEY (user_id)
            REFERENCES t_users (id)
            ON DELETE CASCADE;

ALTER TABLE t_users_permissions
    ADD CONSTRAINT fk_t_users_permissions_t_permission
        FOREIGN KEY (permissions_id)
            REFERENCES t_permission (id)
            ON DELETE CASCADE;
