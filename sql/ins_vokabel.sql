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