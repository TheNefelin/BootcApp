package cl.praxis.bootcapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private int note;
    private User teacher;
    private User estudent;
    private Subject subject;
}
