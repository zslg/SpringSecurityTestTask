
SET search_path = public, pg_catalog;

CREATE FUNCTION update_date() RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
  NEW.last_update := current_timestamp;
  RETURN NEW;
END;
$$;

CREATE SEQUENCE role_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


CREATE TABLE role (
  id bigint DEFAULT nextval('role_id_seq'::regclass) NOT NULL,
  name character varying(256) NOT NULL
);


CREATE SEQUENCE user_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


CREATE TABLE user_ (
  id bigint DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
  name character varying(256) NOT NULL,
  password character varying NOT NULL,
  is_active boolean DEFAULT false NOT NULL,
  last_update timestamp with time zone DEFAULT now() NOT NULL
);


CREATE TABLE user_role (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL
);


ALTER TABLE ONLY role
  ADD CONSTRAINT name_unique UNIQUE (name);


ALTER TABLE ONLY role
  ADD CONSTRAINT role_id_pk PRIMARY KEY (id);

ALTER TABLE ONLY user_
  ADD CONSTRAINT user_id_pk PRIMARY KEY (id);

ALTER TABLE ONLY user_
  ADD CONSTRAINT user_name_unique UNIQUE (name);

CREATE TRIGGER user__tr BEFORE UPDATE ON user_ FOR EACH ROW EXECUTE PROCEDURE update_date();

ALTER TABLE ONLY user_role
  ADD CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES role(id);

ALTER TABLE ONLY user_role
  ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES user_(id);

--add start user
INSERT INTO public.user_(name, password, is_active)
VALUES ('admin','$2a$04$FMmd7k.kiiIFqz2oeYEGmeHeHkIV6bPNtjzOKIxY2lEou6JOwX3gq', true);