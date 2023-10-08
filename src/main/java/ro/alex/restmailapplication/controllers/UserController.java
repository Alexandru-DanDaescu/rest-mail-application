package ro.alex.restmailapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.alex.restmailapplication.models.User;
import ro.alex.restmailapplication.services.UserService;

@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmation){
        return userService.confirmEmail(confirmation);
    }



}
