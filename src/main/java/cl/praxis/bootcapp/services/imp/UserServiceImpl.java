package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.UserEntitiy;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.IUserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IBaseServiceCRUD<UserEntitiy>, IUserService {

    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntitiy> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntitiy getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntitiy create(UserEntitiy user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntitiy update(UserEntitiy user) {
        Optional<UserEntitiy> userExist = userRepository.findById(user.getId());
        if(userExist.isPresent()){
            user.setCourses(userExist.get().getCourses());
            return userRepository.save(user);
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