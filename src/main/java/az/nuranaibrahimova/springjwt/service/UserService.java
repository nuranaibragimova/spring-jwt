package az.nuranaibrahimova.springjwt.service;

import az.nuranaibrahimova.springjwt.entity.User;
import az.nuranaibrahimova.springjwt.repo.RolesRepository;
import az.nuranaibrahimova.springjwt.repo.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with username %s is not found", username))
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().
                        stream().
                        map(role ->
                                new SimpleGrantedAuthority
                                        (role.getName())).
                        collect(Collectors.toList())
        );
    }

    public void createNewUser(User user){
        user.setRoles(List.of(rolesRepository.findByName("ROLE_USER").get()));
        usersRepository.save(user);

    }
}
