package dev.andrewjfei.user.management.api.example.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "[user]")
@Getter
@Setter
@ToString
public class UserDao {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "[id]", columnDefinition = "UUID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "[username]", columnDefinition = "VARCHAR", unique = true, nullable = false)
    private String username;

    @Column(name = "[first_name]", columnDefinition = "VARCHAR", nullable = false)
    private String firstName;

    @Column(name = "[last_name]", columnDefinition = "VARCHAR", nullable = false)
    private String lastName;

    @Column(name = "[password]", columnDefinition = "VARCHAR", nullable = false)
    private String password;

    @OneToMany(mappedBy = "requester", fetch = FetchType.EAGER)
    private List<FriendshipDao> friends;

    @Column(name = "[created]", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created;

    public UserDao() {

    }

    public UserDao(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.created = LocalDateTime.now();
    }

}
