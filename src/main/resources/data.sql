INSERT INTO insurance_rate (package_name, description, insurance_type, rate)
VALUES ('BASIC', 'Základný', 'SHORT_TERM', 1.2);

INSERT INTO insurance_rate (package_name, description, insurance_type, rate)
VALUES ('ADVANCED', 'Rozšírený', 'SHORT_TERM', 1.8);

INSERT INTO insurance_rate (package_name, description, insurance_type, rate)
VALUES ('EXTRA', 'Extra', 'SHORT_TERM', 2.4);

INSERT INTO insurance_rate (package_name, description, insurance_type, rate)
VALUES ('BASIC', 'Základný', 'YEAR_ROUND', 39);

INSERT INTO insurance_rate (package_name, description, insurance_type, rate)
VALUES ('ADVANCED', 'Rozšírený', 'YEAR_ROUND', 49);

INSERT INTO insurance_rate (package_name, description, insurance_type, rate)
VALUES ('EXTRA', 'Extra', 'YEAR_ROUND', 59);

INSERT INTO additional_insurance (name, description)
VALUES ('SPORT', 'Športové aktivity');

INSERT INTO additional_insurance (name, description)
VALUES ('STORNO', 'Storno cesty');
