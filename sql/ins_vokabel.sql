INSERT INTO category (cat_name) VALUES ('Food');
INSERT INTO category (cat_name) VALUES ('Animal');
INSERT INTO category (cat_name) VALUES ('Household');

INSERT INTO word (wrd_german, wrd_english, wrd_cat_id)
   VALUES ('Hund', 'Dog', 2);
INSERT INTO word (wrd_german, wrd_english, wrd_cat_id)
   VALUES ('Katze', 'Cat', 2);
INSERT INTO word (wrd_german, wrd_english, wrd_cat_id)
   VALUES ('Apfel', 'Apple', 1);
INSERT INTO word (wrd_german, wrd_english, wrd_cat_id)
   VALUES ('Teller', 'Plate', 3);
INSERT INTO word (wrd_german, wrd_english, wrd_cat_id)
   VALUES ('Gabel', 'Fork', 3);
--INSERT INTO word (wrd_german, wrd_english, wrd_cat_id)
--   VALUES ('', '', );

INSERT INTO event_type (et_name) VALUES ('SCHULARBEIT');
INSERT INTO event_type (et_name) VALUES ('TEST');
INSERT INTO event_type (et_name) VALUES ('MITARBEITSKONTROLLE');
INSERT INTO event_type (et_name) VALUES ('UEBUNG');

INSERT INTO event (evt_event_type, evt_date, evt_descr)VALUES ('SCHULARBEIT', '2020-04-01 08:00:00', 'Schularbeit');
INSERT INTO event (evt_event_type, evt_date, evt_descr)VALUES ('TEST', '2020-05-13 10:00:00', 'Test');
INSERT INTO event (evt_event_type, evt_date, evt_descr)VALUES ('MITARBEITSKONTROLLE', '2020-05-27 08:00:00', 'Mitarbeitskontrolle');
INSERT INTO event (evt_event_type, evt_date, evt_descr)VALUES ('UEBUNG', '2020-6-02 12:45:00', 'Uebung');

INSERT INTO card (crd_wrd_id, crd_evt_id) VALUES (1, 1);
INSERT INTO card (crd_wrd_id, crd_evt_id) VALUES (2, 1);
INSERT INTO card (crd_wrd_id, crd_evt_id) VALUES (3, 1);
INSERT INTO card (crd_wrd_id, crd_evt_id) VALUES (4, 1);
INSERT INTO card (crd_wrd_id, crd_evt_id) VALUES (5, 1);
INSERT INTO card (crd_wrd_id, crd_evt_id) VALUES (1, 2);
INSERT INTO card (crd_wrd_id, crd_evt_id) VALUES (4, 2);