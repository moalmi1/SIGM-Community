CREATE OR REPLACE FUNCTION
ADD_MONTHS(TIMESTAMP, NUMERIC)
RETURNS  TIMESTAMP AS '
DECLARE
	feFecha ALIAS FOR $1;
	inMeses ALIAS FOR $2;
   	feFechFin  	DATE;
   	sbIntervalo	INTERVAL;
   	interval__units VARCHAR;
BEGIN
	interval__units := ''month'';
	sbIntervalo := ('''''''' || inMeses || '' '' ||
interval__units || '''''''')::INTERVAL;
 	SELECT feFecha + sbIntervalo  into feFechFin;
	RETURN feFechFin;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ISNUMERIC(character varying)
  RETURNS INTEGER AS
$BODY$
DECLARE
	vNum ALIAS FOR $1;
	vReturnValue INTEGER;
BEGIN
	vReturnValue:= vNum;
	RETURN 1;
EXCEPTION
	WHEN others THEN
	         RETURN 0;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION GETNUMERICPOSITIVE(character varying)
  RETURNS BIGINT AS
$BODY$
DECLARE
	vNum ALIAS FOR $1;
	vReturnValue BIGINT;
BEGIN
	vReturnValue:= vNum;
	RETURN vReturnValue;
EXCEPTION
	WHEN others THEN
	         RETURN -1;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION DEVOLVERMARCA(numeric, numeric)
  RETURNS numeric AS
$BODY$
DECLARE
	numeroEnBase10 ALIAS FOR $1;
	posicionBit ALIAS FOR $2;
	vNumeros VARCHAR(10);
        nuevoNumero VARCHAR;
        auxNumeroEnBase10 NUMERIC;
BEGIN
     vNumeros:= '0123456789';
     nuevoNumero:='';
     auxNumeroEnBase10:=numeroEnBase10;   
     IF posicionBit<0 THEN
        RETURN 0;
     END IF;

     WHILE auxNumeroEnBase10 <> 0 LOOP
            nuevoNumero := SUBSTR(vNumeros, CAST(MOD(auxNumeroEnBase10,2) + 1 AS integer), 1) || nuevoNumero;
            auxNumeroEnBase10 := FLOOR(auxNumeroEnBase10 / 2);
     END LOOP;

     IF (posicionBit>=char_length(nuevoNumero)) THEN
        RETURN 0;
     END IF;
     
     IF SUBSTR(nuevoNumero, CAST(LENGTH(nuevoNumero)-posicionBit AS integer),1) ='1' THEN
        RETURN 1;
     END IF;
     RETURN 0;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION  CALCULARFINALCODREFPADRE(VARCHAR(255),VARCHAR(255),VARCHAR(255),VARCHAR(255),VARCHAR(255))
  RETURNS VARCHAR(255) AS
$BODY$
DECLARE
	vCodReferencia ALIAS FOR $1;
	vCodRefFondo ALIAS FOR $2;
	vCodigo	ALIAS FOR $3;
	vFinalCodRefPadreActual ALIAS FOR $4;
	vDelimitador ALIAS FOR $5;

	vCodRefSinCodRefFondo	VARCHAR(255);
	iNumCars integer;
	vReturnValue VARCHAR(255);
BEGIN
	-- algoritmo: Ej
	-- vCodReferencia			ES/NA/AJRP/CF1/CF2/F1/CS1/CS2/S1/U1
	-- vCodRefFondo				ES/NA/AJRP/CF1/CF2/F1
	-- vCodigo					U1
	-- vFinalCodRefPadreActual	CF1/CF2/F2/CS1/CS2/S1/U1
	-- vDelimitador				/

	-- valor retornado:
	--	sin lanzar excepciones:		CS1/CS2/S1
	--	si algo fala:				CF1/CF2/F2/CS1/CS2/S1/U1

	--reemplazar en la cadena la parte del fondo+delimitador por la cadena vacia
	

	-- El codigo de referencia del fondo nunca puede llegar nulo, si llega nulo => error
	IF(LENGTH(vCodRefFondo)=0) THEN 
		RETURN vFinalCodRefPadreActual;
	END IF;

	-- El codigo de referencia nunca puede llegar nulo, si llega nulo => error
	IF(LENGTH(vCodReferencia)=0) THEN 
		RETURN vFinalCodRefPadreActual;
	END IF;
	
	-- Si el codigo de referencia del fondo no esta contenido ,en el codigo de referencia => error
	IF(STRPOS(vCodReferencia,vCodRefFondo)=0) THEN 
		RETURN vFinalCodRefPadreActual;
	END IF;

	-- Quitamos la parte con el codigo de referencia del fondo, del codigo de referencia
	vCodRefSinCodRefFondo := REPLACE(vCodReferencia, vCodRefFondo || vDelimitador,'');

	-- codigo vacio
	IF(LENGTH(vCodigo)=0) THEN
		RETURN vCodRefSinCodRefFondo;
	END IF;
	
	-- quitamos el codigo al final de la cadena y el caracter delimitador
	iNumCars:=LENGTH(vCodRefSinCodRefFondo)-LENGTH(vCodigo);

	IF (iNumCars>0) THEN
		vReturnValue:=SUBSTRING(vCodRefSinCodRefFondo,1,iNumCars-1);
	END IF;

	RETURN vReturnValue;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
  
CREATE OR REPLACE FUNCTION GETCODREF(VARCHAR(32), VARCHAR(1))
  RETURNS VARCHAR(1024) AS
$BODY$
DECLARE
	IDELEMENTO ALIAS FOR $1;
	SEPARATOR ALIAS FOR $2;
	AUXCODREFERENCIA VARCHAR(1024);
	CODIGO VARCHAR(128);
	CODPAIS VARCHAR(16);
	CODCOMUNIDAD VARCHAR(16);
	CODARCHIVO VARCHAR(32);

	ELEMENTOS REFCURSOR;
	FONDO REFCURSOR;
	
BEGIN

	IF (IDELEMENTO IS NULL) THEN
	 	AUXCODREFERENCIA := NULL;
	ELSE
		AUXCODREFERENCIA := NULL;
		CODPAIS := NULL;
	
		OPEN ELEMENTOS FOR EXECUTE
			'SELECT ASGFELEMENTOCF.CODIGO FROM CONNECTBY(''ASGFELEMENTOCF'', ''IDPADRE'', ''ID'', ''' || IDELEMENTO || ''', 0)
			AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
			WHERE T.KEYID = ASGFELEMENTOCF.ID
			ORDER BY LEVEL';	

		FETCH ELEMENTOS INTO CODIGO;
		
		WHILE FOUND LOOP
			IF (CODIGO IS NOT NULL) THEN
				IF (LENGTH(CODIGO)>0) THEN
					IF (AUXCODREFERENCIA IS NULL) THEN
						AUXCODREFERENCIA := CODIGO;
					ELSE
						AUXCODREFERENCIA := CODIGO || SEPARATOR || AUXCODREFERENCIA;
					END IF;
				END IF;
			END IF;
			FETCH ELEMENTOS INTO CODIGO;	
		END LOOP;
		CLOSE ELEMENTOS;
		
		IF (LENGTH(AUXCODREFERENCIA)>0) THEN

			OPEN FONDO FOR EXECUTE
				'SELECT CODPAIS, CODCOMUNIDAD, CODARCHIVO 
				FROM ASGFELEMENTOCF ASGFELEMENTOCF, ASGFFONDO ASGFFONDO
				WHERE	ASGFELEMENTOCF.ID=''' || IDELEMENTO || '''AND
						ASGFELEMENTOCF.IDFONDO=ASGFFONDO.IDELEMENTOCF';
						
			FETCH FONDO INTO CODPAIS, CODCOMUNIDAD, CODARCHIVO;
			IF (LENGTH(CODPAIS)>0) THEN
				AUXCODREFERENCIA := CODPAIS || SEPARATOR || CODCOMUNIDAD || SEPARATOR || CODARCHIVO || SEPARATOR|| AUXCODREFERENCIA;
			END IF;
			
			CLOSE FONDO;

		END IF;
	END IF;

	IF (LENGTH(AUXCODREFERENCIA)=0) THEN
		AUXCODREFERENCIA := NULL;
	END IF;
	
	RETURN AUXCODREFERENCIA;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION GETFINCODREFPADRE (VARCHAR(32),VARCHAR(1)) 
RETURNS VARCHAR(1024) AS
$BODY$
DECLARE

	IDELEMENTO ALIAS FOR $1;
	SEPARATOR ALIAS FOR $2;
   	ELEMENTOCF REFCURSOR;
	CODSREFERENCIAELEMENTO REFCURSOR;
	AUXFINALCODREFPADRE VARCHAR(1024);
	CODIGOREFERENCIAELEMENTOPADRE VARCHAR(1024);
	CODIGOREFERENCIAFONDO VARCHAR(1024);	
	IDFONDO VARCHAR(32);
	TIPO SMALLINT;
	
BEGIN

	IF (IDELEMENTO IS NULL) THEN 
		AUXFINALCODREFPADRE := NULL;
	ELSE
		TIPO:=-1;

		OPEN ELEMENTOCF FOR EXECUTE
			'SELECT TIPO
			FROM ASGFELEMENTOCF
			WHERE ID = ''' || IDELEMENTO || '''';

		FETCH ELEMENTOCF INTO TIPO;
		CLOSE ELEMENTOCF;
		
		IF (TIPO IN (-1,2,6)) THEN 
			AUXFINALCODREFPADRE := NULL;
		ELSE
			OPEN CODSREFERENCIAELEMENTO FOR EXECUTE
				'SELECT GETCODREF(IDPADRE,''' || SEPARATOR || ''') AS CODIGOREFERENCIAELEMENTOPADRE, GETCODREF(IDFONDO,''' || SEPARATOR || ''') AS CODIGOREFERENCIAFONDO
				FROM ASGFELEMENTOCF
				WHERE ID = ''' || IDELEMENTO || '''';
			
			FETCH CODSREFERENCIAELEMENTO INTO CODIGOREFERENCIAELEMENTOPADRE, CODIGOREFERENCIAFONDO;
			CLOSE CODSREFERENCIAELEMENTO;
		
			IF (LENGTH(CODIGOREFERENCIAFONDO)>0) THEN
			   AUXFINALCODREFPADRE := REPLACE(CODIGOREFERENCIAELEMENTOPADRE,CODIGOREFERENCIAFONDO || SEPARATOR,'');
			   AUXFINALCODREFPADRE := REPLACE(AUXFINALCODREFPADRE,CODIGOREFERENCIAFONDO,'');			   
			ELSE
			   AUXFINALCODREFPADRE := CODIGOREFERENCIAELEMENTOPADRE;
			END IF;
		END IF;
	END IF;

	IF (LENGTH(AUXFINALCODREFPADRE)=0) THEN
		AUXFINALCODREFPADRE := NULL;
	END IF;
	
    RETURN AUXFINALCODREFPADRE;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION UPDATECODREF ( VARCHAR(32), VARCHAR(1), VARCHAR(1) ) 
RETURNS VOID AS
$BODY$
DECLARE

	ROOT ALIAS FOR $1;
	SEPARATOR ALIAS FOR $2;	
	UPDTABLES ALIAS FOR $3;	
	CODREFFONDO_CALCULADO VARCHAR(1024);
	CODREFERENCIA_CALCULADO VARCHAR(1024);
	FINALCODREFPADRE_CALCULADO VARCHAR(1024);
	ID_BUCLE VARCHAR(32);
	IDSERIE_CALCULADO VARCHAR(32);
	IDFONDO_CALCULADO VARCHAR(32);
	CODREFERENCIAFONDO_CALCULADO VARCHAR(32);
	TIPO_ELEMENTO SMALLINT;

	ELEMENTOS_NOSERIE CURSOR IS
		SELECT ASGFELEMENTOCF.ID, ASGFELEMENTOCF.CODIGO 
			FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
			AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
			WHERE T.KEYID = ASGFELEMENTOCF.ID
			AND ASGFELEMENTOCF.TIPO NOT IN (4,6)
			ORDER BY LEVEL;

	ELEMENTOS_SERIE CURSOR IS
		SELECT ASGFELEMENTOCF.ID, ASGFELEMENTOCF.CODIGO 
			FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
			AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
			WHERE T.KEYID = ASGFELEMENTOCF.ID
			AND ASGFELEMENTOCF.TIPO = 4
			ORDER BY LEVEL;
			
	CODSREFERENCIA CURSOR (IDELEMENTO VARCHAR(32)) IS
		SELECT COALESCE(GETCODREF(IDFONDO, SEPARATOR),NULL) AS CODREFFONDO, 
		GETCODREF(IDELEMENTO, SEPARATOR) AS CODREFERENCIA, 
		GETFINCODREFPADRE(IDELEMENTO, SEPARATOR) AS FINALCODREFPADRE
		FROM ASGFELEMENTOCF WHERE ID=IDELEMENTO;

	ELEMENTO CURSOR IS
		SELECT TIPO FROM ASGFELEMENTOCF WHERE ID=ROOT;

	SERIE CURSOR IS
		SELECT ASGFELEMENTOCF.ID
			FROM CONNECTBY('ASGFELEMENTOCF', 'IDPADRE', 'ID', ROOT, 0)
			AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
			WHERE T.KEYID = ASGFELEMENTOCF.ID
			AND ASGFELEMENTOCF.TIPO=4
			ORDER BY LEVEL;

	FONDO CURSOR IS
		SELECT ASGFELEMENTOCF.ID, GETCODREF(ASGFELEMENTOCF.ID,SEPARATOR)
			FROM CONNECTBY('ASGFELEMENTOCF', 'IDPADRE', 'ID', ROOT, 0)
			AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
			WHERE T.KEYID = ASGFELEMENTOCF.ID
			AND ASGFELEMENTOCF.TIPO=2
			ORDER BY LEVEL;	
			
BEGIN  
	   IF (ROOT IS NOT NULL) THEN
	   
		OPEN ELEMENTO;
		FETCH ELEMENTO INTO TIPO_ELEMENTO;
		CLOSE ELEMENTO;

		IF (TIPO_ELEMENTO IS NOT NULL) THEN

			IF ((TIPO_ELEMENTO IN (2,3,4,5,6)) AND (UPDTABLES='S')) THEN
				OPEN FONDO;
				FETCH FONDO INTO IDFONDO_CALCULADO, CODREFERENCIAFONDO_CALCULADO;
				CLOSE FONDO;
				
				-- Actualizar el idfondo a todos sus hijos
				UPDATE ASGFELEMENTOCF SET IDFONDO=IDFONDO_CALCULADO WHERE ID IN 
				(
					SELECT ASGFELEMENTOCF.ID
					FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
					AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
					WHERE T.KEYID = ASGFELEMENTOCF.ID
					ORDER BY LEVEL
				);

				-- Actualizar el idfondo a todas sus series
				UPDATE ASGFSERIE SET IDFONDO=IDFONDO_CALCULADO WHERE IDELEMENTOCF IN
				(
					SELECT ASGFELEMENTOCF.ID
					FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
					AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
					WHERE T.KEYID = ASGFELEMENTOCF.ID
					AND ASGFELEMENTOCF.TIPO=4
					ORDER BY LEVEL
				);

				-- Actualizar el idfondo a todas las unidades documentales
				UPDATE ASGFUNIDADDOC SET IDFONDO=IDFONDO_CALCULADO WHERE IDELEMENTOCF IN
				(
					SELECT ASGFELEMENTOCF.ID
					FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
					AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
					WHERE T.KEYID = ASGFELEMENTOCF.ID
					AND ASGFELEMENTOCF.TIPO=6
					ORDER BY LEVEL
				);

				-- Actualizar la identificacion a todas las unidades documentales
				UPDATE ASGDUDOCENUI SET IDENTIFICACION=CODREFERENCIAFONDO_CALCULADO || '-' || SIGNATURAUDOC WHERE IDUNIDADDOC IN
				(
					SELECT ASGFELEMENTOCF.ID
					FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
					AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
					WHERE T.KEYID = ASGFELEMENTOCF.ID
					AND ASGFELEMENTOCF.TIPO=6
					ORDER BY LEVEL
				);	

				-- Actualizar la identificacion de las unidades de instalación
				UPDATE ASGDUINSTALACION SET IDENTIFICACION=CODREFERENCIAFONDO_CALCULADO || '.' || SIGNATURAUI WHERE ID IN
				(
					SELECT ASGDUDOCENUI.IDUINSTALACION
					FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
					AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF ASGFELEMENTOCF, ASGDUDOCENUI ASGDUDOCENUI
					WHERE T.KEYID = ASGFELEMENTOCF.ID
					AND ASGFELEMENTOCF.TIPO=6
					AND ASGFELEMENTOCF.ID=ASGDUDOCENUI.IDUNIDADDOC
					ORDER BY LEVEL
				);
								
			END IF;

			IF ((TIPO_ELEMENTO IN (6)) AND (UPDTABLES='S')) THEN
				OPEN SERIE;
				FETCH SERIE INTO IDSERIE_CALCULADO;
				CLOSE SERIE;

				UPDATE ASGFUNIDADDOC SET IDSERIE=IDSERIE_CALCULADO WHERE IDELEMENTOCF IN 
				(
					SELECT ASGFELEMENTOCF.ID
					FROM CONNECTBY('ASGFELEMENTOCF', 'ID', 'IDPADRE', ROOT, 0)
					AS T(KEYID TEXT, PARENT_KEYID TEXT, LEVEL INT), ASGFELEMENTOCF
					WHERE T.KEYID = ASGFELEMENTOCF.ID
					AND ASGFELEMENTOCF.TIPO=6
					ORDER BY LEVEL
				);
			END IF;

			IF (TIPO_ELEMENTO IN (6)) THEN
				OPEN CODSREFERENCIA(ROOT);
				FETCH CODSREFERENCIA INTO CODREFFONDO_CALCULADO, CODREFERENCIA_CALCULADO, FINALCODREFPADRE_CALCULADO;
				CLOSE CODSREFERENCIA;
				UPDATE ASGFELEMENTOCF SET CODREFFONDO=CODREFFONDO_CALCULADO, CODREFERENCIA=CODREFERENCIA_CALCULADO, FINALCODREFPADRE=FINALCODREFPADRE_CALCULADO WHERE ID=ROOT;
			ELSE
				OPEN ELEMENTOS_NOSERIE;
				FETCH ELEMENTOS_NOSERIE INTO ID_BUCLE;

				WHILE FOUND LOOP
					OPEN CODSREFERENCIA(ID_BUCLE);
					FETCH CODSREFERENCIA INTO CODREFFONDO_CALCULADO, CODREFERENCIA_CALCULADO, FINALCODREFPADRE_CALCULADO;
					CLOSE CODSREFERENCIA;
					UPDATE ASGFELEMENTOCF SET CODREFFONDO=CODREFFONDO_CALCULADO, CODREFERENCIA=CODREFERENCIA_CALCULADO, FINALCODREFPADRE=FINALCODREFPADRE_CALCULADO WHERE ID=ID_BUCLE;
					
					FETCH ELEMENTOS_NOSERIE INTO ID_BUCLE;
				END LOOP;
				
				CLOSE ELEMENTOS_NOSERIE;

				OPEN ELEMENTOS_SERIE;
				FETCH ELEMENTOS_SERIE INTO ID_BUCLE;

				WHILE FOUND LOOP
					OPEN CODSREFERENCIA(ID_BUCLE);
					FETCH CODSREFERENCIA INTO CODREFFONDO_CALCULADO, CODREFERENCIA_CALCULADO, FINALCODREFPADRE_CALCULADO;
					CLOSE CODSREFERENCIA;
					UPDATE ASGFELEMENTOCF SET CODREFFONDO=CODREFFONDO_CALCULADO, CODREFERENCIA=CODREFERENCIA_CALCULADO, FINALCODREFPADRE=FINALCODREFPADRE_CALCULADO WHERE ID=ID_BUCLE;
					UPDATE ASGFELEMENTOCF SET CODREFFONDO=CODREFFONDO_CALCULADO, CODREFERENCIA=CODREFERENCIA_CALCULADO || SEPARATOR || CODIGO , FINALCODREFPADRE=NULL WHERE IDPADRE=ID_BUCLE;
					FETCH ELEMENTOS_SERIE INTO ID_BUCLE;
				END LOOP;
				
				CLOSE ELEMENTOS_SERIE;
			END IF;
		END IF;
	END IF;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;