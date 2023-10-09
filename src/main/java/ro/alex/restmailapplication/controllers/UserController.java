package ro.alex.restmailapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.alex.restmailapplication.models.User;
import ro.alex.restmailapplication.services.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getallUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmation){
        return userService.confirmEmail(confirmation);
    }



}
