import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import tung.example.AccountService;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.Arguments;

class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Test registerAccount with CSV data")
    void testRegisterAccountFromCsv(String username, String password, String email, boolean expected) {
        boolean result = accountService.registerAccount(username, password, email);
        assertEquals(expected, result);
    }

    static Stream<Arguments> emailProvider() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of("plainaddress", false),
                Arguments.of("email@domain", false),
                Arguments.of("email@domain.com", true)
        );
    }

    @ParameterizedTest
    @MethodSource("emailProvider")
    @DisplayName("isValidEmail should handle null, invalid, and valid emails")
    void testIsValidEmail(String email, boolean expected) {
        assertEquals(expected, accountService.isValidEmail(email));
    }

    static Stream<Arguments> passwordProvider() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of("short", false),
                Arguments.of("1234567", true),
                Arguments.of("longpassword", true)
        );
    }

    @ParameterizedTest
    @MethodSource("passwordProvider")
    @DisplayName("isValidPassword should validate password length properly")
    void testIsValidPassword(String password, boolean expected) {
        assertEquals(expected, accountService.isValidPassword(password));
    }

    // ✅ THÊM MỚI: Test cho isValidUsername
    static Stream<Arguments> usernameProvider() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of("ab", false),
                Arguments.of("john_doe", true),
                Arguments.of("user123", true),
                Arguments.of("invalid username", false),
                Arguments.of("   ", false)
        );
    }

    @ParameterizedTest
    @MethodSource("usernameProvider")
    @DisplayName("isValidUsername should validate usernames correctly")
    void testIsValidUsername(String username, boolean expected) {
        assertEquals(expected, accountService.isValidUsername(username));
    }
}

