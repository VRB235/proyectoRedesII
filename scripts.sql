create database descargasBD;

create table cliente(
	int cli_id,
	character varying cli_nombre,
	int cli_puerto,
	character varying cli_ip,
	int cli_num_descargas;
	constraint pk_cliente primary key (cli_id)
);

create table video(
	int vid_id,
	character varying vid_nombre;
	character varying vid_ruta;
	character varying vid_tamano;
	int vid_num_descargados;
);