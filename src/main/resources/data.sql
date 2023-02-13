INSERT INTO "user"(id, username, first_name, last_name, password, created) VALUES
('11afa5b4-c622-4320-a4e9-7c374172b63d', 'joesmith', 'Joe', 'Smith', 'password', CURRENT_TIMESTAMP),
('26770bad-887c-4ef7-a77c-f582d50e201c', 'caseywang', 'Casey', 'Wang', 'password', CURRENT_TIMESTAMP),
('ee934861-9c00-4795-b78d-0bb760517dd4', 'jaxjones', 'Jax', 'Jones', 'password', CURRENT_TIMESTAMP),
('b0a73dff-25a8-4ea6-9a84-3a7338d04ba7', 'alexchen', 'Alex', 'Chen', 'password', CURRENT_TIMESTAMP),
('ebcc52ff-1e91-4f21-be7c-9508ed1a1f48', 'bobgreen', 'Bob', 'Green', 'password', CURRENT_TIMESTAMP);

INSERT INTO "friendship"(id, requester_id, receiver_id, is_accepted, created) VALUES
('b45d4850-9f68-40b6-b734-e46c63e99aab', '11afa5b4-c622-4320-a4e9-7c374172b63d', '26770bad-887c-4ef7-a77c-f582d50e201c', true, CURRENT_TIMESTAMP),
('88e52876-f861-4528-8352-2231af686617', '11afa5b4-c622-4320-a4e9-7c374172b63d', 'ee934861-9c00-4795-b78d-0bb760517dd4', false, CURRENT_TIMESTAMP),
('39447eb3-60bc-42f5-946c-916144ae4a02', '26770bad-887c-4ef7-a77c-f582d50e201c', '11afa5b4-c622-4320-a4e9-7c374172b63d', true, CURRENT_TIMESTAMP),
('6c85adab-8d60-4c26-bc8c-2df7fd4feba4', '26770bad-887c-4ef7-a77c-f582d50e201c', 'ee934861-9c00-4795-b78d-0bb760517dd4', true, CURRENT_TIMESTAMP),
('0b570db0-6a74-4f2c-bc91-2e162bbae0d1', 'ee934861-9c00-4795-b78d-0bb760517dd4', '26770bad-887c-4ef7-a77c-f582d50e201c', true, CURRENT_TIMESTAMP),
('87dbe6e7-caf8-44bd-8ce6-5326cb6e2ca7', 'b0a73dff-25a8-4ea6-9a84-3a7338d04ba7', '11afa5b4-c622-4320-a4e9-7c374172b63d', false, CURRENT_TIMESTAMP),
('88d7feba-557b-4f9c-9c6e-0804f687d35d', 'ebcc52ff-1e91-4f21-be7c-9508ed1a1f48', '11afa5b4-c622-4320-a4e9-7c374172b63d', false, CURRENT_TIMESTAMP);