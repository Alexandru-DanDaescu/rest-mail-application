package ro.alex.restmailapplication.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String userEmail;

    private String userPassword;

    private boolean isEnabled;

    public User() {

    }

    public User(Long userId, String userName, String userEmail, String userPassword, boolean isEnabled) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isEnabled = isEnabled;
    }

}
