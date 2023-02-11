package dev.andrewjfei.user.management.api.example.controllers;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseComponentTest {

    private static final String POSTGRESQL_DOCKER_IMAGE = "postgres:14-alpine";

    private static final String POSTGRESQL_USERNAME = "test";

    private static final String POSTGRESQL_PASSWORD = "password";

    private static final String POSTGRESQL_DATABASE_NAME = "user-management-api-example";

    @Container
    public static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse(POSTGRESQL_DOCKER_IMAGE))
                    .withUsername(POSTGRESQL_USERNAME)
                    .withPassword(POSTGRESQL_PASSWORD)
                    .withDatabaseName(POSTGRESQL_DATABASE_NAME);

    @DynamicPropertySource
    public static void applicationProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    }

}
