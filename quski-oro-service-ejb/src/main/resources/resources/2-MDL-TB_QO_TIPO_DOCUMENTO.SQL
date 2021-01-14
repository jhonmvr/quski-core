INSERT INTO tb_qo_tipo_documento (id,tipo_documento,fecha_creacion,descripcion,estado,plantilla,tipo_plantilla,plantilla_uno,plantilla_dos,plantilla_tres,proceso,estado_operacion,servicio) VALUES 
(10,'COMPROBANTE_PAGO',NULL,'Comprobante de un credito vigente','ACT','ComprobantePago.jasper','AB',NULL,NULL,NULL,'NOVACION','DISPONIBLE','ejemploReporteRestController/generateReporte')
,(1,'AUTORIZACION_BURO',NULL,'Carta solicitud Autorizacion Buro','ACT','AutorizacionBuro.jasper','AB',NULL,NULL,NULL,'CLIENTE',NULL,'ejemploReporteRestController/generateReporte')
,(2,'CEDULA_CLIENTE',NULL,'Cedula cliente','INA','CedulaCliente.jasper','AB',NULL,NULL,NULL,'CLIENTE',NULL,'ejemploReporteRestController/generateReporte')
,(4,'SERVICIO_BASICO_CLIENTE',NULL,'Servicio basico del cliente','ACT','ServicioBasicoCliente.jasper','AB',NULL,NULL,NULL,'CLIENTE','DISPONIBLE','ejemploReporteRestController/generateReporte')
,(3,'CEDULA_PAPELETA_CLIENTE',NULL,'Cedula y papeleta del cliente','ACT','CedulaPapeletaCliente.jasper','AB',NULL,NULL,NULL,'CLIENTE','DISPONIBLE','ejemploReporteRestController/generateReporte')
,(5,'DOCUMENTOS LEGALES CREDITO',NULL,'Compilado de habilitantes','ACT','compiladoCredito.jasper','AB',NULL,NULL,NULL,'CREDITONUEVO',NULL,'ejemploReporteRestController/generateReporte')
,(7,'FOTO FUNDA',NULL,'Foto de la funda','ACT','fotosFunda.jasper','AB',NULL,NULL,NULL,'FUNDA',NULL,'ejemploReporteRestController/generateReporte')
,(6,'FOTO JOYAS',NULL,'Fotos de las joyas','ACT','fotosJoyas.jasper','AB',NULL,NULL,NULL,'FUNDA',NULL,'ejemploReporteRestController/generateReporte')
,(9,'DOCUMENTO ENTREGA RECEPCION',NULL,'compilado devolucion','ACT','fotosJoyas.jasper','AB',NULL,NULL,NULL,'DEVOLUCION','ARRIBO','ejemploReporteRestController/generateReporte')
,(8,'DOCUMENTO LEGALES DEVOLUCION',NULL,'compilado devolucion','ACT','fotosJoyas.jasper','AB',NULL,NULL,NULL,'DEVOLUCION','SOLICITUD','ejemploReporteRestController/generateReporte')
;