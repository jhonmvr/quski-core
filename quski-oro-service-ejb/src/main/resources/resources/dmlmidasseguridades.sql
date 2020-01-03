--creacion de usuario
INSERT INTO public.tb_seg_usuario
(id_usuario, contrasena, email_principal, email_secundario, telefono_principal, telefono_secundario, primer_nombre, segundo_nombre, primer_apellido, 
segundo_apellido, identificacion, estado, usr_ins, fch_ins, usr_upd, fch_upd)
VALUES('admin', '821fb2a5dc9bb786e1c00780c94b358d', 'soporte@relative-engine.com', 'info@relative-engine.com', 
'9999999999', '9999999999', 'LUIS', 'ALBERTO', 'TAMAYO', 'MUNOZ', '1792589088001', 'A', 'relative', '2019-05-21',' ', 
'0001-01-01 00:00:00'::timestamp without time zone);

---POSTERIOR A LA EJECUCION DE ESTE SCRIPT ACTUALIZAR SECUENCIAS
--roles

INSERT INTO TB_SEG_ROL (ID_ROL,NOMBRE,DESCRIPCION,ES_ADMINISTRADOR,ESTADO,USR_INS,FCH_INS,USR_UPD,FCH_UPD) VALUES 
(1,'ADMINISTRADOR','ADMINISTRADOR',0,'A','ltamyo',TIMESTAMP '2018-11-14 00:00:00.000000',NULL,NULL);

INSERT INTO TB_SEG_ROL (ID_ROL,NOMBRE,DESCRIPCION,ES_ADMINISTRADOR,ESTADO,USR_INS,FCH_INS,USR_UPD,FCH_UPD) VALUES 
(2,'EJECUTIVO','EJECUTIVO',0,'A','ltamayo',TIMESTAMP '2018-11-14 00:00:00.000000',NULL,NULL);

INSERT INTO TB_SEG_ROL (ID_ROL,NOMBRE,DESCRIPCION,ES_ADMINISTRADOR,ESTADO,USR_INS,FCH_INS,USR_UPD,FCH_UPD) VALUES 
(3,'GERENTE','GERENTE',0,'A','ltamyo',TIMESTAMP '2018-11-14 00:00:00.000000',NULL,NULL);

--aplicacion
INSERT INTO public.tb_seg_aplicacion
(id_aplicacion, nombre, descripcion, token, estado, usr_ins, fch_ins, usr_upd, fch_upd, usr_dlt, fch_dlt)
VALUES(1, 'MIDAS-ORO', 'MIDAS ORO', 'XXX', 'A', 'reuser', '2019-05-23', 'reuser', '2019-05-23', 'reuser', '2019-05-23');

--menu 
INSERT INTO TB_SEG_APLICACION_OPCION
(ID_APLICACION, ID_OPCION, NOMBRE, DESCRIPCION, TIPO, ESTADO, USR_INS, FCH_INS, USR_UPD, FCH_UPD, ID_OPCION_PADRE, URL, NIVEL, ICONO)
VALUES
(1, 1 , 'Cotizacion', 'Cotizacion', ' ', 'A', 'ltamayo', to_date('2018-11-14','YYYY-MM-DD'), null, null, null, '\', 1, 'flaticon-analytics');

--submenu
INSERT INTO TB_SEG_APLICACION_OPCION
(ID_APLICACION, ID_OPCION, NOMBRE, DESCRIPCION, TIPO, ESTADO, USR_INS, FCH_INS, USR_UPD, FCH_UPD, ID_OPCION_PADRE, URL, NIVEL, ICONO)
VALUES
(1, 2 , 'Listado', 'Listado', 'S', 'A', 'ltamayo', to_date('2018-11-14','YYYY-MM-DD'), null, null, 1, '/midas-oro/cotizacion/cargar-cotizacion', 2, 'flaticon-analytics');

INSERT INTO TB_SEG_APLICACION_OPCION
(ID_APLICACION, ID_OPCION, NOMBRE, DESCRIPCION, TIPO, ESTADO, USR_INS, FCH_INS, USR_UPD, FCH_UPD, ID_OPCION_PADRE, URL, NIVEL, ICONO)
VALUES
(1, 3 , 'Gestion Contrato', 'Gestion Contrato', 'S', 'A', 'ltamayo', to_date('2018-11-14','YYYY-MM-DD'), null, null, 1, '/midas-oro/contrato/lista-contratos', 2, 'flaticon-analytics');

INSERT INTO TB_SEG_APLICACION_OPCION
(ID_APLICACION, ID_OPCION, NOMBRE, DESCRIPCION, TIPO, ESTADO, USR_INS, FCH_INS, USR_UPD, FCH_UPD, ID_OPCION_PADRE, URL, NIVEL, ICONO)
VALUES
(1, 4 , 'Gestion Funda', 'Gestion Funda', 'S', 'A', 'ltamayo', to_date('2018-11-14','YYYY-MM-DD'), null, null, 1, '/midas-oro/administracion-funda/gestion-funda', 2, 'flaticon-analytics');



--relacion menu y submenu a rol
INSERT INTO TB_SEG_ROL_APLICACION_OPCION (ID_SECUENCIA, ID_ROL, ID_APLICACION, ID_OPCION)
VALUES(nextval('SQ_SEG_ROL_APLICACION_OPCION'), 1, 1,1 );
INSERT INTO TB_SEG_ROL_APLICACION_OPCION (ID_SECUENCIA, ID_ROL, ID_APLICACION, ID_OPCION)
VALUES(nextval('SQ_SEG_ROL_APLICACION_OPCION'), 1, 1,2 );
INSERT INTO TB_SEG_ROL_APLICACION_OPCION (ID_SECUENCIA, ID_ROL, ID_APLICACION, ID_OPCION)
VALUES(nextval('SQ_SEG_ROL_APLICACION_OPCION'), 1, 1,3 );
INSERT INTO TB_SEG_ROL_APLICACION_OPCION (ID_SECUENCIA, ID_ROL, ID_APLICACION, ID_OPCION)
VALUES(nextval('SQ_SEG_ROL_APLICACION_OPCION'), 1, 1,4 );

INSERT INTO public.tb_seg_usuario_rol (id_secuencia, id_usuario, id_rol, estado)
VALUES(nextval('SQ_SEG_USUARIO_ROL'), 'admin', 1, 'A');

commit;
