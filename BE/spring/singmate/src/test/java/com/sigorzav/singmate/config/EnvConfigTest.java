package com.sigorzav.singmate.config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfigTest {

    @Test
    public void testEnvConfig() {
        EnvConfig envConfig = new EnvConfig();
        Dotenv dotenv = envConfig.dotenv();

        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");
        String dbUrl = dotenv.get("DB_URL");

        Assertions.assertNotNull(dbUsername, "DB_USERNAME should not be null");
        Assertions.assertFalse(dbUsername.isEmpty(), "DB_USERNAME should not be empty");    

        Assertions.assertNotNull(dbPassword, "DB_PASSWORD should not be null");
        Assertions.assertFalse(dbPassword.isEmpty(), "DB_PASSWORD should not be empty");

        Assertions.assertNotNull(dbUrl, "DB_URL should not be null");
        Assertions.assertFalse(dbUrl.isEmpty(), "DB_URL should not be empty");
    }
}
