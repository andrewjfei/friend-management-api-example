package dev.andrewjfei.user.management.api.example.daos;

import dev.andrewjfei.user.management.api.example.models.FriendshipId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "[friendship]")
@Where(clause = "is_accepted")
@IdClass(FriendshipId.class)
@Getter
@Setter
@ToString
public class FriendshipDao {

    @Id
    @ManyToOne
    @JoinColumn(name = "[requester_id]", referencedColumnName = "id", nullable = false)
    private UserDao requester;

    @Id
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
