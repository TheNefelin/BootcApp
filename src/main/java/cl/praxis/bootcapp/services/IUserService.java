package cl.praxis.bootcapp.services;

import cl.praxis.bootcapp.entities.LoginDTO;
import cl.praxis.bootcapp.entities.UserEntity;

public interface IUserService {
    public boolean getUserLoginEmail(UserEntity userEntity, LoginDTO loginDTO, String email);
}
