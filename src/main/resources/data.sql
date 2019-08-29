insert into poslovniprocesi.casopis (id,naziv,issn_broj,is_open_access) values
(1,'Hemijske reakcije','11221122',1);
insert into poslovniprocesi.casopis (id,naziv,issn_broj,is_open_access) values
(2,'Uticaj softvera na drustvo','23232323',0);

insert into poslovniprocesi.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(1,'Opste',2);

insert into poslovniprocesi.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(2, 'Hemija',2);

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (1,'Srbija',
'bojanadjordjevic2@gmail.com','Novi Sad','Bojana','Djordjevic',1,2,'PhD','boka','boka',1);

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (2,'Srbija',
'stefan.krivokapic@gmail.com','Beograd','Stefan','Krivokapic',0,2,'Msc','stefan','stefan',1);

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka) values (3,'Srbija',
'marija.krivokapic@devoteam.com','Novi Sad','Pera','Peric',0,1,'Msc','pera','pera');

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka) values (4,'Srbija',
'mkrivokapic.contractor@libertyglobal.com','Novi Sad','Mika','Mikic',0,1,'Bsc','mika','mika');

insert into poslovniprocesi.casopis_recenzent (id,casopis_id,recenzent_id) values
(1,1,3);

insert into poslovniprocesi.casopis_recenzent (id,casopis_id,recenzent_id) values
(2,1,4);

insert into poslovniprocesi.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (15,'Srbija',
'dusan.krivokapic.ns@gmail.com','Novi Sad','Dusan','Krivokapic',1,2,'PhD','dusan','dusan',2);
