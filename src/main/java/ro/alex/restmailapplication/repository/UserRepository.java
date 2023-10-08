package ro.alex.restmailapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.alex.restmailapplication.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    User findUserByEmail(String emailId);

    Boolean userEmailExists(String email);

}
