CREATE TABLE category (
  c_id INT NOT NULL GENERATED ALWAYS AS IDENTITY
       CONSTRAINT category_pk PRIMARY KEY,
  c_name VARCHAR(50)
);

CREATE TABLE word (
  w_id INT NOT NULL GENERATED ALWAYS AS IDENTITY
       CONSTRAINT word_pk PRIMARY KEY,
  w_german VARCHAR(50),
  w_english VARCHAR(50),
  w_c_id INT CONSTRAINT word_category_fk
         REFERENCES category(c_id)
);

CREATE TABLE eventtype (
    et_id INT NOT NULL GENERATED ALWAYS AS IDENTITY
        CONSTRAINT event_type_pk PRIMARY KEY,
    et_name VARCHAR(50)
);

CREATE TABLE event (
    e_et_id INT CONSTRAINT event_eventtype_fk
        REFERENCES eventtype(et_id),
    e_w_id INT CONSTRAINT event_word_fk
        REFERENCES word(w_id),
    e_date DATE,
    CONSTRAINT event_pk PRIMARY KEY (e_et_id, e_w_id)
);