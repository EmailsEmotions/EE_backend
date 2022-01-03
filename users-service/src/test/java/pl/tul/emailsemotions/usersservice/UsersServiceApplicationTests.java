package pl.tul.emailsemotions.usersservice;

import org.junit.jupiter.api.Test;
import pl.tul.emailsemotions.userservice.utils.Utilities;

import static org.assertj.core.api.Assertions.assertThat;

class UsersServiceApplicationTests {
    public static final String EMAIL_TEST_CORRECT_1 = "mateusz.walczak@p.lodz.pl";
    public static final String EMAIL_TEST_CORRECT_2 = "bartlomiej.jencz123@p.lodz.pl";
    public static final String EMAIL_TEST_WRONG_1 = "konrad.kajszczak@www";
    public static final String EMAIL_TEST_WRONG_2 = "konrad.kajszczak.p.lodz.pl";

    @Test
    void emailRegexTest() {
        assertThat(EMAIL_TEST_CORRECT_1.matches(Utilities.EMAIL_REGEX)).isEqualTo(true);
        assertThat(EMAIL_TEST_CORRECT_2.matches(Utilities.EMAIL_REGEX)).isEqualTo(true);
        assertThat(EMAIL_TEST_WRONG_1.matches(Utilities.EMAIL_REGEX)).isEqualTo(false);
        assertThat(EMAIL_TEST_WRONG_2.matches(Utilities.EMAIL_REGEX)).isEqualTo(false);
    }
}
