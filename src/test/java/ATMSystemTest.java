import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.MessageFormat;

import static org.junit.Assert.assertEquals;
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

    @Test(expected = AccountDoesNotExist.class)
    public void enterAccountInfoRaisesExceptionIfAccountDoesNotExistOnDataStore() throws AccountDoesNotExist {
        system.enterAccountInfo(Bank.WOORI, "DOESNOTEXIST");
    }

    @Test
    public void shouldCountBillCorrectlyWhenWithdraw() {
        system.enterBillAmountToWithdraw(80000);
        int [] billAmount = system.getBillAmount();
        assertEquals(billAmount[0],3);
        assertEquals(billAmount[1],1);
    }

    @Test
    public void shouldCOuntBillCorrectlyWhenWithdrawAsDollars() {
        system.enterBillAmountToWithdrawAsDollar(170);
        int [] billAmount = system.getBillAmount();
        assertEquals(billAmount[0],0);
        assertEquals(billAmount[1],1);
        assertEquals(billAmount[2],1);
        assertEquals(billAmount[3],1);
    }

}
