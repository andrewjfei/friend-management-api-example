package dev.andrewjfei.user.management.api.example.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "[friendship]")
@Where(clause = "is_accepted")
@Getter
@Setter
@ToString
public class FriendshipDao {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "[id]", columnDefinition = "UUID", unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "[requester_id]", referencedColumnName = "id", nullable = false)
    private UserDao requester;

    @ManyToOne
    @JoinColumn(name = "[receiver_id]", referencedColumnName = "id", nullable = false)
    private UserDao receiver;

    @Column(name = "[is_accepted]", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isAccepted;

    @Column(name = "[created]", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created;

    public FriendshipDao() {

    }

    public FriendshipDao(UserDao requester, UserDao receiver) {
        this.requester = requester;
        this.receiver = receiver;
        this.isAccepted = false;
        this.created = LocalDateTime.now();
    }

}
