import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AdminTest {
    public Admin admin;

    @BeforeAll
    public void initAll() {
        this.admin = new Admin(Integer.toString(1), "pw1", "010-1234-1234");
    }

    @Test
    public void checkAdminAccountReturnsTrueIfArgumentIsValid() {
        boolean result = this.admin.checkAdminAccount(Integer.toString(1), "pw1");
        assertEquals(result, true);
    }

    @Test
    public void checkAdminAccountReturnsFalseIfArgumentIsInvalid() {
        boolean result = this.admin.checkAdminAccount(Integer.toString(1), "pw2");
        assertEquals(result, false);
        result = this.admin.checkAdminAccount(Integer.toString(2), "pw1");
        assertEquals(result, false);
    }
}
