create database proyredes2;

create table cliente(
	cli_id serial,
	cli_nombre character varying,
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