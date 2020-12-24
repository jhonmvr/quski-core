-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

COMMENT ON SCHEMA public IS 'standard public schema';

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
	NO CYCLE;-- public.persona definition

-- Drop table

-- DROP TABLE persona;

CREATE TABLE persona (
	id numeric(10) NOT NULL, -- This is comment for the column
	nombre varchar(50) NULL,
	apellido varchar(50) NULL,
	celular varchar(10) NULL,
	telefono varchar(9) NULL,
	cedula varchar(13) NULL,
	CONSTRAINT persona_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.persona.id IS 'This is comment for the column';


-- public.tb_mi_parametro definition

-- Drop table

-- DROP TABLE tb_mi_parametro;

CREATE TABLE tb_mi_parametro (
	id numeric(10) NOT NULL, -- id pk
	nombre varchar(50) NULL, -- Nombre de parametro relacionado a base
	valor varchar(4000) NULL, -- Valor variable relacionado al parametro
	tipo varchar(50) NULL, -- Tipo de parametro segun su funcion
	estado varchar(20) NULL, -- ACT o INA
	caracteritica_uno varchar(100) NULL, -- Caracteristica especial del parametro
	caracteristica_dos varchar(100) NULL, -- Segunda caracteristica especial del parametro
	orden numeric NULL, -- Orden de aparicion segun su tipo
	archivo oid NULL, -- Archivo adgunto al parametro
	fecha_creacion date NULL, -- Fecha de creacion del registro
	CONSTRAINT tb_sa_parameter_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.tb_mi_parametro.id IS 'id pk';
COMMENT ON COLUMN public.tb_mi_parametro.nombre IS 'Nombre de parametro relacionado a base';
COMMENT ON COLUMN public.tb_mi_parametro.valor IS 'Valor variable relacionado al parametro';
COMMENT ON COLUMN public.tb_mi_parametro.tipo IS 'Tipo de parametro segun su funcion';
COMMENT ON COLUMN public.tb_mi_parametro.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_mi_parametro.caracteritica_uno IS 'Caracteristica especial del parametro';
COMMENT ON COLUMN public.tb_mi_parametro.caracteristica_dos IS 'Segunda caracteristica especial del parametro';
COMMENT ON COLUMN public.tb_mi_parametro.orden IS 'Orden de aparicion segun su tipo';
COMMENT ON COLUMN public.tb_mi_parametro.archivo IS 'Archivo adgunto al parametro';
COMMENT ON COLUMN public.tb_mi_parametro.fecha_creacion IS 'Fecha de creacion del registro';


-- public.tb_qo_cliente definition

-- Drop table

-- DROP TABLE tb_qo_cliente;

CREATE TABLE tb_qo_cliente (
	id numeric(10) NOT NULL DEFAULT nextval('seq_cliente'::regclass), -- id pk
	cedula_cliente varchar(20) NOT NULL, -- Cedula del cliente
	primer_nombre varchar(100) NULL, -- Primer nombre del cliente
	segundo_nombre varchar(100) NULL, -- Segundo nombre del cliente
	apellido_paterno varchar(100) NULL, -- Primer apellido del cliente
	genero varchar(10) NULL, -- M o F
	estado_civil varchar(10) NULL, -- Estado civil por catalogo
	cargas_familiares numeric(5) NULL, -- Valores del 1 al 10
	fecha_nacimiento date NULL, -- Fecha de nacimiento del cliente
	lugar_nacimiento varchar(100) NULL, -- Lugar de nacimiento
	nacionalidad numeric(10) NULL, -- Nacionalidad en funcion del catalogo
	nivel_educacion varchar(30) NULL, -- Educacion en funcion del catalogo
	actividad_economica varchar(200) NULL, -- Actividad economica relacionada al cliente
	edad numeric(3) NULL, -- Edad del cliente
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(100) NULL, -- ACT o INA
	apellido_materno varchar(100) NULL, -- Segundo apellido del cliente
	publicidad varchar(100) NULL, -- Publicidad de entrada del cliente
	campania varchar(100) NULL, -- Campana relacionada al cliente
	email varchar(100) NULL, -- email del cliente
	separacion_bienes varchar(100) NULL, -- S o N
	canal_contacto varchar(100) NULL, -- Canal de entrada del cliente
	profesion varchar(50) NULL, -- Profesion relacionada al catalogo
	aprobado_web_mupi varchar(10) NULL, -- S o N
	nombre_completo varchar(403) NULL, -- Nombre completo del cliente
	agencia numeric(10) NULL, -- Agencia de registro del cliente
	usuario varchar(20) NULL, -- usuario que registro al cliente
	ingresos numeric(10) NULL, -- ingresos relacionados al cliente
	egresos numeric(10) NULL, -- Egresos relacionados al cliente
	pasivos numeric(10) NULL, -- Pasivos del cliente
	activos numeric NULL, -- Activos del cliente
	CONSTRAINT constraint_name UNIQUE (cedula_cliente),
	CONSTRAINT tb_qo_cliente_pkey PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_cliente.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_cliente.cedula_cliente IS 'Cedula del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.primer_nombre IS 'Primer nombre del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.segundo_nombre IS 'Segundo nombre del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.apellido_paterno IS 'Primer apellido del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.genero IS 'M o F';
COMMENT ON COLUMN public.tb_qo_cliente.estado_civil IS 'Estado civil por catalogo';
COMMENT ON COLUMN public.tb_qo_cliente.cargas_familiares IS 'Valores del 1 al 10';
COMMENT ON COLUMN public.tb_qo_cliente.fecha_nacimiento IS 'Fecha de nacimiento del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.lugar_nacimiento IS 'Lugar de nacimiento';
COMMENT ON COLUMN public.tb_qo_cliente.nacionalidad IS 'Nacionalidad en funcion del catalogo';
COMMENT ON COLUMN public.tb_qo_cliente.nivel_educacion IS 'Educacion en funcion del catalogo';
COMMENT ON COLUMN public.tb_qo_cliente.actividad_economica IS 'Actividad economica relacionada al cliente';
COMMENT ON COLUMN public.tb_qo_cliente.edad IS 'Edad del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_cliente.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_cliente.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_cliente.apellido_materno IS 'Segundo apellido del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.publicidad IS 'Publicidad de entrada del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.campania IS 'Campana relacionada al cliente';
COMMENT ON COLUMN public.tb_qo_cliente.email IS 'email del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.separacion_bienes IS 'S o N';
COMMENT ON COLUMN public.tb_qo_cliente.canal_contacto IS 'Canal de entrada del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.profesion IS 'Profesion relacionada al catalogo';
COMMENT ON COLUMN public.tb_qo_cliente.aprobado_web_mupi IS 'S o N';
COMMENT ON COLUMN public.tb_qo_cliente.nombre_completo IS 'Nombre completo del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.agencia IS 'Agencia de registro del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.usuario IS 'usuario que registro al cliente';
COMMENT ON COLUMN public.tb_qo_cliente.ingresos IS 'ingresos relacionados al cliente';
COMMENT ON COLUMN public.tb_qo_cliente.egresos IS 'Egresos relacionados al cliente';
COMMENT ON COLUMN public.tb_qo_cliente.pasivos IS 'Pasivos del cliente';
COMMENT ON COLUMN public.tb_qo_cliente.activos IS 'Activos del cliente';


-- public.tb_qo_cliente_pago definition

-- Drop table

-- DROP TABLE tb_qo_cliente_pago;

CREATE TABLE tb_qo_cliente_pago (
	id numeric NOT NULL DEFAULT nextval('seq_cliente_pago'::regclass), -- id pk
	nombre_cliente varchar(50) NULL, -- Nombre del cliente
	cedula varchar(15) NULL, -- Cedula del cliente
	codigo_operacion varchar NULL, --  codigo softbank del credito
	codigo_cuenta_mupi varchar NULL, -- cuenta mupi de la operacion
	tipo_credito varchar(50) NULL, -- tipo de credito relacionado
	valor_precancelado numeric(10,2) NULL, -- VAlor precancelado del pago
	valor_depositado numeric(10,2) NULL, -- Valor depositado en la operacion
	observacion varchar(100) NULL, -- Observacion de gestor
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	usuario_creacion varchar NULL, -- Usuario que crea la operacion
	usuario_actualizacion varchar NULL, -- Usuario que actualiza la operacion
	estado varchar(20) NOT NULL, -- ACT o INA
	tipo varchar(15) NULL, -- Tipo de pago 
	codigo varchar(20) NULL, -- codigo de operacion Bpm
	id_agencia numeric(10) NULL, -- agencia donde se realizo la operacion
	asesor varchar(20) NULL, -- Asesor responsable de la operacion
	aprobador varchar(20) NULL, -- Aprobador responsable de la operacion
	CONSTRAINT tb_qo_cliente_pago_un UNIQUE (codigo),
	CONSTRAINT tb_qo_pago_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_cliente_pago.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_cliente_pago.nombre_cliente IS 'Nombre del cliente';
COMMENT ON COLUMN public.tb_qo_cliente_pago.cedula IS 'Cedula del cliente';
COMMENT ON COLUMN public.tb_qo_cliente_pago.codigo_operacion IS ' codigo softbank del credito';
COMMENT ON COLUMN public.tb_qo_cliente_pago.codigo_cuenta_mupi IS 'cuenta mupi de la operacion';
COMMENT ON COLUMN public.tb_qo_cliente_pago.tipo_credito IS 'tipo de credito relacionado';
COMMENT ON COLUMN public.tb_qo_cliente_pago.valor_precancelado IS 'VAlor precancelado del pago';
COMMENT ON COLUMN public.tb_qo_cliente_pago.valor_depositado IS 'Valor depositado en la operacion';
COMMENT ON COLUMN public.tb_qo_cliente_pago.observacion IS 'Observacion de gestor';
COMMENT ON COLUMN public.tb_qo_cliente_pago.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_cliente_pago.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_cliente_pago.usuario_creacion IS 'Usuario que crea la operacion';
COMMENT ON COLUMN public.tb_qo_cliente_pago.usuario_actualizacion IS 'Usuario que actualiza la operacion';
COMMENT ON COLUMN public.tb_qo_cliente_pago.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_cliente_pago.tipo IS 'Tipo de pago ';
COMMENT ON COLUMN public.tb_qo_cliente_pago.codigo IS 'codigo de operacion Bpm';
COMMENT ON COLUMN public.tb_qo_cliente_pago.id_agencia IS 'agencia donde se realizo la operacion';
COMMENT ON COLUMN public.tb_qo_cliente_pago.asesor IS 'Asesor responsable de la operacion';
COMMENT ON COLUMN public.tb_qo_cliente_pago.aprobador IS 'Aprobador responsable de la operacion';


-- public.tb_qo_devolucion definition

-- Drop table

-- DROP TABLE tb_qo_devolucion;

CREATE TABLE tb_qo_devolucion (
	id numeric(10) NOT NULL, -- id pk
	estado varchar(20) NOT NULL, -- ACT o INA
	fecha_creacion date NOT NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	codigo varchar(20) NOT NULL, -- Cdogio Bpm de la operacion
	asesor varchar(20) NOT NULL, -- Asesor responsable
	aprobador varchar(20) NULL, -- Aprobador responsable
	id_agencia numeric(20) NOT NULL, -- ID de la agencia donde se realizo la operacion
	nombre_cliente varchar(400) NOT NULL, -- Nombre completo del cliente
	cedula_cliente varchar(20) NOT NULL, -- Cedula del cliente
	codigo_operacion varchar(30) NULL, -- Codigo softbank del credito relacionado
	nivel_educacion varchar(30) NULL, -- Nivel de educacion del cliente
	estado_civil varchar(30) NULL, -- Estado civil del cliente
	separacion_bienes varchar(10) NULL, -- S or No
	fecha_nacimiento date NULL, -- Fecha de nacimiento del cliente
	nacionalidad varchar(50) NULL, -- Id Nacionalidad del cliente
	lugar_nacimiento varchar(50) NULL, -- Id del lugar de nacimiento
	tipo_cliente varchar(40) NULL, -- Codigo del tipo de cliente
	observaciones varchar(250) NULL, -- Observacion realizada por el aprobador
	agencia_entrega varchar(50) NULL, -- Agencia de entrega de las garantias
	valor_custodia_aprox numeric NULL,
	code_herederos varchar(1000) NULL,
	code_detalle_credito varchar(1000) NULL,
	code_detalle_garantia varchar(1000) NULL,
	genero varchar(30) NULL, -- Codigo el genero del cliente
	fecha_arribo date NULL, -- Fecha de arribo de la garantia
	nombre_agencia_solicitud varchar(30) NULL, -- nombre de la agencia de solicitud
	id_agencia_entrega numeric NULL, -- Id de la agencia de entrega
	fecha_aprobacion_solicitud date NULL, -- Fecha de aprobacion de la solicitud
	codigo_operacion_madre varchar(50) NULL, -- Codigo de operacion madre softbank relacionada al credito
	funda_actual varchar(50) NULL, -- Fecha actual de la operacion
	funda_madre varchar(50) NULL, -- Funda madre relacionada al credito
	arribo bool NULL,
	ciudad_tevcol varchar(50) NULL,
	valor_avaluo numeric NULL,
	peso_bruto numeric NULL,
	devuelto bool NULL,
	observacion_aprobador varchar(1000) NULL, -- Observacion de aprobador responsable
	CONSTRAINT tb_qo_devolucion_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_devolucion_un UNIQUE (codigo)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_devolucion.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_devolucion.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_devolucion.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_devolucion.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_devolucion.codigo IS 'Cdogio Bpm de la operacion';
COMMENT ON COLUMN public.tb_qo_devolucion.asesor IS 'Asesor responsable';
COMMENT ON COLUMN public.tb_qo_devolucion.aprobador IS 'Aprobador responsable';
COMMENT ON COLUMN public.tb_qo_devolucion.id_agencia IS 'ID de la agencia donde se realizo la operacion';
COMMENT ON COLUMN public.tb_qo_devolucion.nombre_cliente IS 'Nombre completo del cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.cedula_cliente IS 'Cedula del cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.codigo_operacion IS 'Codigo softbank del credito relacionado';
COMMENT ON COLUMN public.tb_qo_devolucion.nivel_educacion IS 'Nivel de educacion del cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.estado_civil IS 'Estado civil del cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.separacion_bienes IS 'S or No';
COMMENT ON COLUMN public.tb_qo_devolucion.fecha_nacimiento IS 'Fecha de nacimiento del cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.nacionalidad IS 'Id Nacionalidad del cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.lugar_nacimiento IS 'Id del lugar de nacimiento';
COMMENT ON COLUMN public.tb_qo_devolucion.tipo_cliente IS 'Codigo del tipo de cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.observaciones IS 'Observacion realizada por el aprobador';
COMMENT ON COLUMN public.tb_qo_devolucion.agencia_entrega IS 'Agencia de entrega de las garantias';
COMMENT ON COLUMN public.tb_qo_devolucion.genero IS 'Codigo el genero del cliente';
COMMENT ON COLUMN public.tb_qo_devolucion.fecha_arribo IS 'Fecha de arribo de la garantia';
COMMENT ON COLUMN public.tb_qo_devolucion.nombre_agencia_solicitud IS 'nombre de la agencia de solicitud';
COMMENT ON COLUMN public.tb_qo_devolucion.id_agencia_entrega IS 'Id de la agencia de entrega';
COMMENT ON COLUMN public.tb_qo_devolucion.fecha_aprobacion_solicitud IS 'Fecha de aprobacion de la solicitud';
COMMENT ON COLUMN public.tb_qo_devolucion.codigo_operacion_madre IS 'Codigo de operacion madre softbank relacionada al credito';
COMMENT ON COLUMN public.tb_qo_devolucion.funda_actual IS 'Fecha actual de la operacion';
COMMENT ON COLUMN public.tb_qo_devolucion.funda_madre IS 'Funda madre relacionada al credito';
COMMENT ON COLUMN public.tb_qo_devolucion.observacion_aprobador IS 'Observacion de aprobador responsable';


-- public.tb_qo_estado_garantia definition

-- Drop table

-- DROP TABLE tb_qo_estado_garantia;

CREATE TABLE tb_qo_estado_garantia (
	id numeric(10) NOT NULL, -- id pk
	proceso varchar(20) NOT NULL, -- Enum: "UTI", "APD", "DEV", "LIB", "PRM", "EJE"
	caja_fuerte varchar(20) NOT NULL, -- Enum: "CAJA 1", "CAJA 2", "TEVCOL", "CLIENTE", "MUTUALISTA", "DESCONOCIDA"
	ubicacion varchar(20) NOT NULL, -- Enum:  "AGE", "ENC", "CUS", "CLI", "TRM", "IFI" 
	CONSTRAINT tb_qo_estado_garantia_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.tb_qo_estado_garantia IS 'Tabla registra la situacion actual de la garantia atada a una operacion.
';

-- Column comments

COMMENT ON COLUMN public.tb_qo_estado_garantia.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_estado_garantia.proceso IS 'Enum: "UTI", "APD", "DEV", "LIB", "PRM", "EJE"';
COMMENT ON COLUMN public.tb_qo_estado_garantia.caja_fuerte IS 'Enum: "CAJA 1", "CAJA 2", "TEVCOL", "CLIENTE", "MUTUALISTA", "DESCONOCIDA"';
COMMENT ON COLUMN public.tb_qo_estado_garantia.ubicacion IS 'Enum:  "AGE", "ENC", "CUS", "CLI", "TRM", "IFI"	';


-- public.tb_qo_excepcion_rol definition

-- Drop table

-- DROP TABLE tb_qo_excepcion_rol;

CREATE TABLE tb_qo_excepcion_rol (
	rol varchar NULL, -- Rol relacionado a la excepcion
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar NULL, -- ACT o INA
	id numeric NOT NULL DEFAULT nextval('seq_excepcion_rol'::regclass), -- id pk
	excepcion varchar NULL, -- Tipo de xcepcion
	CONSTRAINT tb_qo_excepcion_rol_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_excepcion_rol.rol IS 'Rol relacionado a la excepcion';
COMMENT ON COLUMN public.tb_qo_excepcion_rol.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_excepcion_rol.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_excepcion_rol.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_excepcion_rol.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_excepcion_rol.excepcion IS 'Tipo de xcepcion';


-- public.tb_qo_proceso definition

-- Drop table

-- DROP TABLE tb_qo_proceso;

CREATE TABLE tb_qo_proceso (
	id numeric(10) NOT NULL DEFAULT nextval('seq_proceso'::regclass), -- id pk
	id_proceso numeric(10) NULL, -- Id refencia al proceso jbpm
	id_referencia numeric(10) NOT NULL, -- Id de referencia a operacion
	proceso varchar(40) NOT NULL, -- Tipo de proceso
	estado_proceso varchar(30) NOT NULL, -- Estado actual del proceso
	usuario varchar(30) NULL, -- Usuario actual del proceso
	estado varchar(30) NOT NULL, -- ACT o INA
	fecha_creacion date NOT NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	CONSTRAINT tb_qo_proceso_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_proceso.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_proceso.id_proceso IS 'Id refencia al proceso jbpm';
COMMENT ON COLUMN public.tb_qo_proceso.id_referencia IS 'Id de referencia a operacion';
COMMENT ON COLUMN public.tb_qo_proceso.proceso IS 'Tipo de proceso';
COMMENT ON COLUMN public.tb_qo_proceso.estado_proceso IS 'Estado actual del proceso';
COMMENT ON COLUMN public.tb_qo_proceso.usuario IS 'Usuario actual del proceso';
COMMENT ON COLUMN public.tb_qo_proceso.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_proceso.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_proceso.fecha_actualizacion IS 'Fecha de actializacion del registro';


-- public.tb_qo_rol_tipo_documento definition

-- Drop table

-- DROP TABLE tb_qo_rol_tipo_documento;

CREATE TABLE tb_qo_rol_tipo_documento (
	id numeric(10) NOT NULL, -- id pk
	id_tipo_documento numeric(10) NULL, -- g
	id_rol numeric(10) NULL, -- g
	estado varchar(100) NULL, -- ACT o INA
	proceso varchar(100) NULL, -- g
	estado_operacion varchar(100) NULL, -- g
	descarga_plantilla bool NULL, -- g
	carga_documento bool NULL, -- g
	descarga_documento bool NULL, -- g
	carga_documento_adicional bool NULL, -- g
	descarga_documento_adicional bool NULL, -- g
	accion_uno bool NULL, -- g
	accion_dos bool NULL, -- g
	accion_tres bool NULL, -- g
	CONSTRAINT pk_rol_tipodocumento PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.id_tipo_documento IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.id_rol IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.proceso IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.estado_operacion IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.descarga_plantilla IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.carga_documento IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.descarga_documento IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.carga_documento_adicional IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.descarga_documento_adicional IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.accion_uno IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.accion_dos IS 'g';
COMMENT ON COLUMN public.tb_qo_rol_tipo_documento.accion_tres IS 'g';


-- public.tb_qo_tipo_archivo definition

-- Drop table

-- DROP TABLE tb_qo_tipo_archivo;

CREATE TABLE tb_qo_tipo_archivo (
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

-- DROP TABLE tb_qo_tipo_documento;

CREATE TABLE tb_qo_tipo_documento (
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

-- DROP TABLE tb_qo_tracking;

CREATE TABLE tb_qo_tracking (
	id numeric NOT NULL DEFAULT nextval('seq_tracking'::regclass),
	proceso varchar(20) NULL,
	actividad varchar(50) NULL,
	seccion varchar(50) NULL,
	codigo_bpm varchar(50) NULL,
	codigo_operacion_softbank varchar(50) NULL,
	fecha_creacion date NULL,
	fecha_actualizacion date NULL,
	estado varchar(20) NULL,
	usuario_creacion varchar(20) NULL,
	usuario_actualizacion varchar(20) NULL,
	nombre_asesor varchar(20) NULL,
	tiempo_transcurrido numeric NULL,
	fecha_inicio timestamp NULL,
	fecha_fin timestamp NULL,
	observacion varchar NULL
);
CREATE INDEX tb_qo_tracking_id_idx ON public.tb_qo_tracking USING btree (id);


-- public.tb_qo_verificacion_telefonica definition

-- Drop table

-- DROP TABLE tb_qo_verificacion_telefonica;

CREATE TABLE tb_qo_verificacion_telefonica (
	id numeric(10) NOT NULL, -- id pk
	fecha_creacion date NOT NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(20) NOT NULL, -- ACT o INA
	asesor varchar(20) NOT NULL,
	aprobador varchar(20) NULL,
	codigo varchar(20) NOT NULL,
	nombre_cliente varchar(400) NOT NULL,
	cedula_cliente varchar(20) NOT NULL,
	id_agencia numeric(10) NOT NULL,
	codigo_operacion varchar(20) NULL,
	CONSTRAINT tb_qo_verificacion_telefonica_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_verificacion_telefonica_un UNIQUE (codigo)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_verificacion_telefonica.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_verificacion_telefonica.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_verificacion_telefonica.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_verificacion_telefonica.estado IS 'ACT o INA';


-- public.tb_qo_archivo_cliente definition

-- Drop table

-- DROP TABLE tb_qo_archivo_cliente;

CREATE TABLE tb_qo_archivo_cliente (
	id numeric(10) NOT NULL, -- id pk
	nombre_archivo varchar(150) NULL,
	archivo oid NULL,
	estado varchar(20) NULL, -- ACT o INA
	id_cliente numeric(10) NULL,
	id_tipo_archivo numeric(10) NULL,
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	CONSTRAINT pk_archivo_cliente PRIMARY KEY (id),
	CONSTRAINT fk_archivo_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id),
	CONSTRAINT fk_tipo_archivo FOREIGN KEY (id_tipo_archivo) REFERENCES tb_qo_tipo_archivo(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_archivo_cliente.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_archivo_cliente.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_archivo_cliente.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_archivo_cliente.fecha_actualizacion IS 'Fecha de actializacion del registro';


-- public.tb_qo_cotizador definition

-- Drop table

-- DROP TABLE tb_qo_cotizador;

CREATE TABLE tb_qo_cotizador (
	id numeric(10) NOT NULL DEFAULT nextval('seq_cotizador'::regclass), -- id pk
	grado_interes varchar(100) NULL, -- Grado de interes del prospecto
	motivo_de_desestimiento varchar(100) NULL, -- Motivo de cancelacion de la operacion
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(100) NULL, -- ACT o INA
	id_cliente numeric(10) NULL, -- pk del cliente relacionado
	codigo_cotizacion varchar(11) NULL, -- codigo Bpm de la operacion
	CONSTRAINT tb_qo_cotizador_pkey PRIMARY KEY (id),
	CONSTRAINT fk_cotizador_cliente_id_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_cotizador.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_cotizador.grado_interes IS 'Grado de interes del prospecto';
COMMENT ON COLUMN public.tb_qo_cotizador.motivo_de_desestimiento IS 'Motivo de cancelacion de la operacion';
COMMENT ON COLUMN public.tb_qo_cotizador.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_cotizador.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_cotizador.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_cotizador.id_cliente IS 'pk del cliente relacionado';
COMMENT ON COLUMN public.tb_qo_cotizador.codigo_cotizacion IS 'codigo Bpm de la operacion';


-- public.tb_qo_cuenta_bancaria_cliente definition

-- Drop table

-- DROP TABLE tb_qo_cuenta_bancaria_cliente;

CREATE TABLE tb_qo_cuenta_bancaria_cliente (
	id numeric(10) NOT NULL DEFAULT nextval('seq_cuenta'::regclass), -- id pk
	id_cliente numeric(10) NOT NULL, -- pk del cliente
	id_softbank numeric(10) NULL, -- id de relacion softbank
	banco numeric(10) NOT NULL, -- codigo del banco del cliente
	cuenta varchar(30) NOT NULL, -- Numero de cuenta del cliente
	es_ahorros bool NOT NULL, -- true or false
	estado varchar(20) NOT NULL, -- ACT o INA
	CONSTRAINT tb_qo_cuenta_bancaria_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_cuenta_bancaria_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_cuenta_bancaria_cliente.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_cuenta_bancaria_cliente.id_cliente IS 'pk del cliente';
COMMENT ON COLUMN public.tb_qo_cuenta_bancaria_cliente.id_softbank IS 'id de relacion softbank';
COMMENT ON COLUMN public.tb_qo_cuenta_bancaria_cliente.banco IS 'codigo del banco del cliente';
COMMENT ON COLUMN public.tb_qo_cuenta_bancaria_cliente.cuenta IS 'Numero de cuenta del cliente';
COMMENT ON COLUMN public.tb_qo_cuenta_bancaria_cliente.es_ahorros IS 'true or false';
COMMENT ON COLUMN public.tb_qo_cuenta_bancaria_cliente.estado IS 'ACT o INA';


-- public.tb_qo_dato_trabajo_cliente definition

-- Drop table

-- DROP TABLE tb_qo_dato_trabajo_cliente;

CREATE TABLE tb_qo_dato_trabajo_cliente (
	id numeric(10) NOT NULL, -- id pk
	id_cliente numeric(10) NOT NULL, -- pk de cliente
	id_softbank numeric(10) NULL, -- id de racion softbank
	actividad_economica numeric(10) NOT NULL, -- id Actividad economica
	actividad_economica_mupi varchar(20) NULL, -- Id actividad economica mupi
	es_relacion_dependencia bool NOT NULL, -- S or N
	origen_ingreso varchar(20) NOT NULL, -- codigo origen de ingresos del cliente
	ocupacion varchar(20) NOT NULL, -- codigo de opcupacion el cliente
	cargo varchar(20) NOT NULL, -- codigo del cargo del cliente
	esprincipal bool NOT NULL, -- true or false
	nombre_empresa varchar(50) NULL, -- Nombre de la empresa del cliente
	estado varchar(20) NULL, -- ACT o INA
	CONSTRAINT tb_qo_dato_trabajo_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_dato_trabajo_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.id_cliente IS 'pk de cliente';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.id_softbank IS 'id de racion softbank';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.actividad_economica IS 'id Actividad economica';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.actividad_economica_mupi IS 'Id actividad economica mupi';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.es_relacion_dependencia IS 'S or N';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.origen_ingreso IS 'codigo origen de ingresos del cliente';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.ocupacion IS 'codigo de opcupacion el cliente';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.cargo IS 'codigo del cargo del cliente';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.esprincipal IS 'true or false';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.nombre_empresa IS 'Nombre de la empresa del cliente';
COMMENT ON COLUMN public.tb_qo_dato_trabajo_cliente.estado IS 'ACT o INA';


-- public.tb_qo_detalle_credito definition

-- Drop table

-- DROP TABLE tb_qo_detalle_credito;

CREATE TABLE tb_qo_detalle_credito (
	id numeric(10) NOT NULL DEFAULT nextval('seq_detalle_credito'::regclass), -- id pk
	plazo numeric(10) NULL, -- Plazo simulado del credito
	monto_preaprobado numeric(15,2) NULL, -- monto proaprobado del credito
	recibir_cliente numeric(15,2) NULL, -- Valor a recibir del cliente
	costo_nueva_operacion numeric(15,2) NULL, -- Costo de la nueva operaion
	costo_custodia numeric(15,2) NULL, -- Rubro
	costo_transporte numeric(15,2) NULL, -- Rubro
	costo_credito numeric(15,2) NULL, -- Rubro
	costo_seguro numeric(15,2) NULL, -- Rubro
	costo_resguardado numeric(15,2) NULL, -- Rubro
	costo_estimado numeric(15,2) NULL, -- Rubro
	valor_cuota numeric(15,2) NULL, -- Valor de la cuota
	id_cotizador numeric(10) NULL, -- pk de la cotizacion
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(100) NULL, -- ACT o INA
	costo_valoracion numeric(15,2) NULL, -- Rubro
	costo_tasacion numeric(15,2) NULL, -- Rubro
	solca numeric(15,2) NULL, -- Rubro
	periodo_plazo varchar(20) NULL, -- Periodo del plazo
	CONSTRAINT pk_tb_qo_detalle_credito PRIMARY KEY (id),
	CONSTRAINT fk_detalle_credito_cotizador_id_cotizador FOREIGN KEY (id_cotizador) REFERENCES tb_qo_cotizador(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_detalle_credito.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_detalle_credito.plazo IS 'Plazo simulado del credito';
COMMENT ON COLUMN public.tb_qo_detalle_credito.monto_preaprobado IS 'monto proaprobado del credito';
COMMENT ON COLUMN public.tb_qo_detalle_credito.recibir_cliente IS 'Valor a recibir del cliente';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_nueva_operacion IS 'Costo de la nueva operaion';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_custodia IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_transporte IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_credito IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_seguro IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_resguardado IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_estimado IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.valor_cuota IS 'Valor de la cuota';
COMMENT ON COLUMN public.tb_qo_detalle_credito.id_cotizador IS 'pk de la cotizacion';
COMMENT ON COLUMN public.tb_qo_detalle_credito.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_valoracion IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.costo_tasacion IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.solca IS 'Rubro';
COMMENT ON COLUMN public.tb_qo_detalle_credito.periodo_plazo IS 'Periodo del plazo';


-- public.tb_qo_direccion_cliente definition

-- Drop table

-- DROP TABLE tb_qo_direccion_cliente;

CREATE TABLE tb_qo_direccion_cliente (
	id numeric(10) NOT NULL, -- id pk
	direccion_legal bool NOT NULL, -- true or false
	direccion_envio_correspondencia bool NOT NULL, -- true or false
	tipo_direccion varchar(10) NOT NULL, -- codigo de tipo operacion
	barrio varchar(50) NULL, -- nombre del barrio
	sector varchar(50) NOT NULL, -- nombre del sector
	calle_principal varchar(50) NOT NULL, -- true or false
	numeracion varchar(50) NOT NULL, -- Numeracion de la direccion
	referencia_ubicacion varchar(50) NOT NULL, -- Referencia hacia la ubicacion
	calle_segundaria varchar(50) NULL, -- nombre de la calle segundaria
	tipo_vivienda varchar(20) NOT NULL, -- codigo de tipo de vivienda
	id_cliente numeric(10) NOT NULL, -- pk de cliente
	estado varchar(10) NOT NULL, -- ACT o INA
	division_politica numeric(20) NULL, -- id de la divicion politica de la direccion
	id_softbank numeric(10) NULL, -- id de relacion a softbank
	CONSTRAINT tb_qo_direccion_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_direccion_cliente_un UNIQUE (id_softbank),
	CONSTRAINT tb_qo_direccion_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_direccion_cliente.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.direccion_legal IS 'true or false';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.direccion_envio_correspondencia IS 'true or false';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.tipo_direccion IS 'codigo de tipo operacion';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.barrio IS 'nombre del barrio';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.sector IS 'nombre del sector';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.calle_principal IS 'true or false';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.numeracion IS 'Numeracion de la direccion';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.referencia_ubicacion IS 'Referencia hacia la ubicacion';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.calle_segundaria IS 'nombre de la calle segundaria';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.tipo_vivienda IS 'codigo de tipo de vivienda';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.id_cliente IS 'pk de cliente';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.division_politica IS 'id de la divicion politica de la direccion';
COMMENT ON COLUMN public.tb_qo_direccion_cliente.id_softbank IS 'id de relacion a softbank';


-- public.tb_qo_ingreso_egreso_cliente definition

-- Drop table

-- DROP TABLE tb_qo_ingreso_egreso_cliente;

CREATE TABLE tb_qo_ingreso_egreso_cliente (
	id numeric(10) NOT NULL, -- id pk
	valor numeric(10) NULL, -- g
	id_cliente numeric(10) NOT NULL, -- g
	es_ingreso bool NULL, -- g
	es_egreso bool NULL, -- g
	estado varchar(10) NOT NULL, -- ACT o INA
	CONSTRAINT tb_qo_ingreso_egreso_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_ingreso_egreso_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_ingreso_egreso_cliente.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_ingreso_egreso_cliente.valor IS 'g';
COMMENT ON COLUMN public.tb_qo_ingreso_egreso_cliente.id_cliente IS 'g';
COMMENT ON COLUMN public.tb_qo_ingreso_egreso_cliente.es_ingreso IS 'g';
COMMENT ON COLUMN public.tb_qo_ingreso_egreso_cliente.es_egreso IS 'g';
COMMENT ON COLUMN public.tb_qo_ingreso_egreso_cliente.estado IS 'ACT o INA';


-- public.tb_qo_negociacion definition

-- Drop table

-- DROP TABLE tb_qo_negociacion;

CREATE TABLE tb_qo_negociacion (
	id numeric(10) NOT NULL DEFAULT nextval('seq_negociacion'::regclass), -- id pk
	id_cliente numeric(10) NOT NULL, -- pk de cliente
	fecha_creacion date NOT NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(100) NOT NULL, -- ACT o INA
	asesor varchar(100) NOT NULL, -- Asesor responsable
	aprobador varchar(20) NULL, -- Aprobador responsable
	CONSTRAINT tb_qo_negociacion_pkey PRIMARY KEY (id),
	CONSTRAINT fk_negociacion_cliente_id_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_negociacion.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_negociacion.id_cliente IS 'pk de cliente';
COMMENT ON COLUMN public.tb_qo_negociacion.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_negociacion.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_negociacion.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_negociacion.asesor IS 'Asesor responsable';
COMMENT ON COLUMN public.tb_qo_negociacion.aprobador IS 'Aprobador responsable';


-- public.tb_qo_patrimonio definition

-- Drop table

-- DROP TABLE tb_qo_patrimonio;

CREATE TABLE tb_qo_patrimonio (
	id numeric(10) NOT NULL, -- id pk
	activos varchar(150) NULL, -- Nomrbre del activo
	avaluo numeric(10) NULL, -- Valor del patrimonio
	pasivos varchar(20) NULL, -- Nombre del pasivo
	ifis numeric(10) NULL,
	infocorp numeric(10) NULL,
	id_cliente numeric(10) NOT NULL, -- pk del cliente
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(10) NOT NULL, -- ACT o INA
	CONSTRAINT pk_patrimonio_cliente PRIMARY KEY (id),
	CONSTRAINT fk_archivo_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_patrimonio.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_patrimonio.activos IS 'Nomrbre del activo';
COMMENT ON COLUMN public.tb_qo_patrimonio.avaluo IS 'Valor del patrimonio';
COMMENT ON COLUMN public.tb_qo_patrimonio.pasivos IS 'Nombre del pasivo';
COMMENT ON COLUMN public.tb_qo_patrimonio.id_cliente IS 'pk del cliente';
COMMENT ON COLUMN public.tb_qo_patrimonio.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_patrimonio.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_patrimonio.estado IS 'ACT o INA';


-- public.tb_qo_precio_oro definition

-- Drop table

-- DROP TABLE tb_qo_precio_oro;

CREATE TABLE tb_qo_precio_oro (
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
COMMENT ON TABLE public.tb_qo_precio_oro IS 'Eliminar tabla al tener el servicio de softbank';


-- public.tb_qo_referencia_personal definition

-- Drop table

-- DROP TABLE tb_qo_referencia_personal;

CREATE TABLE tb_qo_referencia_personal (
	id numeric(10) NOT NULL DEFAULT nextval('seq_referencia_personal'::regclass), -- id pk
	parentesco varchar(100) NULL, -- g
	direccion varchar(100) NULL, -- g
	telefono_movil varchar(100) NULL, -- g
	telefono_fijo varchar(100) NULL, -- g
	id_cliente numeric(10) NULL, -- g
	estado varchar(100) NOT NULL, -- ACT o INA
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	id_softbank numeric(10) NULL, -- g
	nombres varchar(50) NULL, -- g
	apellidos varchar(50) NULL, -- g
	CONSTRAINT pk_referencia_personal PRIMARY KEY (id),
	CONSTRAINT fk_referencia_personal_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_referencia_personal.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_referencia_personal.parentesco IS 'g';
COMMENT ON COLUMN public.tb_qo_referencia_personal.direccion IS 'g';
COMMENT ON COLUMN public.tb_qo_referencia_personal.telefono_movil IS 'g';
COMMENT ON COLUMN public.tb_qo_referencia_personal.telefono_fijo IS 'g';
COMMENT ON COLUMN public.tb_qo_referencia_personal.id_cliente IS 'g';
COMMENT ON COLUMN public.tb_qo_referencia_personal.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_referencia_personal.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_referencia_personal.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_referencia_personal.id_softbank IS 'g';
COMMENT ON COLUMN public.tb_qo_referencia_personal.nombres IS 'g';
COMMENT ON COLUMN public.tb_qo_referencia_personal.apellidos IS 'g';


-- public.tb_qo_registrar_pago definition

-- Drop table

-- DROP TABLE tb_qo_registrar_pago;

CREATE TABLE tb_qo_registrar_pago (
	id numeric NOT NULL DEFAULT nextval('seq_registrar_pago'::regclass), -- id pk
	id_pago numeric NULL, -- g
	institucion_financiera varchar(50) NULL, -- g
	cuentas varchar(50) NULL, -- g
	fecha_pago date NULL, -- g
	numero_deposito numeric NULL, -- g
	valor_pagado numeric(10,2) NULL, -- g
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	usuario_creacion varchar NULL, -- g
	usuario_actualizacion varchar NULL, -- g
	id_comprobante varchar(100) NULL, -- g
	estado varchar NULL, -- ACT o INA
	CONSTRAINT tb_qo_registra_pago_pkey PRIMARY KEY (id),
	CONSTRAINT tb_qo_registrar_pago_fk FOREIGN KEY (id_pago) REFERENCES tb_qo_cliente_pago(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_registrar_pago.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_registrar_pago.id_pago IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.institucion_financiera IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.cuentas IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.fecha_pago IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.numero_deposito IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.valor_pagado IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_registrar_pago.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_registrar_pago.usuario_creacion IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.usuario_actualizacion IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.id_comprobante IS 'g';
COMMENT ON COLUMN public.tb_qo_registrar_pago.estado IS 'ACT o INA';


-- public.tb_qo_riesgo_acumulado definition

-- Drop table

-- DROP TABLE tb_qo_riesgo_acumulado;

CREATE TABLE tb_qo_riesgo_acumulado (
	id numeric(10) NOT NULL, -- id pk
	estado varchar(10) NOT NULL, -- ACT o INA
	fecha_creacion date NOT NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	id_cliente numeric(10) NULL, -- g
	referencia varchar(200) NULL, -- g
	numero_operacion varchar(100) NULL, -- g
	codigo_cartera_quski varchar(100) NULL, -- g
	tipo_operacion varchar(100) NULL, -- g
	fecha_efectiva date NULL, -- g
	fecha_vencimiento date NULL, -- g
	interes_mora numeric(10,2) NULL, -- g
	saldo numeric(10) NULL, -- g
	valor_al_dia numeric(10) NULL, -- g
	valor_al_dia_mas_cuota_actual numeric(10) NULL, -- g
	valor_cancela_prestamo numeric(10) NULL, -- g
	valor_proyectado_cuota_actual numeric(10) NULL, -- g
	dias_mora_actual numeric(10) NULL, -- g
	numero_cuotas_totales numeric(10) NULL, -- g
	nombre_producto varchar(100) NULL, -- g
	numero_cuotas_faltantes numeric(10) NULL, -- g
	primera_cuota_vigente numeric(10) NULL, -- g
	estado_primera_cuota_vigente varchar(100) NULL, -- g
	numero_garantias_reales numeric(10) NULL, -- g
	estado_operacion varchar(100) NULL, -- g
	es_demandada bool NULL, -- g
	id_softbank numeric(10) NULL, -- g
	id_moneda numeric(10) NULL, -- g
	id_negociacion numeric(10) NULL, -- g
	CONSTRAINT tb_qo_riesgo_acumulado_fk FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.id_cliente IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.referencia IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.numero_operacion IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.codigo_cartera_quski IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.tipo_operacion IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.fecha_efectiva IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.fecha_vencimiento IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.interes_mora IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.saldo IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.valor_al_dia IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.valor_al_dia_mas_cuota_actual IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.valor_cancela_prestamo IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.valor_proyectado_cuota_actual IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.dias_mora_actual IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.numero_cuotas_totales IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.nombre_producto IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.numero_cuotas_faltantes IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.primera_cuota_vigente IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.estado_primera_cuota_vigente IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.numero_garantias_reales IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.estado_operacion IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.es_demandada IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.id_softbank IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.id_moneda IS 'g';
COMMENT ON COLUMN public.tb_qo_riesgo_acumulado.id_negociacion IS 'g';


-- public.tb_qo_telefono_cliente definition

-- Drop table

-- DROP TABLE tb_qo_telefono_cliente;

CREATE TABLE tb_qo_telefono_cliente (
	id numeric(10) NOT NULL DEFAULT nextval('seq_telefono'::regclass), -- id pk
	id_cliente numeric(10) NOT NULL, -- pk del cliente
	id_softbank numeric(10) NULL, -- ID de referencia softbank
	tipo_telefono varchar(20) NOT NULL, -- codigo tipo de telefono
	numero varchar(20) NULL, -- numero de telefono
	estado varchar(20) NOT NULL, -- ACT o INA
	CONSTRAINT tb_qo_telefono_cliente_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_telefono_cliente_fk FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_telefono_cliente.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_telefono_cliente.id_cliente IS 'pk del cliente';
COMMENT ON COLUMN public.tb_qo_telefono_cliente.id_softbank IS 'ID de referencia softbank';
COMMENT ON COLUMN public.tb_qo_telefono_cliente.tipo_telefono IS 'codigo tipo de telefono';
COMMENT ON COLUMN public.tb_qo_telefono_cliente.numero IS 'numero de telefono';
COMMENT ON COLUMN public.tb_qo_telefono_cliente.estado IS 'ACT o INA';


-- public.tb_qo_variables_crediticias definition

-- Drop table

-- DROP TABLE tb_qo_variables_crediticias;

CREATE TABLE tb_qo_variables_crediticias (
	id numeric(10) NOT NULL DEFAULT nextval('seq_variable_crediticia'::regclass), -- id pk
	nombre varchar(50) NULL, -- g
	valor varchar(100) NULL, -- g
	id_cotizador numeric(10) NULL, -- g
	id_negociacion numeric(10) NULL, -- g
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(50) NULL, -- ACT o INA
	orden varchar(10) NULL, -- g
	codigo varchar(100) NULL, -- g
	CONSTRAINT tb_qo_variables_crediticias_pkey PRIMARY KEY (id),
	CONSTRAINT cotizador FOREIGN KEY (id_cotizador) REFERENCES tb_qo_cotizador(id),
	CONSTRAINT negociacion FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_variables_crediticias.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.nombre IS 'g';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.valor IS 'g';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.id_cotizador IS 'g';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.id_negociacion IS 'g';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.orden IS 'g';
COMMENT ON COLUMN public.tb_qo_variables_crediticias.codigo IS 'g';


-- public.tb_qo_credito_negociacion definition

-- Drop table

-- DROP TABLE tb_qo_credito_negociacion;

CREATE TABLE tb_qo_credito_negociacion (
	id numeric(10) NOT NULL, -- id pk
	plazo_credito numeric(15,2) NULL, -- Plazo de credito
	monto_preaprobado numeric(15,2) NULL,
	valor_cuota numeric(15,2) NULL, --  valor de la cuota del credito
	id_negociacion numeric(10) NOT NULL, -- pk de la negociacion relacionada
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	estado varchar(100) NULL, -- ACT o INA
	fecha_vencimiento date NULL, -- Fecha de vencimiento del credito
	id_agencia numeric(10) NULL, -- agencia donde se creo el credito
	tipo varchar(100) NULL,
	codigo varchar(50) NULL, -- codigo bpm de la operacion
	monto_solicitado numeric(15,2) NULL, -- Monto solicitado por cliente
	monto_diferido numeric(15,2) NULL, -- Monto diferido de la operacion
	monto_desembolso numeric(15,2) NULL, -- Monto de desembloso
	neto_al_cliente numeric(15,2) NULL,
	a_recibir_cliente numeric(15,2) NULL, -- Valor a recibir por cliente
	a_pagar_cliente numeric(15,2) NULL, -- Valor a pagar por cliente
	riesgo_total_cliente numeric(15,2) NULL, -- riesgo total junto al riesgo de la operacion
	tipo_cartera_quski varchar(20) NULL, -- Tipo de cartera relacionada al credito
	estado_softbank varchar(20) NULL, -- Estado del credito en softbank
	fecha_efectiva date NULL, -- Fecha efectiva del credito
	total_costo_nueva_operacion numeric(10) NULL, -- Costo total de la nueva operacion
	numero_funda numeric(10) NULL, -- numero de funda de las joyas
	peso_funda varchar(40) NULL,
	descripcion_producto varchar(100) NULL, -- Descripcion del producto definido en softbank
	codigo_operacion varchar(20) NULL,
	pago_dia date NULL, -- Dia del pago de las cuotas
	codigo_tipo_funda varchar(20) NULL, -- Codigo del peso de la funda
	total_valor_avaluo numeric(10) NULL,
	total_valor_comercial numeric(10) NULL,
	total_valor_realizacion numeric(10) NULL,
	uri_imagen_sin_funda varchar(50) NULL, -- ObjectId de la imagen de las joyas
	uri_imagen_con_funda varchar(50) NULL, -- Object Id de la imagen de la funda
	tabla_amortizacion varchar(20) NULL, -- Codigo de la tabla de amortizacion
	monto_financiado numeric(10) NULL, -- Monto financiado del credito
	identificacion_codeudor varchar(20) NULL, -- Informacion del codeudor
	nombre_completo_codeudor varchar(200) NULL, -- Informacion del codeudor
	fecha_nacimiento_codeudor date NULL, -- Informacion del codeudor
	identificacion_apoderado varchar(20) NULL, -- Informacion del Apoderado
	nombre_completo_apoderado varchar(200) NULL, -- Informacion del Apoderado
	fecha_nacimiento_apoderado date NULL, -- Informacion del Apoderado
	numero_cuenta varchar(50) NULL, -- numero de cuenta mupi
	numero_operacion varchar(50) NULL, -- Numero de operacion softbank
	total_interes_vencimiento numeric(10) NULL, -- Total del interes al vencimiento
	deuda_inicial numeric(10) NULL, -- Deuda inicial del credito
	periodo_plazo varchar(10) NULL, -- Periodo del plazo del credito
	periodicidad_plazo varchar(10) NULL, -- Periodicidad del credito 
	valor_a_recibir numeric(15,2) NULL, -- Valor a recibir por cliente
	valor_a_pagar numeric(15,2) NULL, -- Valor a pagar por cliente
	costo_custodia numeric(15,2) NULL, -- Rubro del credito
	costo_fideicomiso numeric(15,2) NULL, -- Rubro del credito
	costo_seguro numeric(15,2) NULL, -- Rubro del credito
	costo_tasacion numeric(15,2) NULL, -- Rubro del credito
	costo_transporte numeric(15,2) NULL, -- Rubro del credito
	costo_valoracion numeric(15,2) NULL, -- Rubro del credito
	impuesto_solca numeric(15,2) NULL, -- Rubro del credito
	forma_pago_impuesto_solca varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_capital varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_custodia varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_fideicomiso varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_interes varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_mora varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_gasto_cobranza varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_seguro varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_tasador varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_transporte varchar(10) NULL, -- Codigo de la forma de pago del rubro
	forma_pago_valoracion varchar(10) NULL, -- Codigo de la forma de pago del rubro
	saldo_interes numeric(15,2) NULL, -- Rubro del credito
	saldo_mora numeric(15,2) NULL, -- Rubro del credito
	gasto_cobranza numeric(15,2) NULL, -- Rubro del credito
	cuota numeric(15,2) NULL, -- VAlor de la cuota
	saldo_capital_renov numeric(15,2) NULL, -- Saldo capital de la renovacion
	monto_previo_desembolso numeric(15,2) NULL, -- Monto de desembolso previo
	total_gastos_nueva_operacion numeric(15,2) NULL, -- Total de gastos de la nueva operacion
	total_costos_operacion_anterior numeric(15,2) NULL, -- Total de costos relacionada a la operacion anterior
	custodia_devengada numeric(15,2) NULL, -- Custodia devengada de la operacion
	forma_pago_custodia_devengada varchar(10) NULL, -- Codigo de la forma de pago relacionada al rubro
	tipo_oferta varchar(10) NULL, -- Tipo de oferta
	porcentaje_flujo_planeado numeric(15,2) NULL, -- Porcentaje del flujo planeado
	dividendo_flujo_planeado numeric(15,2) NULL, -- Dividendo del flujo planeado
	dividendo_prorrateo numeric(15,2) NULL, -- Dividendo del prorrogateo
	firmante_operacion varchar(40) NULL, -- Firmante mupi de la operacion
	tipo_cliente varchar(40) NULL, -- Tipo de cliente en caso de ser codeudor deudor o garante
	numero_coutas numeric(10) NULL, -- Numero de cuotas relacionadas al credito
	codigo_cash varchar(20) NULL, -- Codigo cash
	codigo_devuelto varchar(20) NULL, -- Codigo del motivo de devolucion
	descripcion_devuelto varchar(400) NULL, -- DEscripcion de un credito devulto por aprobador
	cobertura varchar(3) NULL, -- Cobertura del credito excepcionado
	numero_operacion_madre varchar(40) NULL, -- Numero de la operacion madre del credito
	CONSTRAINT pk_tb_qo_credito_negociacion PRIMARY KEY (id),
	CONSTRAINT fk_credito_negociacion_negociar_id_negociacion FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_credito_negociacion.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.plazo_credito IS 'Plazo de credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.valor_cuota IS ' valor de la cuota del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.id_negociacion IS 'pk de la negociacion relacionada';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.fecha_vencimiento IS 'Fecha de vencimiento del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.id_agencia IS 'agencia donde se creo el credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.codigo IS 'codigo bpm de la operacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.monto_solicitado IS 'Monto solicitado por cliente';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.monto_diferido IS 'Monto diferido de la operacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.monto_desembolso IS 'Monto de desembloso';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.a_recibir_cliente IS 'Valor a recibir por cliente';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.a_pagar_cliente IS 'Valor a pagar por cliente';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.riesgo_total_cliente IS 'riesgo total junto al riesgo de la operacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.tipo_cartera_quski IS 'Tipo de cartera relacionada al credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.estado_softbank IS 'Estado del credito en softbank';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.fecha_efectiva IS 'Fecha efectiva del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.total_costo_nueva_operacion IS 'Costo total de la nueva operacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.numero_funda IS 'numero de funda de las joyas';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.descripcion_producto IS 'Descripcion del producto definido en softbank';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.pago_dia IS 'Dia del pago de las cuotas';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.codigo_tipo_funda IS 'Codigo del peso de la funda';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.uri_imagen_sin_funda IS 'ObjectId de la imagen de las joyas';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.uri_imagen_con_funda IS 'Object Id de la imagen de la funda';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.tabla_amortizacion IS 'Codigo de la tabla de amortizacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.monto_financiado IS 'Monto financiado del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.identificacion_codeudor IS 'Informacion del codeudor';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.nombre_completo_codeudor IS 'Informacion del codeudor';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.fecha_nacimiento_codeudor IS 'Informacion del codeudor';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.identificacion_apoderado IS 'Informacion del Apoderado';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.nombre_completo_apoderado IS 'Informacion del Apoderado';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.fecha_nacimiento_apoderado IS 'Informacion del Apoderado';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.numero_cuenta IS 'numero de cuenta mupi';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.numero_operacion IS 'Numero de operacion softbank';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.total_interes_vencimiento IS 'Total del interes al vencimiento';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.deuda_inicial IS 'Deuda inicial del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.periodo_plazo IS 'Periodo del plazo del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.periodicidad_plazo IS 'Periodicidad del credito ';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.valor_a_recibir IS 'Valor a recibir por cliente';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.valor_a_pagar IS 'Valor a pagar por cliente';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.costo_custodia IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.costo_fideicomiso IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.costo_seguro IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.costo_tasacion IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.costo_transporte IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.costo_valoracion IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.impuesto_solca IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_impuesto_solca IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_capital IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_custodia IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_fideicomiso IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_interes IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_mora IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_gasto_cobranza IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_seguro IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_tasador IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_transporte IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_valoracion IS 'Codigo de la forma de pago del rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.saldo_interes IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.saldo_mora IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.gasto_cobranza IS 'Rubro del credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.cuota IS 'VAlor de la cuota';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.saldo_capital_renov IS 'Saldo capital de la renovacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.monto_previo_desembolso IS 'Monto de desembolso previo';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.total_gastos_nueva_operacion IS 'Total de gastos de la nueva operacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.total_costos_operacion_anterior IS 'Total de costos relacionada a la operacion anterior';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.custodia_devengada IS 'Custodia devengada de la operacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.forma_pago_custodia_devengada IS 'Codigo de la forma de pago relacionada al rubro';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.tipo_oferta IS 'Tipo de oferta';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.porcentaje_flujo_planeado IS 'Porcentaje del flujo planeado';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.dividendo_flujo_planeado IS 'Dividendo del flujo planeado';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.dividendo_prorrateo IS 'Dividendo del prorrogateo';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.firmante_operacion IS 'Firmante mupi de la operacion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.tipo_cliente IS 'Tipo de cliente en caso de ser codeudor deudor o garante';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.numero_coutas IS 'Numero de cuotas relacionadas al credito';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.codigo_cash IS 'Codigo cash';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.codigo_devuelto IS 'Codigo del motivo de devolucion';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.descripcion_devuelto IS 'DEscripcion de un credito devulto por aprobador';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.cobertura IS 'Cobertura del credito excepcionado';
COMMENT ON COLUMN public.tb_qo_credito_negociacion.numero_operacion_madre IS 'Numero de la operacion madre del credito';


-- public.tb_qo_documento_habilitante definition

-- Drop table

-- DROP TABLE tb_qo_documento_habilitante;

CREATE TABLE tb_qo_documento_habilitante (
	id numeric(10) NOT NULL DEFAULT nextval('seq_habilitante'::regclass), -- id pk
	nombre_archivo varchar(150) NULL, -- Nombre explicativo del archivo
	archivo oid NULL, -- Archivo habilitante
	estado varchar(20) NULL, -- ACT o INA
	id_tipo_documento numeric(10) NULL, -- Tipo de documento
	id_cotizacion numeric(10) NULL, -- ID de la cotizacion relacionada
	id_negociacion numeric(10) NULL, -- ID de la negociacion relacionada
	id_cliente numeric(10) NULL, -- i del cliente relacionado
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	proceso varchar(100) NULL, -- proceso vinculado al archivo
	id_referencia numeric NULL, -- id de referencia
	object_id varchar(300) NULL, -- ObjectId del archivo
	estado_operacion varchar(100) NULL, -- Estado actual de la operacion
	tipo_ducumento varchar(200) NULL, -- Tipo  de documento del archivo
	CONSTRAINT pk_documento_habilitantes PRIMARY KEY (id),
	CONSTRAINT un_doc_cot_id UNIQUE (id_tipo_documento, id_cotizacion),
	CONSTRAINT un_doc_neg_id UNIQUE (id_tipo_documento, id_negociacion),
	CONSTRAINT fk_con_cotizador_id FOREIGN KEY (id_cotizacion) REFERENCES tb_qo_cotizador(id),
	CONSTRAINT fk_con_tipo_habilitantes_id FOREIGN KEY (id_tipo_documento) REFERENCES tb_qo_tipo_documento(id),
	CONSTRAINT fk_doc_hab_cliente FOREIGN KEY (id_cliente) REFERENCES tb_qo_cliente(id),
	CONSTRAINT fk_doc_hab_negociacion FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_documento_habilitante.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.nombre_archivo IS 'Nombre explicativo del archivo';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.archivo IS 'Archivo habilitante';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.id_tipo_documento IS 'Tipo de documento';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.id_cotizacion IS 'ID de la cotizacion relacionada';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.id_negociacion IS 'ID de la negociacion relacionada';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.id_cliente IS 'i del cliente relacionado';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.proceso IS 'proceso vinculado al archivo';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.id_referencia IS 'id de referencia';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.object_id IS 'ObjectId del archivo';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.estado_operacion IS 'Estado actual de la operacion';
COMMENT ON COLUMN public.tb_qo_documento_habilitante.tipo_ducumento IS 'Tipo  de documento del archivo';


-- public.tb_qo_excepcion definition

-- Drop table

-- DROP TABLE tb_qo_excepcion;

CREATE TABLE tb_qo_excepcion (
	id numeric(10) NOT NULL DEFAULT nextval('seq_excepcion'::regclass), -- id pk
	tipo_excepcion varchar(20) NOT NULL, -- g
	estado_excepcion varchar(20) NOT NULL, -- g
	id_asesor varchar(20) NOT NULL, -- g
	id_aprobador varchar(20) NULL, -- g
	id_negociacion numeric(10) NOT NULL, -- g
	estado varchar(10) NOT NULL, -- ACT o INA
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	fecha_creacion date NOT NULL, -- Fecha de creacion del registro
	observacion_asesor varchar(200) NULL, -- g
	observacion_aprobador varchar(200) NULL, -- g
	caracteristica varchar(160) NULL, -- g
	mensaje_bre varchar(160) NULL, -- g
	CONSTRAINT tb_qo_excepciones_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_excepciones_fk FOREIGN KEY (id_negociacion) REFERENCES tb_qo_negociacion(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_excepcion.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_excepcion.tipo_excepcion IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.estado_excepcion IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.id_asesor IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.id_aprobador IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.id_negociacion IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_excepcion.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_excepcion.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_excepcion.observacion_asesor IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.observacion_aprobador IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.caracteristica IS 'g';
COMMENT ON COLUMN public.tb_qo_excepcion.mensaje_bre IS 'g';


-- public.tb_qo_rubro definition

-- Drop table

-- DROP TABLE tb_qo_rubro;

CREATE TABLE tb_qo_rubro (
	id numeric(10) NOT NULL DEFAULT nextval('seq_rubro'::regclass), -- id pk
	fecha_creacion date NOT NULL, -- Fecha de creacion del registro
	estado varchar(20) NOT NULL, -- ACT o INA
	codigo varchar(20) NOT NULL, -- g
	forma_pago varchar(20) NOT NULL, -- g
	factor varchar(20) NOT NULL, -- g
	valor numeric(10) NOT NULL, -- g
	descripcion varchar(100) NULL, -- g
	id_credito numeric(10) NOT NULL, -- g
	CONSTRAINT tb_qo_rubro_pk PRIMARY KEY (id),
	CONSTRAINT tb_qo_rubro_fk FOREIGN KEY (id_credito) REFERENCES tb_qo_credito_negociacion(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_rubro.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_rubro.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_rubro.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_rubro.codigo IS 'g';
COMMENT ON COLUMN public.tb_qo_rubro.forma_pago IS 'g';
COMMENT ON COLUMN public.tb_qo_rubro.factor IS 'g';
COMMENT ON COLUMN public.tb_qo_rubro.valor IS 'g';
COMMENT ON COLUMN public.tb_qo_rubro.descripcion IS 'g';
COMMENT ON COLUMN public.tb_qo_rubro.id_credito IS 'g';


-- public.tb_qo_tasacion definition

-- Drop table

-- DROP TABLE tb_qo_tasacion;

CREATE TABLE tb_qo_tasacion (
	id numeric(10) NOT NULL DEFAULT nextval('seq_tasacion'::regclass), -- id pk
	numero_piezas int4 NULL, -- g
	tipo_joya varchar(10) NULL, -- g
	estado_joya varchar(50) NULL, -- g
	descripcion varchar(100) NULL, -- g
	descuento_peso_piedra numeric(15,2) NULL, -- g
	peso_neto numeric(15,2) NULL, -- g
	peso_bruto numeric(15,2) NULL, -- g
	valor_oro numeric(15,2) NULL, -- g
	valor_comercial numeric(15,2) NULL, -- g
	descuento_suelda numeric(15,2) NULL, -- g
	valor_avaluo numeric(15,2) NULL, -- g
	valor_realizacion numeric(15,2) NULL, -- g
	descuento_peso_piedra_retasacion numeric(15,2) NULL, -- g
	peso_neto_retasacion numeric(15,2) NULL, -- g
	peso_bruto_retasacion numeric(15,2) NULL, -- g
	valor_oro_retasacion numeric(15,2) NULL, -- g
	valor_comercial_retasacion numeric(15,2) NULL, -- g
	descuento_suelda_retasacion numeric(15,2) NULL, -- g
	valor_avaluo_retasacion numeric(15,2) NULL, -- g
	valor_realizacion_retasacion numeric(15,2) NULL, -- g
	fecha_creacion date NULL, -- Fecha de creacion del registro
	fecha_actualizacion date NULL, -- Fecha de actializacion del registro
	id_credito_negociacion numeric(10) NULL, -- g
	tipo_oro varchar(15) NULL, -- g
	estado varchar(20) NULL, -- ACT o INA
	tiene_piedras bool NULL, -- g
	detalle_piedras varchar(100) NULL, -- g
	numero_garantia numeric(10) NULL, -- g
	numero_expediente numeric(10) NULL, -- g
	tipo_garantia varchar(20) NULL, -- g
	sub_tipo_garantia varchar(20) NULL, -- g
	CONSTRAINT tb_qo_tasacion_pkey PRIMARY KEY (id),
	CONSTRAINT tq_qo_credito_negociacion_fk FOREIGN KEY (id_credito_negociacion) REFERENCES tb_qo_credito_negociacion(id)
);

-- Column comments

COMMENT ON COLUMN public.tb_qo_tasacion.id IS 'id pk';
COMMENT ON COLUMN public.tb_qo_tasacion.numero_piezas IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.tipo_joya IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.estado_joya IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.descripcion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.descuento_peso_piedra IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.peso_neto IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.peso_bruto IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_oro IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_comercial IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.descuento_suelda IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_avaluo IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_realizacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.descuento_peso_piedra_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.peso_neto_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.peso_bruto_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_oro_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_comercial_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.descuento_suelda_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_avaluo_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.valor_realizacion_retasacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN public.tb_qo_tasacion.fecha_actualizacion IS 'Fecha de actializacion del registro';
COMMENT ON COLUMN public.tb_qo_tasacion.id_credito_negociacion IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.tipo_oro IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.estado IS 'ACT o INA';
COMMENT ON COLUMN public.tb_qo_tasacion.tiene_piedras IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.detalle_piedras IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.numero_garantia IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.numero_expediente IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.tipo_garantia IS 'g';
COMMENT ON COLUMN public.tb_qo_tasacion.sub_tipo_garantia IS 'g';
