-- DROP SEQUENCE public.seq_cliente;

CREATE SEQUENCE public.seq_cliente
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_cliente_pago;

CREATE SEQUENCE public.seq_cliente_pago
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_cotizador;

CREATE SEQUENCE public.seq_cotizador
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_credito_negociacion;

CREATE SEQUENCE public.seq_credito_negociacion
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_cuenta;

CREATE SEQUENCE public.seq_cuenta
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_datos;

CREATE SEQUENCE public.seq_datos
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_detalle_credito;

CREATE SEQUENCE public.seq_detalle_credito
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_devolucion;

CREATE SEQUENCE public.seq_devolucion
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_direccion_cliente;

CREATE SEQUENCE public.seq_direccion_cliente
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_excepcion;

CREATE SEQUENCE public.seq_excepcion
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_excepcion_rol;

CREATE SEQUENCE public.seq_excepcion_rol
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_habilitante;

CREATE SEQUENCE public.seq_habilitante
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_ingreso_egreso_cliente;

CREATE SEQUENCE public.seq_ingreso_egreso_cliente
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_negociacion;

CREATE SEQUENCE public.seq_negociacion
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_parametro;

CREATE SEQUENCE public.seq_parametro
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 400
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_patrimonio;

CREATE SEQUENCE public.seq_patrimonio
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_precio_oro;

CREATE SEQUENCE public.seq_precio_oro
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_proceso;

CREATE SEQUENCE public.seq_proceso
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_referencia_personal;

CREATE SEQUENCE public.seq_referencia_personal
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_registrar_pago;

CREATE SEQUENCE public.seq_registrar_pago
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_riesgo_acumulado;

CREATE SEQUENCE public.seq_riesgo_acumulado
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_rol_tipo_documento;

CREATE SEQUENCE public.seq_rol_tipo_documento
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_rubro;

CREATE SEQUENCE public.seq_rubro
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_tasacion;

CREATE SEQUENCE public.seq_tasacion
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_telefono;

CREATE SEQUENCE public.seq_telefono
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_tipo_documento;

CREATE SEQUENCE public.seq_tipo_documento
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_tracking;

CREATE SEQUENCE public.seq_tracking
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_variable_crediticia;

CREATE SEQUENCE public.seq_variable_crediticia
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.seq_verificacion_telefonica;

CREATE SEQUENCE public.seq_verificacion_telefonica
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
-- public.tb_mi_parametro definition

-- Drop table

-- DROP TABLE public.tb_mi_parametro;

CREATE TABLE public.tb_mi_parametro (
	id numeric(10) NOT NULL,
	nombre varchar(50) NULL,
	valor varchar(4000) NULL,
	tipo varchar(50) NULL,
	estado varchar(20) NULL,
	caracteritica_uno varchar(100) NULL,
	caracteristica_dos varchar(100) NULL,
	orden numeric NULL,
	archivo oid NULL,
	fecha_creacion date NULL,
	CONSTRAINT tb_sa_parameter_pk PRIMARY KEY (id)
);


-- public.tb_qo_cliente definition

-- Drop table

-- DROP TABLE public.tb_qo_cliente;

CREATE TABLE public.tb_qo_cliente (
	id numeric(10) NOT NULL DEFAULT nextval('seq_cliente'::regclass),
	cedula_cliente varchar(20) NOT NULL,
	primer_nombre varchar(100) NULL,
	segundo_nombre varchar(100) NULL,
	apellido_paterno varchar(100) NULL,
	genero varchar(10) NULL,
	estado_civil varchar(10) NULL,
	cargas_familiares numeric(5) NULL,
	fecha_nacimiento date NULL,
	lugar_nacimiento varchar(100) NULL,
	nacionalidad numeric(10) NULL,
	nivel_educacion varchar(30) NULL,
	actividad_economica varchar(200) NULL,
	edad numeric(3) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(100) NULL,
	apellido_materno varchar(100) NULL,
	publicidad varchar(100) NULL,
	campania varchar(100) NULL,
	email varchar(100) NULL,
	separacion_bienes varchar(100) NULL,
	canal_contacto varchar(100) NULL,
	profesion varchar(50) NULL,
	aprobado_web_mupi varchar(10) NULL,
	nombre_completo varchar(403) NULL,
	agencia numeric(10) NULL,
	usuario varchar(100) NULL,
	ingresos numeric(10) NULL,
	egresos numeric(10) NULL,
	pasivos numeric(10) NULL,
	activos numeric NULL,
	CONSTRAINT constraint_name UNIQUE (cedula_cliente),
	CONSTRAINT tb_qo_cliente_pkey PRIMARY KEY (id)
);


-- public.tb_qo_cliente_pago definition

-- Drop table

-- DROP TABLE public.tb_qo_cliente_pago;

CREATE TABLE public.tb_qo_cliente_pago (
	id numeric NOT NULL DEFAULT nextval('seq_cliente_pago'::regclass),
	nombre_cliente varchar(100) NULL,
	cedula varchar(15) NULL,
	codigo_operacion varchar NULL,
	codigo_cuenta_mupi varchar NULL,
	tipo_credito varchar(50) NULL,
	valor_precancelado numeric(10,2) NULL,
	valor_depositado numeric(10,2) NULL,
	observacion varchar(100) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	usuario_creacion varchar(100) NULL,
	usuario_actualizacion varchar(100) NULL,
	estado varchar(20) NOT NULL,
	tipo varchar(15) NULL,
	codigo varchar(20) NULL,
	id_agencia numeric(10) NULL,
	asesor varchar(100) NULL,
	aprobador varchar(100) NULL,
	CONSTRAINT tb_qo_cliente_pago_un UNIQUE (codigo),
	CONSTRAINT tb_qo_pago_pk PRIMARY KEY (id)
);


-- public.tb_qo_devolucion definition

-- Drop table

-- DROP TABLE public.tb_qo_devolucion;

CREATE TABLE public.tb_qo_devolucion (
	id numeric(10) NOT NULL,
	estado varchar(20) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NULL,
	codigo varchar(20) NOT NULL,
	asesor varchar(100) NOT NULL,
	aprobador varchar(100) NULL,
	id_agencia numeric(20) NOT NULL,
	nombre_cliente varchar(400) NOT NULL,
	cedula_cliente varchar(20) NOT NULL,
	codigo_operacion varchar(30) NULL,
	nivel_educacion varchar(30) NULL,
	estado_civil varchar(30) NULL,
	separacion_bienes varchar(10) NULL,
	fecha_nacimiento date NULL,
	nacionalidad varchar(50) NULL,
	lugar_nacimiento varchar(50) NULL,
	tipo_cliente varchar(40) NULL,
	observaciones varchar(250) NULL,
	agencia_entrega varchar(50) NULL,
	valor_custodia_aprox numeric NULL,
	code_herederos varchar(1000) NULL,
	code_detalle_credito varchar(1000) NULL,
	code_detalle_garantia varchar(1000) NULL,
	genero varchar(30) NULL,
	fecha_arribo date NULL,
	nombre_agencia_solicitud varchar(30) NULL,
	id_agencia_entrega numeric NULL,
	fecha_aprobacion_solicitud date NULL,
	codigo_operacion_madre varchar(50) NULL,
	funda_actual varchar(50) NULL,
	funda_madre varchar(50) NULL,
	arribo bool NULL,
	ciudad_tevcol varchar(50) NULL,
	valor_avaluo numeric NULL,
	peso_bruto numeric NULL,
	devuelto bool NULL,
	observacion_aprobador varchar(1000) NULL,
	nombre_apoderado varchar(100) NULL,
	cedula_apoderado varchar(50) NULL,
	fecha_efectiva date NULL,
	CONSTRAINT tb_qo_devolucion_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_devolucion_un UNIQUE (codigo)
);


-- public.tb_qo_estado_garantia definition

-- Drop table

-- DROP TABLE public.tb_qo_estado_garantia;

CREATE TABLE public.tb_qo_estado_garantia (
	id numeric(10) NOT NULL,
	proceso varchar(20) NOT NULL,
	caja_fuerte varchar(20) NOT NULL,
	ubicacion varchar(20) NOT NULL,
	CONSTRAINT tb_qo_estado_garantia_pk PRIMARY KEY (id)
);


-- public.tb_qo_excepcion_rol definition

-- Drop table

-- DROP TABLE public.tb_qo_excepcion_rol;

CREATE TABLE public.tb_qo_excepcion_rol (
	rol varchar NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar NULL,
	id numeric NOT NULL DEFAULT nextval('seq_excepcion_rol'::regclass),
	excepcion varchar NULL,
	CONSTRAINT tb_qo_excepcion_rol_pk PRIMARY KEY (id)
);


-- public.tb_qo_proceso definition

-- Drop table

-- DROP TABLE public.tb_qo_proceso;

CREATE TABLE public.tb_qo_proceso (
	id numeric(10) NOT NULL DEFAULT nextval('seq_proceso'::regclass),
	id_proceso numeric(10) NULL,
	id_referencia numeric(10) NOT NULL,
	proceso varchar(40) NOT NULL,
	estado_proceso varchar(30) NOT NULL,
	usuario varchar(100) NULL,
	estado varchar(30) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NULL,
	CONSTRAINT tb_qo_proceso_pk PRIMARY KEY (id)
);


-- public.tb_qo_rol_tipo_documento definition

-- Drop table

-- DROP TABLE public.tb_qo_rol_tipo_documento;

CREATE TABLE public.tb_qo_rol_tipo_documento (
	id numeric(10) NOT NULL,
	id_tipo_documento numeric(10) NULL,
	id_rol numeric(10) NULL,
	estado varchar(100) NULL,
	proceso varchar(100) NULL,
	estado_operacion varchar(100) NULL,
	descarga_plantilla bool NULL,
	carga_documento bool NULL,
	descarga_documento bool NULL,
	carga_documento_adicional bool NULL,
	descarga_documento_adicional bool NULL,
	accion_uno bool NULL,
	accion_dos bool NULL,
	accion_tres bool NULL,
	CONSTRAINT pk_rol_tipodocumento PRIMARY KEY (id)
);


-- public.tb_qo_tipo_archivo definition

-- Drop table

-- DROP TABLE public.tb_qo_tipo_archivo;

CREATE TABLE public.tb_qo_tipo_archivo (
	id numeric(10) NOT NULL,
	tipo_archivo varchar(100) NULL,
	fecha_creacion date NULL,
	descripcion varchar(100) NULL,
	estado varchar(20) NULL,
	plantilla varchar(200) NULL,
	tipo_plantilla varchar(20) NULL,
	plantilla_uno varchar(200) NULL,
	plantilla_dos varchar(200) NULL,
	plantilla_tres varchar(200) NULL,
	CONSTRAINT pk_archivo PRIMARY KEY (id)
);


-- public.tb_qo_tipo_documento definition

-- Drop table

-- DROP TABLE public.tb_qo_tipo_documento;

CREATE TABLE public.tb_qo_tipo_documento (
	id numeric(10) NOT NULL,
	tipo_documento varchar(100) NULL,
	fecha_creacion date NULL,
	descripcion varchar(100) NULL,
	estado varchar(20) NULL,
	plantilla varchar(200) NULL,
	tipo_plantilla varchar(20) NULL,
	plantilla_uno varchar(200) NULL,
	plantilla_dos varchar(200) NULL,
	plantilla_tres varchar(200) NULL,
	proceso varchar(100) NULL,
	estado_operacion varchar(100) NULL,
	servicio varchar(500) NULL,
	CONSTRAINT pk_documento PRIMARY KEY (id)
);


-- public.tb_qo_tracking definition

-- Drop table

-- DROP TABLE public.tb_qo_tracking;

CREATE TABLE public.tb_qo_tracking (
	id numeric NOT NULL DEFAULT nextval('seq_tracking'::regclass),
	proceso varchar(20) NULL,
	actividad varchar(50) NULL,
	seccion varchar(50) NULL,
	codigo_bpm varchar(50) NULL,
	codigo_operacion_softbank varchar(50) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(20) NULL,
	usuario_creacion varchar(100) NULL,
	usuario_actualizacion varchar(100) NULL,
	nombre_asesor varchar(100) NULL,
	tiempo_transcurrido numeric NULL,
	fecha_inicio timestamp NULL,
	fecha_fin timestamp NULL,
	observacion varchar(500) NULL
);
CREATE INDEX tb_qo_tracking_id_idx ON public.tb_qo_tracking USING btree (id);


-- public.tb_qo_verificacion_telefonica definition

-- Drop table

-- DROP TABLE public.tb_qo_verificacion_telefonica;

CREATE TABLE public.tb_qo_verificacion_telefonica (
	id numeric(10) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NULL,
	estado varchar(20) NOT NULL,
	asesor varchar(100) NOT NULL,
	aprobador varchar(100) NULL,
	codigo varchar(20) NOT NULL,
	nombre_cliente varchar(400) NOT NULL,
	cedula_cliente varchar(20) NOT NULL,
	id_agencia numeric(10) NOT NULL,
	codigo_operacion varchar(20) NULL,
	CONSTRAINT tb_qo_verificacion_telefonica_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_verificacion_telefonica_un UNIQUE (codigo)
);


-- public.tb_qo_archivo_cliente definition

-- Drop table

-- DROP TABLE public.tb_qo_archivo_cliente;

CREATE TABLE public.tb_qo_archivo_cliente (
	id numeric(10) NOT NULL,
	nombre_archivo varchar(150) NULL,
	archivo oid NULL,
	estado varchar(20) NULL,
	id_cliente numeric(10) NULL,
	id_tipo_archivo numeric(10) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	CONSTRAINT pk_archivo_cliente PRIMARY KEY (id),
	CONSTRAINT fk_archivo_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id),
	CONSTRAINT fk_tipo_archivo FOREIGN KEY (id_tipo_archivo) REFERENCES tb_qo_tipo_archivo(id)
);


-- public.tb_qo_cotizador definition

-- Drop table

-- DROP TABLE public.tb_qo_cotizador;

CREATE TABLE public.tb_qo_cotizador (
	id numeric(10) NOT NULL DEFAULT nextval('seq_cotizador'::regclass),
	grado_interes varchar(100) NULL,
	motivo_de_desestimiento varchar(100) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(100) NULL,
	id_cliente numeric(10) NULL,
	codigo_cotizacion varchar(11) NULL,
	asesor varchar(100) NULL,
	id_agencia numeric(10) NULL,
	CONSTRAINT tb_qo_cotizador_pkey PRIMARY KEY (id),
	CONSTRAINT fk_cotizador_cliente_id_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_cuenta_bancaria_cliente definition

-- Drop table

-- DROP TABLE public.tb_qo_cuenta_bancaria_cliente;

CREATE TABLE public.tb_qo_cuenta_bancaria_cliente (
	id numeric(10) NOT NULL DEFAULT nextval('seq_cuenta'::regclass),
	id_cliente numeric(10) NOT NULL,
	id_softbank numeric(10) NULL,
	banco numeric(10) NOT NULL,
	cuenta varchar(30) NOT NULL,
	es_ahorros bool NOT NULL,
	estado varchar(20) NOT NULL,
	CONSTRAINT tb_qo_cuenta_bancaria_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_cuenta_bancaria_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_dato_trabajo_cliente definition

-- Drop table

-- DROP TABLE public.tb_qo_dato_trabajo_cliente;

CREATE TABLE public.tb_qo_dato_trabajo_cliente (
	id numeric(10) NOT NULL,
	id_cliente numeric(10) NOT NULL,
	id_softbank numeric(10) NULL,
	actividad_economica numeric(10) NOT NULL,
	actividad_economica_mupi varchar(20) NULL,
	es_relacion_dependencia bool NOT NULL,
	origen_ingreso varchar(20) NOT NULL,
	ocupacion varchar(20) NOT NULL,
	cargo varchar(20) NOT NULL,
	esprincipal bool NOT NULL,
	nombre_empresa varchar(50) NULL,
	estado varchar(20) NULL,
	CONSTRAINT tb_qo_dato_trabajo_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_dato_trabajo_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_detalle_credito definition

-- Drop table

-- DROP TABLE public.tb_qo_detalle_credito;

CREATE TABLE public.tb_qo_detalle_credito (
	id numeric(10) NOT NULL DEFAULT nextval('seq_detalle_credito'::regclass),
	plazo numeric(10) NULL,
	monto_preaprobado numeric(15,2) NULL,
	recibir_cliente numeric(15,2) NULL,
	costo_nueva_operacion numeric(15,2) NULL,
	costo_custodia numeric(15,2) NULL,
	costo_transporte numeric(15,2) NULL,
	costo_credito numeric(15,2) NULL,
	costo_seguro numeric(15,2) NULL,
	costo_resguardado numeric(15,2) NULL,
	costo_estimado numeric(15,2) NULL,
	valor_cuota numeric(15,2) NULL,
	id_cotizador numeric(10) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(100) NULL,
	costo_valoracion numeric(15,2) NULL,
	costo_tasacion numeric(15,2) NULL,
	solca numeric(15,2) NULL,
	periodo_plazo varchar(20) NULL,
	CONSTRAINT pk_tb_qo_detalle_credito PRIMARY KEY (id),
	CONSTRAINT fk_detalle_credito_cotizador_id_cotizador FOREIGN KEY (id_cotizador) REFERENCES tb_qo_cotizador(id)
);


-- public.tb_qo_direccion_cliente definition

-- Drop table

-- DROP TABLE public.tb_qo_direccion_cliente;

CREATE TABLE public.tb_qo_direccion_cliente (
	id numeric(10) NOT NULL,
	direccion_legal bool NOT NULL,
	direccion_envio_correspondencia bool NOT NULL,
	tipo_direccion varchar(10) NOT NULL,
	barrio varchar(50) NULL,
	sector varchar(50) NOT NULL,
	calle_principal varchar(50) NOT NULL,
	numeracion varchar(50) NOT NULL,
	referencia_ubicacion varchar(50) NOT NULL,
	calle_segundaria varchar(50) NULL,
	tipo_vivienda varchar(20) NOT NULL,
	id_cliente numeric(10) NOT NULL,
	estado varchar(10) NOT NULL,
	division_politica numeric(20) NULL,
	id_softbank numeric(10) NULL,
	CONSTRAINT tb_qo_direccion_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_direccion_cliente_un UNIQUE (id_softbank),
	CONSTRAINT tb_qo_direccion_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_ingreso_egreso_cliente definition

-- Drop table

-- DROP TABLE public.tb_qo_ingreso_egreso_cliente;

CREATE TABLE public.tb_qo_ingreso_egreso_cliente (
	id numeric(10) NOT NULL,
	valor numeric(10) NULL,
	id_cliente numeric(10) NOT NULL,
	es_ingreso bool NULL,
	es_egreso bool NULL,
	estado varchar(10) NOT NULL,
	CONSTRAINT tb_qo_ingreso_egreso_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_ingreso_egreso_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_negociacion definition

-- Drop table

-- DROP TABLE public.tb_qo_negociacion;

CREATE TABLE public.tb_qo_negociacion (
	id numeric(10) NOT NULL DEFAULT nextval('seq_negociacion'::regclass),
	id_cliente numeric(10) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NULL,
	estado varchar(100) NOT NULL,
	asesor varchar(100) NOT NULL,
	aprobador varchar(100) NULL,
	CONSTRAINT tb_qo_negociacion_pkey PRIMARY KEY (id),
	CONSTRAINT fk_negociacion_cliente_id_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_patrimonio definition

-- Drop table

-- DROP TABLE public.tb_qo_patrimonio;

CREATE TABLE public.tb_qo_patrimonio (
	id numeric(10) NOT NULL,
	activos varchar(150) NULL,
	avaluo numeric(10) NULL,
	pasivos varchar(20) NULL,
	ifis numeric(10) NULL,
	infocorp numeric(10) NULL,
	id_cliente numeric(10) NOT NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(10) NOT NULL,
	CONSTRAINT pk_patrimonio_cliente PRIMARY KEY (id),
	CONSTRAINT fk_archivo_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_precio_oro definition

-- Drop table

-- DROP TABLE public.tb_qo_precio_oro;

CREATE TABLE public.tb_qo_precio_oro (
	id numeric(10) NOT NULL DEFAULT nextval('seq_precio_oro'::regclass),
	tipo_oro numeric(10) NULL,
	peso_neto_estimado numeric(15,2) NULL,
	id_cotizador numeric(10) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(100) NULL,
	precio numeric(15,2) NULL,
	CONSTRAINT pk_tb_qo_precio_oro PRIMARY KEY (id),
	CONSTRAINT fk_precio_oro_cotizador_id_cotizador FOREIGN KEY (id_cotizador) REFERENCES tb_qo_cotizador(id)
);


-- public.tb_qo_referencia_personal definition

-- Drop table

-- DROP TABLE public.tb_qo_referencia_personal;

CREATE TABLE public.tb_qo_referencia_personal (
	id numeric(10) NOT NULL DEFAULT nextval('seq_referencia_personal'::regclass),
	parentesco varchar(100) NULL,
	direccion varchar(100) NULL,
	telefono_movil varchar(100) NULL,
	telefono_fijo varchar(100) NULL,
	id_cliente numeric(10) NULL,
	estado varchar(100) NOT NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	id_softbank numeric(10) NULL,
	nombres varchar(50) NULL,
	apellidos varchar(50) NULL,
	CONSTRAINT pk_referencia_personal PRIMARY KEY (id),
	CONSTRAINT fk_referencia_personal_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_riesgo_acumulado definition

-- Drop table

-- DROP TABLE public.tb_qo_riesgo_acumulado;

CREATE TABLE public.tb_qo_riesgo_acumulado (
	id numeric(10) NOT NULL,
	estado varchar(10) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NULL,
	id_cliente numeric(10) NULL,
	referencia varchar(200) NULL,
	numero_operacion varchar(100) NULL,
	codigo_cartera_quski varchar(100) NULL,
	tipo_operacion varchar(100) NULL,
	fecha_efectiva date NULL,
	fecha_vencimiento date NULL,
	interes_mora numeric(10,2) NULL,
	saldo numeric(10) NULL,
	valor_al_dia numeric(10) NULL,
	valor_al_dia_mas_cuota_actual numeric(10) NULL,
	valor_cancela_prestamo numeric(10) NULL,
	valor_proyectado_cuota_actual numeric(10) NULL,
	dias_mora_actual numeric(10) NULL,
	numero_cuotas_totales numeric(10) NULL,
	nombre_producto varchar(100) NULL,
	numero_cuotas_faltantes numeric(10) NULL,
	primera_cuota_vigente numeric(10) NULL,
	estado_primera_cuota_vigente varchar(100) NULL,
	numero_garantias_reales numeric(10) NULL,
	estado_operacion varchar(100) NULL,
	es_demandada bool NULL,
	id_softbank numeric(10) NULL,
	id_moneda numeric(10) NULL,
	id_negociacion numeric(10) NULL,
	id_cotizador numeric(10) NULL,
	CONSTRAINT tb_qo_riesgo_acumulado_fk FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);


-- public.tb_qo_telefono_cliente definition

-- Drop table

-- DROP TABLE public.tb_qo_telefono_cliente;

CREATE TABLE public.tb_qo_telefono_cliente (
	id numeric(10) NOT NULL DEFAULT nextval('seq_telefono'::regclass),
	id_cliente numeric(10) NOT NULL,
	id_softbank numeric(10) NULL,
	tipo_telefono varchar(20) NOT NULL,
	numero varchar(20) NULL,
	estado varchar(20) NOT NULL,
	CONSTRAINT tb_qo_telefono_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_telefono_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);


-- public.tb_qo_variables_crediticias definition

-- Drop table

-- DROP TABLE public.tb_qo_variables_crediticias;

CREATE TABLE public.tb_qo_variables_crediticias (
	id numeric(10) NOT NULL DEFAULT nextval('seq_variable_crediticia'::regclass),
	nombre varchar(50) NULL,
	valor varchar(100) NULL,
	id_cotizador numeric(10) NULL,
	id_negociacion numeric(10) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(50) NULL,
	orden varchar(10) NULL,
	codigo varchar(100) NULL,
	CONSTRAINT tb_qo_variables_crediticias_pkey PRIMARY KEY (id),
	CONSTRAINT cotizador FOREIGN KEY (id_cotizador) REFERENCES tb_qo_cotizador(id),
	CONSTRAINT negociacion FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);


-- public.tb_qo_credito_negociacion definition

-- Drop table

-- DROP TABLE public.tb_qo_credito_negociacion;

CREATE TABLE public.tb_qo_credito_negociacion (
	id numeric(10) NOT NULL,
	plazo_credito numeric(15,2) NULL,
	monto_preaprobado numeric(15,2) NULL,
	valor_cuota numeric(15,2) NULL,
	id_negociacion numeric(10) NOT NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(100) NULL,
	fecha_vencimiento date NULL,
	id_agencia numeric(10) NULL,
	tipo varchar(100) NULL,
	codigo varchar(50) NULL,
	monto_solicitado numeric(15,2) NULL,
	monto_diferido numeric(15,2) NULL,
	monto_desembolso numeric(15,2) NULL,
	neto_al_cliente numeric(15,2) NULL,
	a_recibir_cliente numeric(15,2) NULL,
	a_pagar_cliente numeric(15,2) NULL,
	riesgo_total_cliente numeric(15,2) NULL,
	tipo_cartera_quski varchar(20) NULL,
	estado_softbank varchar(20) NULL,
	fecha_efectiva date NULL,
	total_costo_nueva_operacion numeric(10) NULL,
	numero_funda varchar(20) NULL,
	descripcion_producto varchar(100) NULL,
	pago_dia date NULL,
	codigo_tipo_funda varchar(20) NULL,
	uri_imagen_sin_funda varchar(50) NULL,
	uri_imagen_con_funda varchar(50) NULL,
	tabla_amortizacion varchar(20) NULL,
	monto_financiado numeric(10) NULL,
	identificacion_codeudor varchar(20) NULL,
	nombre_completo_codeudor varchar(200) NULL,
	fecha_nacimiento_codeudor date NULL,
	identificacion_apoderado varchar(20) NULL,
	nombre_completo_apoderado varchar(200) NULL,
	fecha_nacimiento_apoderado date NULL,
	numero_cuenta varchar(50) NULL,
	numero_operacion varchar(50) NULL,
	total_interes_vencimiento numeric(10) NULL,
	deuda_inicial numeric(10) NULL,
	periodo_plazo varchar(10) NULL,
	periodicidad_plazo varchar(10) NULL,
	valor_a_recibir numeric(15,2) NULL,
	valor_a_pagar numeric(15,2) NULL,
	costo_custodia numeric(15,2) NULL,
	costo_fideicomiso numeric(15,2) NULL,
	costo_seguro numeric(15,2) NULL,
	costo_tasacion numeric(15,2) NULL,
	costo_transporte numeric(15,2) NULL,
	costo_valoracion numeric(15,2) NULL,
	impuesto_solca numeric(15,2) NULL,
	forma_pago_impuesto_solca varchar(10) NULL,
	forma_pago_capital varchar(10) NULL,
	forma_pago_custodia varchar(10) NULL,
	forma_pago_fideicomiso varchar(10) NULL,
	forma_pago_interes varchar(10) NULL,
	forma_pago_mora varchar(10) NULL,
	forma_pago_gasto_cobranza varchar(10) NULL,
	forma_pago_seguro varchar(10) NULL,
	forma_pago_tasador varchar(10) NULL,
	forma_pago_transporte varchar(10) NULL,
	forma_pago_valoracion varchar(10) NULL,
	saldo_interes numeric(15,2) NULL,
	saldo_mora numeric(15,2) NULL,
	gasto_cobranza numeric(15,2) NULL,
	cuota numeric(15,2) NULL,
	saldo_capital_renov numeric(15,2) NULL,
	monto_previo_desembolso numeric(15,2) NULL,
	total_gastos_nueva_operacion numeric(15,2) NULL,
	total_costos_operacion_anterior numeric(15,2) NULL,
	custodia_devengada numeric(15,2) NULL,
	forma_pago_custodia_devengada varchar(10) NULL,
	tipo_oferta varchar(10) NULL,
	porcentaje_flujo_planeado numeric(15,2) NULL,
	dividendo_flujo_planeado numeric(15,2) NULL,
	dividendo_prorrateo numeric(15,2) NULL,
	firmante_operacion varchar(40) NULL,
	tipo_cliente varchar(40) NULL,
	numero_coutas numeric(10) NULL,
	codigo_cash varchar(20) NULL,
	codigo_devuelto varchar(20) NULL,
	descripcion_devuelto varchar(400) NULL,
	cobertura varchar(3) NULL,
	numero_operacion_madre varchar(40) NULL,
	excepcion_operativa varchar(20) NULL,
	fecha_regularizacion date NULL,
	CONSTRAINT pk_tb_qo_credito_negociacion PRIMARY KEY (id),
	CONSTRAINT fk_credito_negociacion_negociar_id_negociacion FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);


-- public.tb_qo_documento_habilitante definition

-- Drop table

-- DROP TABLE public.tb_qo_documento_habilitante;

CREATE TABLE public.tb_qo_documento_habilitante (
	id numeric(10) NOT NULL DEFAULT nextval('seq_habilitante'::regclass),
	nombre_archivo varchar(150) NULL,
	archivo oid NULL,
	estado varchar(20) NULL,
	id_tipo_documento numeric(10) NULL,
	id_cotizacion numeric(10) NULL,
	id_negociacion numeric(10) NULL,
	id_cliente numeric(10) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	proceso varchar(100) NULL,
	id_referencia varchar(300) NULL,
	object_id varchar(300) NULL,
	estado_operacion varchar(100) NULL,
	tipo_ducumento varchar(200) NULL,
	CONSTRAINT pk_documento_habilitantes PRIMARY KEY (id),
	CONSTRAINT un_doc_cot_id UNIQUE (id_tipo_documento, id_cotizacion),
	CONSTRAINT un_doc_neg_id UNIQUE (id_tipo_documento, id_negociacion),
	CONSTRAINT fk_con_cotizador_id FOREIGN KEY (id_cotizacion) REFERENCES tb_qo_cotizador(id),
	CONSTRAINT fk_con_tipo_habilitantes_id FOREIGN KEY (id_tipo_documento) REFERENCES tb_qo_tipo_documento(id),
	CONSTRAINT fk_doc_hab_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id),
	CONSTRAINT fk_doc_hab_negociacion FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);


-- public.tb_qo_excepcion definition

-- Drop table

-- DROP TABLE public.tb_qo_excepcion;

CREATE TABLE public.tb_qo_excepcion (
	id numeric(10) NOT NULL DEFAULT nextval('seq_excepcion'::regclass),
	tipo_excepcion varchar(20) NOT NULL,
	estado_excepcion varchar(20) NOT NULL,
	id_asesor varchar(100) NOT NULL,
	id_aprobador varchar(100) NULL,
	id_negociacion numeric(10) NOT NULL,
	estado varchar(10) NOT NULL,
	fecha_actualizacion date NULL,
	fecha_creacion date NOT NULL,
	observacion_asesor varchar(200) NULL,
	observacion_aprobador varchar(200) NULL,
	caracteristica varchar(160) NULL,
	mensaje_bre varchar(160) NULL,
	CONSTRAINT tb_qo_excepciones_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_excepciones_fk FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);


-- public.tb_qo_registrar_pago definition

-- Drop table

-- DROP TABLE public.tb_qo_registrar_pago;

CREATE TABLE public.tb_qo_registrar_pago (
	id numeric NOT NULL DEFAULT nextval('seq_registrar_pago'::regclass),
	id_pago numeric NULL,
	institucion_financiera varchar(50) NULL,
	cuentas varchar(50) NULL,
	fecha_pago date NULL,
	numero_deposito numeric NULL,
	valor_pagado numeric(10,2) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	usuario_creacion varchar(100) NULL,
	usuario_actualizacion varchar(100) NULL,
	id_comprobante varchar(100) NULL,
	estado varchar NULL,
	id_credito numeric(20) NULL,
	CONSTRAINT tb_qo_registra_pago_pkey PRIMARY KEY (id),
	CONSTRAINT tb_qo_registrar_pago_fk FOREIGN KEY (id_pago) REFERENCES tb_qo_cliente_pago(id),
	CONSTRAINT tb_qo_registrar_pago_fk_credito FOREIGN KEY (id_credito) REFERENCES tb_qo_credito_negociacion(id)
);


-- public.tb_qo_rubro definition

-- Drop table

-- DROP TABLE public.tb_qo_rubro;

CREATE TABLE public.tb_qo_rubro (
	id numeric(10) NOT NULL DEFAULT nextval('seq_rubro'::regclass),
	fecha_creacion date NOT NULL,
	estado varchar(20) NOT NULL,
	codigo varchar(20) NOT NULL,
	forma_pago varchar(20) NOT NULL,
	factor varchar(20) NOT NULL,
	valor numeric(10) NOT NULL,
	descripcion varchar(100) NULL,
	id_credito numeric(10) NOT NULL,
	CONSTRAINT tb_qo_rubro_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_rubro_fk FOREIGN KEY (id_credito) REFERENCES tb_qo_credito_negociacion(id)
);


-- public.tb_qo_tasacion definition

-- Drop table

-- DROP TABLE public.tb_qo_tasacion;

CREATE TABLE public.tb_qo_tasacion (
	id numeric(10) NOT NULL DEFAULT nextval('seq_tasacion'::regclass),
	numero_piezas int4 NULL,
	tipo_joya varchar(10) NULL,
	estado_joya varchar(50) NULL,
	descripcion varchar(100) NULL,
	descuento_peso_piedra numeric(15,2) NULL,
	peso_neto numeric(15,2) NULL,
	peso_bruto numeric(15,2) NULL,
	valor_oro numeric(15,2) NULL,
	valor_comercial numeric(15,2) NULL,
	descuento_suelda numeric(15,2) NULL,
	valor_avaluo numeric(15,2) NULL,
	valor_realizacion numeric(15,2) NULL,
	descuento_peso_piedra_retasacion numeric(15,2) NULL,
	peso_neto_retasacion numeric(15,2) NULL,
	peso_bruto_retasacion numeric(15,2) NULL,
	valor_oro_retasacion numeric(15,2) NULL,
	valor_comercial_retasacion numeric(15,2) NULL,
	descuento_suelda_retasacion numeric(15,2) NULL,
	valor_avaluo_retasacion numeric(15,2) NULL,
	valor_realizacion_retasacion numeric(15,2) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	id_credito_negociacion numeric(10) NULL,
	tipo_oro varchar(15) NULL,
	estado varchar(20) NULL,
	tiene_piedras bool NULL,
	detalle_piedras varchar(100) NULL,
	numero_garantia numeric(10) NULL,
	numero_expediente numeric(10) NULL,
	tipo_garantia varchar(20) NULL,
	sub_tipo_garantia varchar(20) NULL,
	id_cotizador numeric(10) NULL,
	CONSTRAINT tb_qo_tasacion_pkey PRIMARY KEY (id),
	CONSTRAINT tb_qo_tasacion_fk FOREIGN KEY (id_cotizador) REFERENCES tb_qo_cotizador(id),
	CONSTRAINT tq_qo_credito_negociacion_fk FOREIGN KEY (id_credito_negociacion) REFERENCES tb_qo_credito_negociacion(id)
);
