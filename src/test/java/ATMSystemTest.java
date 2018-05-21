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

    @Test
    public void enterBillCorrectlyCountsBills() {
        int [][] billAmount = {
            {1, 2, 3, 4},
            {1, 2, 0, 0},
            {3, 4, 0, 3},
            {5, 0, 1, 0},
            {0, 1, 1, 1},
        };

        int [] expectedResultArray = {
            1 * 1000 + 2 * 5000 + 3 * 10000 + 4 * 50000,
            1 * 1000 + 2 * 5000 + 0 * 10000 + 0 * 50000,
            3 * 1000 + 4 * 5000 + 0 * 10000 + 3 * 50000,
            5 * 1000 + 0 * 5000 + 1 * 10000 + 0 * 50000,
            0 * 1000 + 1 * 5000 + 1 * 10000 + 1 * 50000,
        };

        if (billAmount.length != expectedResultArray.length) {
            fail("Mistake!");
        }

        for ( int i = 0 ; i < billAmount.length ; i++ ) {
            system.setCashAmount(0);

            try {
                system.enterBill(billAmount[i]);
            } catch (InvalidBillException e) {
                fail("throw InvalidBillException");
            }

            assertEquals(MessageFormat.format("{0}th compare", i),
                system.getCashAmount(), expectedResultArray[i]);
        }
    }

    @Test(expected = InvalidBillException.class)
    public void enterBillRaisesExceptionIfLengthIsInvalid() throws InvalidBillException {
        int [] billAmount = { 1, 2 };

        system.enterBill(billAmount);
    }

    @Test
    public void enterBillAsDollarCorrectlyCountsBills() {
    }
}
