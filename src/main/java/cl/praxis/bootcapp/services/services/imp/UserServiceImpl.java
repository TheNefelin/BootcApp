package cl.praxis.bootcapp.services.services.imp;

import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.services.IBaseServiceCRUD;
import cl.praxis.bootcapp.services.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        userRepository.deleteById(id);
        return false;
    }


}