package cl.praxis.bootcapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notas")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nota", nullable = false)
    private int grade;

    @Column(name = "fecha", nullable = false)
    private LocalDate date;

    @Column(name = "id_profesor", nullable = false)
    private Long idTeacher;

    @Column(name = "id_estudiante", nullable = false)
    private Long idStudent;

    @Column(name = "id_asignatura", nullable = false)
    private Long idSubject;
}