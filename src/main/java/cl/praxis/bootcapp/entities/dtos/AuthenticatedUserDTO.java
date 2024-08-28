package cl.praxis.bootcapp.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUserDTO {
    private Long id;
    private String email;
    private List<String> roles;
}
