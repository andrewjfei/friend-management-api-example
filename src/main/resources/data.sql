INSERT INTO "user"(id, username, first_name, last_name, password, created) VALUES
('11afa5b4-c622-4320-a4e9-7c374172b63d', 'joesmith', 'Joe', 'Smith', 'password', CURRENT_TIMESTAMP),
('26770bad-887c-4ef7-a77c-f582d50e201c', 'caseywang', 'Casey', 'Wang', 'password', CURRENT_TIMESTAMP),
('ee934861-9c00-4795-b78d-0bb760517dd4', 'jaxjones', 'Jax', 'Jones', 'password', CURRENT_TIMESTAMP);

INSERT INTO "friendship"(requester_id, receiver_id, is_accepted, created) VALUES
('11afa5b4-c622-4320-a4e9-7c374172b63d', '26770bad-887c-4ef7-a77c-f582d50e201c', true, CURRENT_TIMESTAMP),
('11afa5b4-c622-4320-a4e9-7c374172b63d', 'ee934861-9c00-4795-b78d-0bb760517dd4', false, CURRENT_TIMESTAMP),
('26770bad-887c-4ef7-a77c-f582d50e201c', '11afa5b4-c622-4320-a4e9-7c374172b63d', true, CURRENT_TIMESTAMP),
('26770bad-887c-4ef7-a77c-f582d50e201c', 'ee934861-9c00-4795-b78d-0bb760517dd4', true, CURRENT_TIMESTAMP),
('ee934861-9c00-4795-b78d-0bb760517dd4', '11afa5b4-c622-4320-a4e9-7c374172b63d', true, CURRENT_TIMESTAMP);