package dev.andrewjfei.user.management.api.example.repositories.v1;

import dev.andrewjfei.user.management.api.example.daos.FriendshipDao;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends CrudRepository<FriendshipDao, UUID> {

    FriendshipDao findByRequesterIdAndReceiverId(UUID requesterId, UUID receiverID);

}
