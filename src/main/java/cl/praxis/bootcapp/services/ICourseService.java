package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.User;

import java.util.List;

public interface ICourseService {

    List<User>getUserByIdUser(Long idUser);

}
