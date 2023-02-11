package dev.andrewjfei.user.management.api.example.repositories;

import dev.andrewjfei.user.management.api.example.daos.UserDAO;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, UUID> {



}
