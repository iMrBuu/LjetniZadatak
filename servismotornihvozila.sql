# c:\xampp\mysql\bin\mysql -uedunova -pedunova < e:\jp22\hello\servismotornihvozila.sql
drop database if exists servismotornihvozila;
create database servismotornihvozila;
use servismotornihvozila;

create table klijent(
	sifra int not null primary key auto_increment,
	ime varchar(50) not null,
	prezime varchar(50) not null,
	oib char(11) not null,
	kontaktbroj varchar(20) not null,
	email varchar(100) not null
	);
	
create table vozilo(
	sifra int not null primary key auto_increment,
	klijent int not null,
	vrsta int not null,
	marka int not null,
	godinaproizvodnje date not null,
	boja varchar(50) not null,
	brojsasije varchar(100) not null
	);

create table vrsta(
	sifra int not null primary key auto_increment,
	naziv varchar(100) not null
	);

create table marka(
	sifra int not null primary key auto_increment,
	naziv varchar(100) not null,
	model varchar(100) not null
	);

create table servis(
	sifra int not null primary key auto_increment,
	vozilo int not null,
	radnik int not null,
	naziv varchar(100) not null,
	opis text not null,
	cijena decimal(18,2) not null
	);

create table radnik(
	sifra int not null primary key auto_increment,
	ime varchar(50) not null,
	prezime varchar(50) not null,
	oib char(11) not null,
	kontaktbroj varchar(20) not null,
	email varchar(100) not null,
	brojugovora varchar(100) not null
	);

alter table vozilo add foreign key (klijent) references klijent(sifra);
alter table vozilo add foreign key (vrsta) references vrsta(sifra);
alter table vozilo add foreign key (marka) references marka(sifra);

alter table servis add foreign key (vozilo) references vozilo(sifra);
alter table servis add foreign key (radnik) references radnik(sifra);


#show tables;
#describe klijent;

insert into klijent (sifra,ime,prezime,oib,kontaktbroj,email)
values	(null,'Ivan','Horvat','11559480314','0912749379','ihorvat1@gmail.com'),
		(null,'Marko','Rist','38347893775','0997595748','mrist90@gmail.com'),
		(null,'Tomislav','Vranješ','03454321027','0976997544','tvranjes2@gmail.com'),
		(null,'Goran','Perić','35935517685','0923648395','goran.peric@yahoo.com'),
		(null,'Saša','Marić','73684638780','0916930573','marićs@gmail.com'),
		(null,'Marica','Katić','96162375544','0928695880','marica.katic@yahoo.com'),
		(null,'Miroslav','Tomić','96164094768','0913474833','mtomic3@gmail.com'),
		(null,'Luka','Miloloža','13865445934','0971828186','luka.miloloza@yahoo.com'),
		(null,'Robert','Marić','36162648345','0954738357','robertm031@yahoo.com'),
		(null,'Ana','Vuko','91620036759','0953753893','anav@gmail.com'),
		(null,'Petar','Bralić','59162574032','0917457535','perica420@gmail.com'),
		(null,'Željko','Duvnjak','32162590023','0958664634','zeljko.duvnjak@gmail.com'),
		(null,'Ivana','Mitrović','46167848257','0958646347','ivanam0@yahoo.com'),
		(null,'Mirela','Petrović','68162479612','0999837560','mirela.petrovic@gmail.com'),
		(null,'Tadijan','Mihaljević','47162668246','0988345360','mihaljevic.tadijan@gmail.com');
		
#select * from klijent;
#select * from vozilo;
#describe vozilo;
#describe vrsta;

insert into vrsta (sifra,naziv)
values	(null,'Automobil'),
		(null,'Motocikl'),
		(null,'Tegljač');
	
#describe marka;

insert into marka (sifra,naziv,model)
values	(null,'Audi','A4'),
		(null,'Audi','A3'),
		(null,'Audi','A6'),
		(null,'Volkswagen','Golf'),
		(null,'Volkswagen','Polo'),
		(null,'Volkswagen','up!'),
		(null,'Ford','Focus'),
		(null,'Ford','Mondeo'),
		(null,'Ford','Fiesta'),
		(null,'Ducati','Panigale'),
		(null,'Ducati','Multistrada'),
		(null,'Ducati','Diavel'),
		(null,'Yamaha','R1'),
		(null,'Yamaha','R6'),
		(null,'Yamaha','WR450F'),
		(null,'Volvo','FH16'),
		(null,'Volvo','FH'),
		(null,'Volvo','FM');


#describe radnik;
#select * from radnik;

insert into radnik (sifra,ime,prezime,oib,kontaktbroj,email,brojugovora)
values	(null,'Marko','Šipić','26967845631','0956772294','marko.sipic@gmail.com','BR.166110-1'),
		(null,'Tomislav','Ribić','83567892431','0919984488','ribict1@gmail.com','BR.167111-2'),
		(null,'Rafael','Dumačić','90248960201','0959523266','rdumancic@gmail.com','BR.168112-3');
	
#describe servis;
#select opis from servis where sifra=1;
#select * from servis;

