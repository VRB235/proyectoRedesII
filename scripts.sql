create database proyredes2;

create table cliente(
	cli_id serial,
	cli_nombre character varying unique,
	cli_puerto integer,
	cli_ip character varying,
	cli_num_descargas integer,
	constraint pk_cliente primary key (cli_id)
);

create table video(
    vid_id serial,
    vid_nombre character varying,
    vid_ruta character varying,
    vid_tamano character varying,
    vid_num_descargados integer,
    fk_usuario integer,
    constraint pk_video primary key (vid_id),
    constraint fk_usuario foreign key (fk_usuario) references cliente(cli_id)
);


insert into cliente (cli_nombre, cli,puerto, cli_ip, cli_num_descargas) values('Leonardo Azocar',6321,'localhost',0);
insert into cliente (cli_nombre, cli,puerto, cli_ip, cli_num_descargas) values('Miguel Medina',6321,'localhost',0);
insert into cliente (cli_nombre, cli,puerto, cli_ip, cli_num_descargas) values('Jorge Marin',6321,'localhost',0);

insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 1','../../Archivos/arch1','3554000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 2','../../Archivos/arch2','1110000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 3','../../Archivos/arch3','4356000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 4','../../Archivos/arch4','1554000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 5','../../Archivos/arch5','5964000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 6','../../Archivos/arch6','820000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 7','../../Archivos/arch7','1145000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 8','../../Archivos/arch8','1331000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 9','../../Archivos/arch9','1416000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 10','../../Archivos/arch10','1354000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 11','../../Archivos/arch11','1218000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 12','../../Archivos/arch12','10522000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 13','../../Archivos/arch13','3321000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 14','../../Archivos/arch14','1496000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 15','../../Archivos/arch15','3984000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 16','../../Archivos/arch16','17428000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 17','../../Archivos/arch17','2131000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 18','../../Archivos/arch18','991000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 19','../../Archivos/arch19','1694000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo 20','../../Archivos/arch20','4496000',0,1);

