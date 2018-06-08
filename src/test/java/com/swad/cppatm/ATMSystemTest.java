package com.swad.cppatm;

import com.swad.cppatm.application.*;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class ATMSystemTest {
    private ATMSystem system;

    @Before
    public void initATMSystem() throws Exception {
        this.system = new ATMSystem();
    }

    @Test(expected = InvalidBillException.class)
    public void enterBillRaisesExceptionIfLengthIsInvalid() throws InvalidBillException, DataStoreError, OverflowBillException {
        int[] billAmount = {1, 2};
        system.enterBill(billAmount);
    }

    @Test(expected = InvalidBillException.class)
    public void enterBillAsDollarRaisesExceptionIfLengthIsInvalid() throws InvalidBillException, DataStoreError, OverflowBillException {
        int[] billAmount = {1, 2};

        system.enterBillAsDollar(billAmount);
    }

    @Test(expected = AccountDoesNotExist.class)
    public void enterAccountInfoRaisesExceptionIfAccountDoesNotExistOnDataStore()
        throws AccountDoesNotExist, FrozenAccountException, DataStoreError, NoneOfFunctionSelected, SameAccountTransferException {
        system.enterAccountInfo(Bank.WOORI, "DOESNOTEXIST");
    }

    @Test
    public void enterBillAmountToWithdrawAsDollarCorrectlyWorking() {
        int[] initialAmount = {500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500};

        try {
            system.selectFunction(FunctionType.FOREIGN_WITHDRAW);
            system.enterAccountInfo(Bank.HANA, "123456789012345");
            system.getBalance().setATMBalance(initialAmount);
        } catch (Exception ex) {
            fail(ex.getClass().getSimpleName());
        }

        try {
            system.enterBillAmountToWithdrawAsDollar(-200);
        } catch (DataStoreError | NegativeBalanceError | OverflowBillException e) {
            fail(e.getClass().getSimpleName());
        }

        assertEquals(500 - 2, system.getBalance().getATMBalance()[10]);
    }

    @Test
    public void enterAccountInfoChangesProperty() {
        try {
            system.selectFunction(FunctionType.DEPOSIT);
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (Exception ex) {
            fail(ex.getClass().getSimpleName());
        }

        assertNotNull(system.getAccount());
        assertEquals(system.getAccount().getBank(), Bank.HANA);
        assertEquals(system.getAccount().getAccountNo(), "123456789012345");
    }

    @Test
    public void enterPasswordSuccess() {
        try {
            system.selectFunction(FunctionType.DEPOSIT);
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (Exception ex) {
            fail(ex.getClass().getSimpleName());
        }

        try {
            system.enterPassword(5555);
        } catch (InvalidPasswordException | AccountDoesNotExist | DataStoreError | NegativeBalanceError | FrozenAccountException ex) {
            fail(ex.getClass().getSimpleName());
        }

        // PASS
    }

    @Test
    public void enterPasswordFreezesAccountWhenInvalidInputRepeatsForFiveTimes() {
        int index;
        try {
            system.selectFunction(FunctionType.DEPOSIT);
            system.enterAccountInfo(Bank.HANA, "123456789012345t");
        } catch (FrozenAccountException ex) {
            // If account is saved as frozen, unfreeze it.
            system.getAccount().unfreezeAccount();
        } catch (Exception ex) {
            fail(ex.getClass().getSimpleName());
        }

        for (index = 0; index < 5; index++) {
            System.out.println("Input Password");

            try {
                system.enterPassword(0000);
            } catch (AccountDoesNotExist | DataStoreError | NegativeBalanceError ex) {
                fail(ex.getClass().getSimpleName());
            } catch (FrozenAccountException ex) {
                break;
            } catch (InvalidPasswordException ex) {
                System.out.println("Invalid password input for " + (index + 1) + " times");
            }
        }

        assertEquals(5 - 1, index);
    }

    @Test
    public void calcBillAmountCorrectlyWorking() {
        int[] result = system.calcBillAmount(100000, "WON");
        int[] expectedResult = {0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0,};

        assertArrayEquals(expectedResult, result);

        result = system.calcBillAmount(130, "Dollar");
        expectedResult = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1};

        assertArrayEquals(expectedResult, result);
    }

    @Test(expected = TooFewUser.class)
    public void enterNumberOfUsersInputIsZero() throws TooFewUser {
        system.enterNumberOfUsers(0);
    }

    @Test
    public void enterNumberOfUsersSuccessfullyDivideCashAmount() throws NoneOfFunctionSelected, MultipleFunctionsExecuted {
        system.selectFunction(FunctionType.SPLIT_PAY);
        system.setCashAmount(1000);

        try {
            system.enterNumberOfUsers(5);
        } catch (TooFewUser e) {
            fail("throw TooFewUser" + e.getMessage());
        }

        assertEquals(system.getCashAmount(), 1000 / 5);
    }

    @Test
    public void enterLotteryMakesTransaction() {
        int[] winningNumbers = {6, 10, 18, 25, 34, 35};
        Lottery lottery = new Lottery(807, winningNumbers);

        try {
            system.enterLottery(lottery);
        } catch (LotteryFailed e) {
            fail("throw LotteryFailed" + e.getMessage());
        }

        assertEquals(50000000, system.getToTransaction().getAmount());
    }

    @Test(expected = LotteryFailed.class)
    public void enterLotteryFailedWhenLose() throws LotteryFailed {
        int[] loseNumbers = {1, 2, 3, 4, 5, 7};
        Lottery lottery = new Lottery(807, loseNumbers);

        system.enterLottery(lottery);
    }

    @Test
    public void enterAdminInfoCorrectlyWorking() {
        system.removeFunctionSelection();

        try {
            system.enterAdminInfo("1234", "01012341234");
        } catch (DataStoreError ex) {
            fail(ex.getClass().getSimpleName());
        }

        Admin[] admins = system.getAdmins();

        assertEquals("1234", admins[admins.length - 1].getPassword());
        assertEquals("01012341234", admins[admins.length - 1].getContact());

        try {
            system.authorizeAdmin(admins[admins.length - 1].getId(), "1234");
            system.selectFunction(FunctionType.REMOVE_ADMIN);
        } catch (InvalidAdminException | NoneOfFunctionSelected | MultipleFunctionsExecuted ex) {
            fail(ex.getClass().getSimpleName());
        }
    }

    @Test
    public void shouldGetCardListCorrectly() throws UserDoestNotExist {
        this.system.enterUserId("123456789012356");
        String[] string;
        string = this.system.getUser().getCardList();
        assertEquals("123456789012345678", string[0]);
    }

    @Test
    public void enterPeriodToQueryDoesGetTransactions() throws NoneOfFunctionSelected, MultipleFunctionsExecuted {
        system.selectFunction(FunctionType.QUERY_TRANSACTION_LIST);

        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
            system.enterPeriodToQuery(new Date(2016 - 1900, 5 - 1, 1),
                new Date(2018 - 1900, 5 - 1, 30));
        } catch (AccountDoesNotExist | DataStoreError | FrozenAccountException | SameAccountTransferException ex) {
            fail(ex.getClass().getSimpleName());
        }

        assertTrue(system.getTransactionList().length > 0);
    }

    @After
    public void restoreFile() throws DataStoreError {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        account.saveAccount();
    }
}
