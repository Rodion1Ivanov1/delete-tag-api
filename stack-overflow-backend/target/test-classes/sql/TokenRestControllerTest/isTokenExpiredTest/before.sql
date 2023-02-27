TRUNCATE TABLE accounts CASCADE;
TRUNCATE TABLE permissions CASCADE;

INSERT INTO permissions(id, name)
VALUES (1, 'ADMIN'),
       (2, 'MODERATOR'),
       (3, 'USER');

INSERT INTO accounts(id, username, password, creation_date, enabled)
VALUES (1, 'user1', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', current_timestamp, true);

INSERT INTO accounts_permissions(account_id, permission_id)
VALUES (1, 3);
