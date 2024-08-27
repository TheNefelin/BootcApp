package cl.praxis.bootcapp.security;

import cl.praxis.bootcapp.entities.UserEntitiy;
import cl.praxis.bootcapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntitiy userEntitiy = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.createAuthorityList("ROLE_".concat(userEntitiy.getRole().getName()));

        return new User(
                userEntitiy.getEmail(),
                userEntitiy.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorityList);
    }
}
