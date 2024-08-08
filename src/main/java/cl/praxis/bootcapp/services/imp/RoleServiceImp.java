package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.repositories.IRoleRepository;
import cl.praxis.bootcapp.services.IBaseServiceCRUD;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class RoleServiceImp implements IBaseServiceCRUD<Role> {
    private IRoleRepository roleRepository;

    public RoleServiceImp(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean delete(Long id) {
        roleRepository.deleteById(id);
        return false;

    }
}
