--tabla parametro
CREATE TABLE TB_MI_PARAMETRO 
   (	ID NUMERIC(10,0) NOT NULL , 
	NOMBRE character varying(50), 
	VALOR character varying(4000), 
	TIPO character varying(50), 
	ESTADO character varying(20), 
	CARACTERITICA_UNO character varying(100), 
	CARACTERISTICA_DOS character varying(100), 
	 CONSTRAINT TB_SA_PARAMETER_PK PRIMARY KEY (ID));


create sequence SEQ_PARAMETRO start with 1 increment by 1;
