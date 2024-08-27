package cl.praxis.bootcapp.entities.dtos;

import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.entities.UserEntitiy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private int grade;
    private UserEntitiy teacher;
    private UserEntitiy student;
    private Subject subject;
}
