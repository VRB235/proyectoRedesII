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


insert into cliente (cli_nombre, cli,puerto, cli_ip, cli_num_descargas) values('Leonardo',6321,'localhost',0);
insert into cliente (cli_nombre, cli,puerto, cli_ip, cli_num_descargas) values('Miguel',6321,'localhost',0);
insert into cliente (cli_nombre, cli,puerto, cli_ip, cli_num_descargas) values('Jorge',6321,'localhost',0);

insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo1.pdf','../../Archivos/arch1','3554000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo2.pdf','../../Archivos/arch2','1110000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo3.pdf','../../Archivos/arch3','4356000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo4.pdf','../../Archivos/arch4','1554000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo5.pdf','../../Archivos/arch5','5964000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo6.pdf','../../Archivos/arch6','820000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo7.pdf','../../Archivos/arch7','1145000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo8.pdf','../../Archivos/arch8','1331000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo9.pdf','../../Archivos/arch9','1416000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo10.pdf','../../Archivos/arch10','1354000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo11.pdf','../../Archivos/arch11','1218000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo12.pdf','../../Archivos/arch12','10522000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo13.pdf','../../Archivos/arch13','3321000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo14.pdf','../../Archivos/arch14','1496000',0,3);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo15.pdf','../../Archivos/arch15','3984000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo16.pdf','../../Archivos/arch16','17428000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo17.pdf','../../Archivos/arch17','2131000',0,1);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo18.pdf','../../Archivos/arch18','991000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo19.pdf','../../Archivos/arch19','1694000',0,2);
insert into video(vid_nombre, vid_ruta, vid_tamano, vid_num_descargados,fk_usuario) values ('archivo20.pdf','../../Archivos/arch20','4496000',0,1);

