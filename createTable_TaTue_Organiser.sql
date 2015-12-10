drop table Schueler cascade constraints;
drop table GuideRating cascade constraints;
drop table Stand cascade constraints;
drop table StandRating cascade constraints;
drop table GewinnspielDaten cascade constraints;
drop table Abteilung cascade constraints;
drop table Quiz cascade constraints;
drop table Frage cascade constraints;
drop table Antwort cascade constraints;

create table Abteilung(
	AB_ID Number primary key,
	AbName varchar(30),
	AbEtage Number
);

create table Stand (
	ST_ID Number primary key,
	StName varchar(50),
	Info varchar(500),
	AB_ID Number references Abteilung(AB_ID) not null,
	shape SDO_GEOMETRY
);

create table Schueler (
	S_ID Number primary key,
	Vorname varchar(50),
	Nachname varchar(50),
	Klasse varchar(10),
	isGuide Number(1),
	St_ID Number references Stand(St_ID)
);

create table GuideRating (
	GR_ID Number primary key,
	Freundlichkeit Number(*,2),
	Kompetenz Number(*,2),
	S_ID Number references Schueler(S_ID) not null
);

create table StandRating (
	SR_ID Number primary key,
	Aufbau Number(*,2),
	Freundlichkeit Number(*,2),
	Kompetenz Number(*,2),
	ST_ID Number references Stand(ST_ID) not null
);

create table Quiz(
	Q_ID Number primary key,
	Titel varchar(100),
	AB_ID Number references Abteilung(AB_ID) not null
);	

create table Frage(
	F_ID Number primary key,
	Text varchar(200),
	Q_ID references Quiz(Q_ID) not null
);
	
create table Antwort(
	A_ID Number primary key,
	Text varchar(200),
    isRight number(1),
	F_ID Number references Frage(F_ID) not null
);

create table GewinnspielDaten (
	GD_ID Number primary key,
	Q_ID NUmber references Quiz(Q_ID) not null,
	Score Number(*,2),
	Vorname varchar(50),
	Nachname varchar(50),
	Email varchar(50),
	Telefon varchar(50)
);

--------------------------------------------------------------------------------------------------------------------
DROP SEQUENCE seq_abteilungsnetz;
CREATE SEQUENCE seq_abteilungsnetz;

delete from user_sdo_geom_metadata
where table_name = 'Stand'
or table_name = 'STAND';

INSERT INTO user_sdo_geom_metadata
( TABLE_NAME,
COLUMN_NAME,
DIMINFO,
SRID
)
VALUES
( 'Stand',
'shape',
SDO_DIM_ARRAY( -- 20X20 grid
SDO_DIM_ELEMENT('X', 0, 20, 0.005),
SDO_DIM_ELEMENT('Y', 0, 20, 0.005)
),
NULL -- SRID
);

CREATE INDEX index_stand ON Stand(shape) INDEXTYPE IS MDSYS.SPATIAL_INDEX;
--------------------------------------------------------------------------------------------------------------------

INSERT INTO Abteilung VALUES(1, 'Informatik', 2);
INSERT INTO Abteilung VALUES(2, 'Bautechnik', 2);
INSERT INTO Abteilung VALUES(3, 'Innenraum', 1);
INSERT INTO Abteilung VALUES(4, 'Netzwerktechnik', 1);

INSERT INTO Stand VALUES(1, 'SAP-Stand', 'Bei diesem Stand werden Informationen über SAP geboten', 1, 
	SDO_GEOMETRY(
		2001,
		NULL,
		SDO_POINT_TYPE(60, 1, NULL),
		NULL,
		NULL
	));
INSERT INTO Stand VALUES(2, 'Baukonstruktionen', 'Häuser Häuser Häuser', 2, 
	SDO_GEOMETRY(
		2001,
		NULL,
		SDO_POINT_TYPE(60, 1, NULL),
		NULL,
		NULL
	));
INSERT INTO Stand VALUES(3, 'Wand Streichen', 'How to Basic: Wand streichen leicht gemacht', 3, 
	SDO_GEOMETRY(
		2001,
		NULL,
		SDO_POINT_TYPE(60, 1, NULL),
		NULL,
		NULL
	));
INSERT INTO Stand VALUES(4, 'Keine Inhalte, nur Apple', 'Unsere PCs kosten mehr als dein Leben', 4, 
	SDO_GEOMETRY(
		2001,
		NULL,
		SDO_POINT_TYPE(60, 1, NULL),
		NULL,
		NULL
	));

INSERT INTO Schueler VALUES(1, 'Jonas', 'Schaltegger', '5BHIFS', 1, null);
INSERT INTO Schueler VALUES(2, 'Henrik', 'Csöre', '5BHIFS', 1, null);
INSERT INTO Schueler VALUES(3, 'Simon', 'Schwantler', '5BHIFS', 1, null);

INSERT INTO Schueler VALUES(4, 'David', 'Jäger', '5BHIFS', 0, 1);
INSERT INTO Schueler VALUES(5, 'Tino', 'Notsch', '5BHIFS', 0, 1);

INSERT INTO Schueler VALUES(6, 'Hansi', 'Jäger', '5BHIFS', 0, null);
INSERT INTO Schueler VALUES(7, 'Stefan', 'Egger', '5BHIFS', 0, null);

INSERT INTO GuideRating VALUES(1, 1, 1, 1);
INSERT INTO GuideRating VALUES(2, 2, 1, 1);
INSERT INTO GuideRating VALUES(3, 2, 4, 1);


INSERT INTO GuideRating VALUES(4, 1, 1, 2);
INSERT INTO GuideRating VALUES(5, 5, 4, 2);
INSERT INTO GuideRating VALUES(6, 1, 2, 2);


INSERT INTO StandRating VALUES(1, 1, 1, 1, 1);
INSERT INTO StandRating VALUES(2, 3, 3, 2, 1);
INSERT INTO StandRating VALUES(3, 2, 4, 5, 1);

INSERT INTO Quiz VALUES(1, 'Informatik-Quiz', 1);

INSERT INTO Frage VALUES(1, 'Was ist keine Programmiersprache?', 1);
INSERT INTO Antwort VALUES(1, 'C++', 0, 1);
INSERT INTO Antwort VALUES(2, 'C', 0, 1);
INSERT INTO Antwort VALUES(3, 'C#', 0, 1);
INSERT INTO Antwort VALUES(4, 'C--', 1, 1);

INSERT INTO Frage VALUES(2, 'Welche Insel heißt wie eine Programmiersprache?', 1);
INSERT INTO Antwort VALUES(5, 'Hawaii', 0, 2);
INSERT INTO Antwort VALUES(6, 'Grönland', 0, 2);
INSERT INTO Antwort VALUES(7, 'Kos', 0, 2);
INSERT INTO Antwort VALUES(8, 'Java', 1, 2);

INSERT INTO GewinnspielDaten VALUES(1, 1, 10, 'Maxi', 'Isnichtblöd', 'binklug@gmx.at', '0664123456789');


commit;