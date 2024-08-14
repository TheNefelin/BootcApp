package cl.praxis.bootcapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private int grade;
    private User teacher;
    private User student;
    private Subject subject;
}
