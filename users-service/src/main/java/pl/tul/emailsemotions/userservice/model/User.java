package pl.tul.emailsemotions.userservice.model;

import lombok.*;
import pl.tul.emailsemotions.userservice.utils.Utilities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotNull
    private String username;

    @Pattern(regexp = Utilities.EMAIL_REGEX)
    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @NotNull
    @Column(name = "confirmed", nullable = false)
    private Boolean confirmed;

    @Column(name = "accounttype")
    @NotNull
    private AccountType accountType;

    @Column(name = "canadmin")
    @NotNull
    private boolean canAdmin;
}
