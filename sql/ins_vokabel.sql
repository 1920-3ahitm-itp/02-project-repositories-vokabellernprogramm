INSERT INTO category (c_name) VALUES ('Food');
INSERT INTO category (c_name) VALUES ('Animal');
INSERT INTO category (c_name) VALUES ('Household');


INSERT INTO word (w_german, w_english, w_c_id)
   VALUES ('Hund', 'Dog', 2);
INSERT INTO word (w_german, w_english, w_c_id)
   VALUES ('Katze', 'Cat', 2);
INSERT INTO word (w_german, w_english, w_c_id)
   VALUES ('Apfel', 'Apple', 1);
INSERT INTO word (w_german, w_english, w_c_id)
   VALUES ('Teller', 'Plate', 3);
INSERT INTO word (w_german, w_english, w_c_id)
   VALUES ('Gabel', 'Fork', 3);
--INSERT INTO word (w_german, w_english, w_c_id)
--   VALUES ('', '', );

INSERT INTO eventtype (et_name) VALUES ('SCHULARBEIT');
INSERT INTO eventtype (et_name) VALUES ('TEST');
INSERT INTO eventtype (et_name) VALUES ('MITARBEITSKONTROLLE');
INSERT INTO eventtype (et_name) VALUES ('UEBUNG');

INSERT INTO event (e_et_id, e_w_id, e_date, e_descr)VALUES (1, 1, '2020-04-01 08:00:00', 'Schularbeit');
INSERT INTO event (e_et_id, e_w_id, e_date, e_descr)VALUES (2, 2, '2020-05-13 10:00:00', 'Test');
INSERT INTO event (e_et_id, e_w_id, e_date, e_descr)VALUES (3, 3, '2020-05-27 08:00:00', 'Mitarbeitskontrolle');
INSERT INTO event (e_et_id, e_w_id, e_date, e_descr)VALUES (4, 4, '2020-6-02 12:45:00', 'Schularbeit');

