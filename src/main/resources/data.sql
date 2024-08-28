INSERT INTO roles
    (nombre, activo)
VALUES
    ('ADMIN', true),
    ('PROFESOR', true),
    ('ESTUDIANTE', true),
    ('APODERADO', true);

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
    ('Fundamentos de Java', 1),
    ('Java Avanzado', 1),
    ('Spring Framework', 1),
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
    ('praxis@praxis.cl', '$2a$10$2EYQgqDOIOkPi9EvgZzctOq1f8S.0qT.QflTC/Ntj7ZMzEjVjRSlq', 'Isaac', 'Netero', 1),
    ('profesor1@praxis.cl', '$2a$10$2EYQgqDOIOkPi9EvgZzctOq1f8S.0qT.QflTC/Ntj7ZMzEjVjRSlq', 'Biscuit', 'Krueger', 2),
    ('profesor2@praxis.cl', '$2a$10$2EYQgqDOIOkPi9EvgZzctOq1f8S.0qT.QflTC/Ntj7ZMzEjVjRSlq', 'Kite', 'Falk', 2),
    ('estudiante1@praxis.cl', '$2a$10$2EYQgqDOIOkPi9EvgZzctOq1f8S.0qT.QflTC/Ntj7ZMzEjVjRSlq', 'Gon', 'Freecss', 3),
    ('estudiante2@praxis.cl', '$2a$10$2EYQgqDOIOkPi9EvgZzctOq1f8S.0qT.QflTC/Ntj7ZMzEjVjRSlq', 'Killua', 'Zoldyck', 3),
    ('estudiante3@praxis.cl', '$2a$10$2EYQgqDOIOkPi9EvgZzctOq1f8S.0qT.QflTC/Ntj7ZMzEjVjRSlq', 'Kurapika', 'Kurta', 3),
    ('apoderado@praxis.cl', '$2a$10$2EYQgqDOIOkPi9EvgZzctOq1f8S.0qT.QflTC/Ntj7ZMzEjVjRSlq', 'Ging', 'Freecss', 4);

INSERT INTO notas
    (nota, fecha, id_profesor, id_estudiante, id_asignatura)
VALUES
    (7, '2024-01-01 08:30:12', 2, 4, 1),
    (4, '2024-01-01 08:30:12', 2, 4, 2),
    (3, '2024-01-01 08:30:12', 2, 4, 3),
    (1, '2024-01-01 08:30:12', 3, 5, 4),
    (5, '2024-01-01 08:30:12', 3, 5, 5),
    (5, '2024-01-01 08:30:12', 3, 5, 6),
    (4, '2024-01-01 08:30:12', 2, 6, 7),
    (5, '2024-01-01 08:30:12', 2, 6, 8),
    (6, '2024-01-01 08:30:12', 2, 6, 9);

INSERT INTO usuario_curso
    (id_curso,id_usuario)
VALUES
    (1,2),
    (2,2),
    (3,3);
