package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.LoginDTO;
import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.entities.UserEntity;
import cl.praxis.bootcapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado por email: " + email));

        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.createAuthorityList(userEntity.getRole().getName());

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                grantedAuthorityList
        );
    }

    public UserEntity register(UserEntity userEntity) {
        Role role = new Role();
        role.setId(2L);

        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return userRepository.save(userEntity);
    }

    public LoginDTO authenticate(@ModelAttribute LoginDTO loginDTO, String email) {
        if(loginDTO.getUsername().equals(email))
            return loginDTO;
        else
            return null;
    }

}
