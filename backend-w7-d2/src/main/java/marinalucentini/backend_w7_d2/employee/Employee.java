package marinalucentini.backend_w7_d2.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
@ToString
@Getter
 @Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"password", "role", "authorities", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
public class Employee {
@Id
    @GeneratedValue
    private UUID id;
private String name;
private String surname;
private String email;
private String username;
private String Urlavatar;
private String password;

    public Employee(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
