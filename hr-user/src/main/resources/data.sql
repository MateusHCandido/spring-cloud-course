INSERT INTO account_credentials (nameAccount, emailAccount, passwordAccount) VALUES ('Nina Brown', 'nina@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');
INSERT INTO account_credentials (nameAccount, emailAccount, passwordAccount) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');

INSERT INTO access_permission (permission_type) VALUES ('ROLE_OPERATOR');
INSERT INTO access_permission (permission_type) VALUES ('ROLE_ADMIN');

INSERT INTO account_access_permission (account_id, role_id) VALUES (1, 1);
INSERT INTO account_access_permission (account_id, role_id) VALUES (2, 1);
INSERT INTO account_access_permission (account_id, role_id) VALUES (2, 2);