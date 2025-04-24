package nl.rocva.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthenticationServiceTest {

    @Test
    public void testPasswordEncryption() {
        AuthenticationService authenticationService = new AuthenticationService();
                String plainPassword = "securePassword123";
                String hashedPassword = authenticationService.hashPassword(plainPassword);

                // Assert that the hashed password is not null or empty
                assertNotNull(hashedPassword);
                assertFalse(hashedPassword.isEmpty());

                // Assert that the hashed password is different from the plain password
                assertNotEquals(plainPassword, hashedPassword);

                // Assert that the hashed password matches the plain password when verified
                assertTrue(authenticationService.verifyPassword(plainPassword, hashedPassword));
    }
}
