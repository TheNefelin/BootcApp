package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.UserEntity;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.IUserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IBaseServiceCRUD<UserEntity>, IUserService {

    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        Optional<UserEntity> userExist = userRepository.findById(userEntity.getId());
        if(userExist.isPresent()){
            userEntity.setCourses(userExist.get().getCourses());
            return userRepository.save(userEntity);
        }else{
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}