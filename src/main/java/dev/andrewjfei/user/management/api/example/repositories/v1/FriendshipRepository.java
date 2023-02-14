package dev.andrewjfei.user.management.api.example.repositories.v1;

import dev.andrewjfei.user.management.api.example.daos.FriendshipDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends CrudRepository<FriendshipDao, UUID> {

    Optional<FriendshipDao> findByRequesterIdAndReceiverId(UUID requesterId, UUID receiverId);

    List<FriendshipDao> findByReceiverIdAndIsAccepted(UUID receiverId, boolean isAccepted);

}
