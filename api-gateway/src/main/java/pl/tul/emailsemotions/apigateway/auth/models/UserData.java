package pl.tul.emailsemotions.apigateway.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.api.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active;

    @NotNull
    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

    @Column(name = "accounttype")
    @NotNull
    private AccountType accountType;

    public User toUser() {
        return User.builder()
            .id(getId())
            .username(getUsername())
            .email(getEmail())
            .active(isActive())
            .confirmed(isConfirmed())
            .accountType(getAccountType())
            .build();
    }
}
