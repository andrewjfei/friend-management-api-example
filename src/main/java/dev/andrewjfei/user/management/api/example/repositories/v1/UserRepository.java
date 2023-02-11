package dev.andrewjfei.user.management.api.example.repositories.v1;

import dev.andrewjfei.user.management.api.example.daos.UserDao;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDao, UUID> {

}
