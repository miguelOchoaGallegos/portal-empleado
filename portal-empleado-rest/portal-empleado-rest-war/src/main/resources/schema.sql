CREATE TABLE hr_user(
	id bigint NOT NULL,
	login varchar(50) NOT NULL,
	password_hash varchar(60) NULL,
	first_name varchar(50) NULL,
	last_name varchar(50) NULL,
	email varchar(100) NULL,
	image_url varchar(255) NULL,
	activated bit NOT NULL,
	lang_key varchar(5) NULL,
	activation_key varchar(20) NULL,
	reset_key varchar(20) NULL,
	created_by varchar(50) NOT NULL,
	created_date datetime NOT NULL,
	reset_date datetime NULL,
	last_modified_by varchar(50) NULL,
	last_modified_date datetime NULL
) ;

CREATE TABLE hr_empleado(
	id bigint NOT NULL,
	codigo_trabajador int NULL,
	codigo_antiguo int NULL,
	nombre varchar(255) NULL,
	apellido_paterno varchar(255) NULL,
	apellido_materno varchar(255) NULL,
	email varchar(255) NULL,
	id_sexo int NULL,
	foto varchar(255) NULL,
	direccion varchar(255) NULL,
	descripcion_zona varchar(255) NULL,
	referencia_direccion varchar(255) NULL,
	discapacidad int NULL,
	id_tipo_documento int NULL,
	numero_documento int NULL,
	nro_ruc bigint NULL,
	fecha_nacimiento datetime NULL,
	fecha_ingreso datetime NULL,
	fecha_reingreso datetime NULL,
	fecha_retiro datetime NULL,
	telefono_alternativo int NULL,
	telefono_fijo varchar(255) NULL,
	telefono_movil int NULL,
	codigo_sap varchar(255) NULL,
	pais_emisor varchar(255) NULL,
	id_situacion int NULL,
	id_estado_civil int NULL,
	id_centro_formacion int NULL,
	id_grado_instruccion int NULL,
	id_nacionalidad int NULL,
	id_departamento varchar(2) NULL,
	id_provincia varchar(3) NULL,
	id_distrito varchar(4) NULL,
	id_tipo_trabajador varchar(4) NULL,
	id_domiciliado int NULL,
	id_situacion_especial int NULL,
	id_calendario int NULL,
	sin_control_horario int NULL,
	pendiente_captura int NULL,
	fecha_caducidad_captura datetime NULL,
	clave_enrolamiento varchar(255) NULL,
	fecha_ingreso_afp_onp datetime NULL,
	mas_vida int NULL,
	moneda_calculo varchar(255) NULL,
	numero_direccion varchar(255) NULL,
	numero_essalud varchar(255) NULL,
	numero_interior varchar(255) NULL,
	otros_ingresos_quinta_categoria int NULL,
	sctr int NULL,
	sctr_pension int NULL,
	senati int NULL,
	sindicalizado int NULL,
	tipo_calculo varchar(255) NULL,
	afiliado_eps int NULL,
	afiliado_essalud int NULL,
	codigo_afp_onp varchar(255) NULL,
	codigo_cussp varchar(255) NULL,
	created_date datetime NOT NULL,
	created_by varchar(200) NOT NULL,
	last_modified_date datetime NULL,
	last_modified_by varchar(200) NULL,
	flag_estado int NULL,
	id_sucursal bigint NULL
);

CREATE TABLE user_empleado(
	id_user bigint NOT NULL,
	id_empleado bigint NOT NULL
);


