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
	isGuide Number,
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
    isRight varchar(1),
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

INSERT INTO Stand VALUES (1,'SAP', 'Bestes System Evaaaa', 1,
	SDO_GEOMETRY(
		2001,
		NULL,
		SDO_POINT_TYPE(60, 1, NULL),
		NULL,
		NULL
	)
);

commit;