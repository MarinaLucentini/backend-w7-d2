package marinalucentini.backend_w7_d2.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
@ToString
@Getter
 @Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"password", "role", "authorities", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
public class Employee  implements UserDetails {
@Id
    @GeneratedValue
    private UUID id;
private String name;
private String surname;
private String email;
private String username;
private String Urlavatar;
private String password;
    @Enumerated(EnumType.STRING)
private Role role;

    public Employee(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;

        this.role = Role.OPERAIO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
