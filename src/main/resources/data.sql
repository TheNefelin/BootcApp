INSERT INTO roles
(nombre, activo)
VALUES
    ('Admin', true),
    ('Profesor', true),
    ('Estudiante', true),
    ('Apoderado', true);

INSERT INTO cursos
(nombre, activo)
VALUES
    ('Full Stack Java', true),
    ('Full Stack JavaStript', true),
    ('Full Stack .NET', true),
    ('Python', true),
    ('TypeScript', true);

INSERT INTO asignaturas
(nombre, id_curso)
VALUES
    ('Fundamentos de JavaScript', 2),
    ('JavaScript Avanzado', 2),
    ('React', 2),
    ('Fundamentos de JavaScript', 2),
    ('JavaScript Avanzado', 2),
    ('React', 2),
    ('Fundamentos de C#', 3),
    ('ASP.NET Core', 3),
    ('Entity Framework', 3),
    ('Fundamentos de Python', 4),
    ('Análisis de Datos con Python', 4),
    ('Machine Learning con Python', 4),
    ('Fundamentos de TypeScript', 5),
    ('TypeScript Avanzado', 5),
    ('Angular', 5);

INSERT INTO usuarios
(correo, clave, nombre, apellido, id_rol)
VALUES
    ('praxis@praxis.cl', '123456', 'Isaac', 'Netero', 1);