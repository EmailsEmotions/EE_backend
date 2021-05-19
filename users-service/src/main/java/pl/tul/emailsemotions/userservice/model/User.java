package pl.tul.emailsemotions.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotEmpty
    private String userName;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "password")
    @NotEmpty
    private String password;

//    @Column(name = "accountExpireDate")
//    @NotEmpty
//    private Date accountExpireDate;


}
