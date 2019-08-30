/*korisnici*/
/*glavni urednici*/
insert into upp_db.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (1,'Srbija',
'bojanadjordjevic2@gmail.com','Novi Sad','Bojana','Djordjevic',1,2,'PhD','boka','boka',1);

insert into upp_db.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (2,'Srbija',
'dusan.krivokapic.ns@gmail.com','Novi Sad','Dusan','Krivokapic',1,2,'PhD','dusan','dusan',2);

/*urednici*/
insert into upp_db.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka,casopis_id) values (3,'Srbija',
'stefan.krivokapic@gmail.com','Beograd','Stefan','Krivokapic',0,1,'Msc','stefan','stefan',1);

insert into upp_db.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka) values (4,'Srbija',
'marija.krivokapic@devoteam.com','Novi Sad','Pera','Peric',0,1,'Msc','pera','pera');

insert into upp_db.korisnik (id,drzava,email,grad,ime,
prezime,is_glavni,uloga,titula,username,lozinka) values (5,'Srbija',
'mkrivokapic.contractor@libertyglobal.com','Novi Sad','Mika','Mikic',0,1,'Bsc','mika','mika');


/*naucne oblasti*/
insert into upp_db.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(1,'Matematika',3);

insert into upp_db.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(2, 'IT',4);

insert into upp_db.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(3, 'Medicina',3);

insert into upp_db.naucna_oblast (id,naziv,urednik_naucne_oblasti_id) values
(4, 'FIzika',5);


/*casopisi*/
insert into upp_db.casopis (id,naziv,issn_broj,is_open_access) values
(1,'Matematika', '123', 1);
insert into upp_db.casopis (id,naziv,issn_broj,is_open_access) values
(2,'Computer world','234',0);
insert into upp_db.casopis (id,naziv,issn_broj,is_open_access) values
(3,'Anatomija','456',0);


/*recenzenti*/
insert into upp_db.casopis_recenzent (id,casopis_id,recenzent_id) values
(1,1,4);

insert into upp_db.casopis_recenzent (id,casopis_id,recenzent_id) values
(2,1,3);

insert into upp_db.casopis_recenzent (id,casopis_id,recenzent_id) values
(3,2,5);
