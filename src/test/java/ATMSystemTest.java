import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.MessageFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SerenityRunner.class)
public class ATMSystemTest {
    public ATMSystem system;

    @Before
    public void initATMSystem() {
        this.system = new ATMSystem();
    }

    @Test(expected = InvalidBillException.class)
    public void enterBillRaisesExceptionIfLengthIsInvalid() throws InvalidBillException, DataStoreError {
        int[] billAmount = {1, 2};

        system.enterBill(billAmount);
    }

    @Test(expected = InvalidBillException.class)
    public void enterBillAsDollarRaisesExceptionIfLengthIsInvalid() throws InvalidBillException, DataStoreError {
        int [] billAmount = { 1, 2 };

        system.enterBillAsDollar(billAmount);
    }
    @Test(expected = AccountDoesNotExist.class)
    public void enterAccountInfoRaisesExceptionIfAccountDoesNotExistOnDataStore() throws AccountDoesNotExist, DataStoreError {
        system.enterAccountInfo(Bank.WOORI, "DOESNOTEXIST");
    }

    @Test
    public void enterAccountInfoChangesProperty() throws DataStoreError {
        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        }

        assertNotNull(system.getAccount());
        assertEquals(system.getAccount().getBank(), Bank.HANA);
        assertEquals(system.getAccount().getAccountNo(), "123456789012345");
    }

    @Test
    public void enterPasswordSuccess() {
        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist " + e.getMessage());
        } catch (DataStoreError e) {
            fail("throw DataStoreError " + e.getMessage());
        }

        try {
            system.enterPassword(5555);
        } catch (InvalidPasswordException e) {
            fail("throw InvalidPasswordException " + e.getMessage());
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        }

        // PASS
    }

    @Test(expected = InvalidPasswordException.class)
    public void enterPasswordFreezesAccountWhenInvalidInputIsRepeatForFiveTimes() throws InvalidPasswordException {
        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        } catch (DataStoreError e) {
            fail("throw DataStoreError" + e.getMessage());
        }

        try {
            system.enterPassword(0000);
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        }
    }
}
