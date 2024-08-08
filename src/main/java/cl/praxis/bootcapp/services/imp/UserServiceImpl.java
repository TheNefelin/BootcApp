package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IBaseServiceCRUD<User>, IUserService {

    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> userExist = userRepository.findById(user.getId());
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
        return false;
    }
}