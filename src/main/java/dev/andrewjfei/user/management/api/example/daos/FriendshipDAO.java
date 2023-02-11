package dev.andrewjfei.user.management.api.example.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "[friendship]")
@Getter
@Setter
@ToString
public class FriendshipDAO {

    @Id
    @ManyToOne
    @JoinColumn(name = "[requester_id]", referencedColumnName = "id", nullable = false)
    private UserDAO requester;

    @Id
    @ManyToOne
    @JoinColumn(name = "[receiver_id]", referencedColumnName = "id", nullable = false)
    private UserDAO receiver;

    @Column(name = "[is_accepted]", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isAccepted;

    @Column(name = "[created]", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created;

    public FriendshipDAO() {

    }

    public FriendshipDAO(UserDAO requester, UserDAO receiver) {
        this.requester = requester;
        this.receiver = receiver;
        this.isAccepted = false;
        this.created = LocalDateTime.now();
    }

}
