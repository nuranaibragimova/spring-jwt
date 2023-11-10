package az.nuranaibrahimova.springjwt.repo;

import az.nuranaibrahimova.springjwt.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RolesRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
