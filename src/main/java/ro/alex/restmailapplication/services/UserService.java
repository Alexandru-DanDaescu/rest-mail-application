package ro.alex.restmailapplication.services;

import org.springframework.http.ResponseEntity;
import ro.alex.restmailapplication.models.User;

import java.util.List;

public interface UserService {

    ResponseEntity<Object> saveUser (User user);

    ResponseEntity<String> confirmEmail(String confirmation);

    List<User> getAllUsers();

}
