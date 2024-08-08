package cl.praxis.bootcapp.repositories;

import cl.praxis.bootcapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static org.hibernate.sql.ast.Clause.WHERE;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
