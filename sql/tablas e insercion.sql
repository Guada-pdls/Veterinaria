create database veterinaria;
use veterinaria;

create table Persona(
	ci char(8) PRIMARY KEY,
	nombre varchar(20) not null,
	apellido varchar(30) not null,
	direccion varchar(50),
	fecha_nac date
);

create table Socio (
	ci char(8) PRIMARY KEY,
	departamento enum(
			'Artigas', 'Canelones', 'Cerro_Largo', 'Colonia', 'Durazno', 
			'Flores', 'Florida', 'Lavalleja', 'Maldonado', 'Montevideo', 
			'Paysandu', 'Rio_Negro', 'Rivera', 'Rocha', 'Salto', 
			'San_Jose', 'Soriano', 'Tacuarembo', 'Treinta_y_Tres'
		),
	fecha_inscripcion datetime DEFAULT current_timestamp,
	FOREIGN KEY (ci) REFERENCES Persona(ci)
);

create table Socio_Tel(
	ci char(8),
	telefono char(9),
	PRIMARY KEY(ci, telefono),
	FOREIGN KEY(ci) REFERENCES Socio (ci)
);

create table Funcionario(
	ci char(8) PRIMARY KEY,
	salario decimal(8,2),
	horas_por_semana int,
	FOREIGN KEY(ci) REFERENCES Persona (ci)
);

create table Recepcionista(
     ci char(8) PRIMARY KEY,
     FOREIGN KEY(ci) REFERENCES Funcionario(ci)
);

create table Recepcionista_Idioma(
    ci char(8),
    idioma varchar(15),
    PRIMARY KEY(ci, idioma),
    FOREIGN KEY(ci) REFERENCES Recepcionista(ci)
);

create table FuncionarioMedico(
	ci char(8) PRIMARY KEY,
	FOREIGN KEY (ci) REFERENCES Funcionario (ci)
);

create table Veterinario(
	ci char(8) PRIMARY KEY,
	FOREIGN KEY (ci) REFERENCES FuncionarioMedico (ci)
);

create table Vet_Cursos(
	ci char(8),
	cursos_especializacion varchar(20),
	PRIMARY KEY(ci, cursos_especializacion),
	FOREIGN KEY (ci) REFERENCES Veterinario (ci)
);

create table Auxiliar(
	ci char(8) PRIMARY KEY,
	FOREIGN KEY (ci) REFERENCES FuncionarioMedico (ci)
);

create table Aux_Cursos(
	ci char(8),
	cursos_especializacion varchar(20),
	PRIMARY KEY(ci, cursos_especializacion),
	FOREIGN KEY (ci) REFERENCES Auxiliar (ci)
);

create table Mascota(
	id int PRIMARY KEY auto_increment,
	nombre varchar(15),
	tipo_animal enum('perro', 'gato', 'ave', 'roedor', 'serpiente', 'pez'),
	raza varchar(50),
	especie varchar(50),
	edad int,
	enfermedad varchar(50)
);

create table Mascota_Vacuna(
	id int,
	vacuna varchar(20),
	PRIMARY KEY(id, vacuna),
	FOREIGN KEY(id) REFERENCES Mascota(id)
);

CREATE TABLE Consulta (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP, 
    descripcion VARCHAR(500),
    idM INT,
    ciFM CHAR(8),
    FOREIGN KEY (idM) REFERENCES Mascota(id), 
    FOREIGN KEY (ciFM) REFERENCES FuncionarioMedico(ci) 
);

create table tiene(
	idM int,
	ciS char(8),
	PRIMARY KEY(idM, ciS),
	FOREIGN KEY(idM) REFERENCES Mascota(id),
	FOREIGN KEY(ciS) REFERENCES Socio(ci)
);

create table registra(
	ciS char(8),
	ciR char(8),
	PRIMARY KEY(ciS, ciR),
	FOREIGN KEY(ciS) REFERENCES Socio (ci),
	FOREIGN KEY(ciR) REFERENCES Recepcionista (ci)
);

create table atiende (
	ciFM char(8),
	idM int,
	PRIMARY KEY(ciFM, idM),
	FOREIGN KEY(ciFM) REFERENCES FuncionarioMedico(ci),
	FOREIGN KEY(idM) REFERENCES Mascota (id)
);

create table genera (
	idC int,
	idM int,
	ciFM char(8),
	PRIMARY KEY(idC, idM, ciFM),
	FOREIGN KEY(idC) REFERENCES Consulta(id),
	FOREIGN KEY(idM) REFERENCES Mascota(id),
	FOREIGN KEY(ciFM) REFERENCES FuncionarioMedico(ci)
);

INSERT INTO Persona (ci, nombre, apellido, direccion, fecha_nac) VALUES
('12345678', 'Ana', 'Pérez', 'Av. Libertador 1234', '1990-05-01'),
('87654321', 'Juan', 'Rodríguez', 'Bulevar Artigas 5678', '1985-03-22'),
('11223344', 'Carlos', 'López', 'Calle 8 de Octubre 2021', '1993-08-12'),
('33445566', 'Pedro', 'García', 'Calle Agraciada 3030', '1988-11-15');

INSERT INTO Socio (ci, departamento, fecha_inscripcion) VALUES
('12345678', 'Montevideo', '2023-01-10'),
('87654321', 'Canelones', '2022-12-05'),
('11223344', 'Montevideo', '2021-07-20');

INSERT INTO Socio_Tel (ci, telefono) VALUES
('12345678', '091234567'),
('87654321', '098765432');

INSERT INTO Funcionario (ci, salario, horas_por_semana) VALUES
('11223344', 45000, 40),
('33445566', 55000, 30); 

INSERT INTO FuncionarioMedico (ci) VALUES
('11223344'); 

INSERT INTO Veterinario (ci) VALUES
('11223344'); 

INSERT INTO Mascota (id, nombre, tipo_animal, raza, especie, edad, enfermedad) VALUES
(1, 'Rex', 'perro', 'Labrador', 'Canis', 5, NULL),
(2, 'Luna', 'gato', 'Siames', 'Felis', 3, 'Gripe'),
(3, 'Nemo', 'pez', 'Goldfish', 'Carassius', 1, NULL);

INSERT INTO Consulta (id, fecha, descripcion) VALUES
(1, '2023-06-15 10:00:00', 'Revisión anual de salud'),
(2, '2023-07-20 11:00:00', 'Consulta por problemas respiratorios');

INSERT INTO genera (idC, idM, ciFM) VALUES
(1, 1, '11223344'), 
(2, 2, '11223344'); 

INSERT INTO tiene (idM, ciS) VALUES
(1, '12345678'), 
(2, '87654321');

INSERT INTO Recepcionista (ci) VALUES ('33445566'); 

INSERT INTO registra (ciS, ciR) VALUES
('12345678', '33445566');

INSERT INTO Vet_Cursos (ci, cursos_especializacion) VALUES
('11223344', 'Cirugía de pequeños animales');

INSERT INTO Mascota_Vacuna (id, vacuna) VALUES
(1, 'Rabia'), 
(2, 'Triple Felina'); 

