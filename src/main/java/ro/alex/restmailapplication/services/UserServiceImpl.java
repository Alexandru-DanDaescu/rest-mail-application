package ro.alex.restmailapplication.services;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ro.alex.restmailapplication.models.ConfirmationToken;
import ro.alex.restmailapplication.models.User;
import ro.alex.restmailapplication.repository.ConfirmationTokenRepository;
import ro.alex.restmailapplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    ConfirmationTokenRepository confirmationTokenRepository;

    EmailService emailService;


    @Override
    public ResponseEntity<Object> saveUser(User user) {
        if (userRepository.userEmailExists(user.getUserEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8085/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<String> confirmEmail(String confirmation) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmation);

        if(token != null)
        {
            User user = userRepository.findUserByEmail(token.getUser().getUserEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

}
