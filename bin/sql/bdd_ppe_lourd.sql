drop database if exists bdd_ppe_lourd ; 
create database bdd_ppe_lourd; 
use bdd_ppe_lourd; 

create table client 
(
    idclient int (3) not null auto_increment, 
    nom varchar (50) not null, 
    siret varchar (50) not null, 
    tel varchar (50) default null, 
    email varchar (50) not null,
    adresse varchar (50) default null,
    CP varchar(5) default NULL,
    ville varchar(20) default NULL, 
    PRIMARY KEY (idclient)
)
ENGINE=innodb DEFAULT CHARSET=latin1; 

create table contrat
(
    num_contrat int(3) auto_increment,
    idclient int(3) not null,
    date_souscription varchar (10) not null,
    etat_du_contrat enum ('valide','resilié', 'en cours') not null,
    objet_du_contrat varchar (50) not null,
    montant_mensuel_ht decimal (8,2) not null,
    PRIMARY KEY (num_contrat),
    foreign key (idclient) references client (idclient)
    on update cascade
    on delete cascade
)
ENGINE=innodb DEFAULT CHARSET=latin1; 


create table prestation
(
    num_prestation int(3),
    num_contrat int(3) not null,
    nom_prestation varchar(20) NOT NULL,
    prix_prestation decimal(8,2) NOT NULL,
    PRIMARY KEY (num_prestation),
    foreign key (num_contrat) references contrat (num_contrat)
    on update cascade
    on delete cascade
)
ENGINE=innodb DEFAULT CHARSET=latin1;


create table materiel
(
    num_prestation int(3) auto_increment,
    num_contrat int(3) not null,
    nom_prestation varchar(50) NOT NULL,
    prix_prestation decimal(8,2) NOT NULL,
    nom_materiel varchar(50) default null,
    cout_materiel decimal(8,2),
    PRIMARY KEY (num_prestation)
    /*foreign key (num_prestation) references prestation (num_prestation)
    on update cascade
    on delete cascade*/
)
ENGINE=innodb DEFAULT CHARSET=latin1;

create table logiciel
(
    num_prestation int(3) auto_increment,
    num_contrat int(3) not null,
    nom_prestation varchar(50) NOT NULL,
    prix_prestation decimal(8,2) NOT NULL,
    nom_logiciel varchar(50),
    version varchar(50),
    PRIMARY KEY (num_prestation)
    /*foreign key (num_prestation) references prestation (num_prestation)
    on update cascade
    on delete cascade*/
)
ENGINE=innodb DEFAULT CHARSET=latin1;


create table facture
(
    num_facture int(3) auto_increment,
    num_prestation int(3) not null,
    date_facture varchar (10) not null,
    montant decimal(8,2) not null,
    etat enum('payée', 'non payée'),
    PRIMARY KEY(num_facture),
    foreign key (num_prestation) references prestation (num_prestation)
    on update cascade
    on delete cascade
)
ENGINE=innodb DEFAULT CHARSET=latin1;


create table user 
(
    iduser int(3) not null auto_increment, 
    nom varchar(30), 
    prenom varchar(30), 
    email varchar(100), 
    mdp varchar(255),
    PRIMARY KEY (iduser)
);


/*************************************************************************** VIEWS *****************************************************************************/
/**************************************************************************************************************************************************************/

create or replace view vlesFacturesClients (num_facture, num_contrat, nom_client ,num_prestation, nom_prestation, date_facture, montant, etat) as (
select f.num_facture, ct.num_contrat, c.nom, p.num_prestation, p.nom_prestation, f.date_facture, f.montant, f.etat
from client c, contrat ct, prestation p, facture f
where c.idclient = ct.idclient
and ct.num_contrat = p.num_contrat
and p.num_prestation = f.num_prestation
);

create or replace view vlesPrestationsLM (num_prestation, num_contrat, nom_prestation, prix_prestation, nom_materiel, cout_materiel, nom_logiciel, version) as 
select p.num_prestation, p.num_contrat, p.nom_prestation, p.prix_prestation , m.nom_materiel, m.cout_materiel, l.nom_logiciel, l.version
from prestation p  left join logiciel l
on p.num_prestation = l.num_prestation
left join materiel m
on p.num_prestation = m.num_prestation;


/*************************************************************************** TRIGGERS *************************************************************************/
/*************************************************************************************************************************************************************/

/************* TRIGGERS HERITAGE ************/

drop trigger if exists ajout_logiciel;
delimiter //
create trigger ajout_logiciel
before insert on logiciel
for each row
begin

declare s int;
declare a int;

select count(*) into a
from prestation
where num_prestation = new.num_prestation;

if a = 0
then
    Set new.num_prestation = (select max(num_prestation) from prestation) +1;
    insert into prestation values (new.num_prestation, new.num_contrat, new.nom_prestation, new.prix_prestation);
end if;

select count(*) into s
from materiel
where num_prestation = new.num_prestation;

if s > 0
then
    signal sqlstate '45000'
    set message_text = 'il s agit d une prestation matérielle';
end if;

end //
delimiter ;

drop trigger if exists ajout_materiel;
delimiter //
create trigger ajout_materiel
before insert on materiel
for each row
begin

declare s int;
declare a int;

select count(*) into a
from prestation
where num_prestation = new.num_prestation;

if a = 0
then
    Set new.num_prestation = (select max(num_prestation) from prestation) +1;
    insert into prestation values (new.num_prestation,new.num_contrat, new.nom_prestation, new.prix_prestation);
end if;

select count(*) into s
from logiciel
where num_prestation = new.num_prestation;

if s > 0
then
    signal sqlstate '45000'
    set message_text = 'il s agit d une prestation logicielle';
end if;

end //
delimiter ;

drop trigger if exists updlogiciel;
delimiter //
create trigger updlogiciel
before update on logiciel
for each row
begin

update prestation
set  num_prestation = new.num_prestation, num_contrat = new.num_contrat, nom_prestation = new.nom_prestation, prix_prestation = new.prix_prestation
where num_prestation = old.num_prestation;

end //

delimiter ;

drop trigger if exists updmateriel;
delimiter //
create trigger updmateriel
before update on materiel
for each row
begin

update prestation
set num_prestation = new.num_prestation, num_contrat = new.num_contrat, nom_prestation = new.nom_prestation, prix_prestation = new.prix_prestation 
where num_prestation = old.num_prestation;

end //

delimiter ;

drop trigger if exists deletelogiciel;
delimiter //
create trigger deletelogiciel
before delete on logiciel
for each row 
begin

delete from prestation
where num_prestation = old.num_prestation;

end //
delimiter ;

drop trigger if exists deletemateriel;
delimiter //
create trigger deletemateriel
before delete on materiel
for each row 
begin

delete from prestation
where num_prestation = old.num_prestation;

end //
delimiter ;

/*****************************************/

/************************************************************************** INSERT *****************************************************************************/
/**************************************************************************************************************************************************************/

INSERT INTO client (idclient,nom, siret, tel, email, adresse, CP, ville) VALUES
(null,'Atos','12356894100054','06.78.19.65.01','atos@gmail.com','86 Avenue de France','95020','Cergy'),
(null,'Capgemini','12356894100055','06.20.48.23.52','Capgemini@gmail.com','20 Rue de versaille','78013','Versaille'),
(null,'Sncf','12356894100056','06.12.41.39.11','sncf@gmail.com','7 Rue Pierre Brosolette','92290','Chatenay Malabry');

INSERT INTO contrat (num_contrat, idclient, date_souscription, etat_du_contrat, objet_du_contrat, montant_mensuel_ht) VALUES
(null,1,'2010-05-10','Valide','dépannage informatique',113.50),
(null,2,'1998-07-15','Resilié','maintenance informatique', 200.10),
(null,3,'2001-12-21','Resilié','assistance 24/24', 107.35);

INSERT INTO prestation (num_prestation, num_contrat, nom_prestation, prix_prestation) VALUES
(1, 1, 'Prestation test', 70.50);

INSERT INTO materiel (num_prestation, num_contrat, nom_prestation, prix_prestation, cout_materiel, nom_materiel) VALUES
(null,1 ,'Prestation M1', 70.30, 47.20, 'Carte électronique'),
(null,2 ,'Prestation M2', 89.30, 45.00, 'Ecran'),
(null,3 ,'Prestation M3', 65.00, 42.00, 'Alimentation');

INSERT INTO logiciel (num_prestation, num_contrat, nom_prestation, prix_prestation, nom_logiciel, version) VALUES
(null,1 ,'Prestation L1', 72.00, 'Geogebra', 'V1.0'),
(null,2 ,'Prestation L2', 15.50, 'Win design', 'V12.3'),
(null,3 ,'Prestation L3', 42.30, 'AdobeXD', 'V10.6');

INSERT INTO facture (num_facture, num_prestation, date_facture, montant, etat) VALUES
(null, 1, '2020-06-12', 130.00, 'payée');

INSERT INTO  user (iduser, nom, prenom, email, mdp) VALUES 
(null,"Chiche", "Mehdi", "adminm@gmail.com", sha1("123"));

INSERT INTO  user (iduser, nom, prenom, email, mdp) VALUES 
(null,"Li", "Jean-Pierre", "adminj@gmail.com", sha1("123"));

