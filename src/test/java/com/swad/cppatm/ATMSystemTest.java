package com.swad.cppatm;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.Lottery;
import com.swad.cppatm.application.Transaction;
import com.swad.cppatm.application.Admin;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.assertj.core.condition.Negative;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.naming.NameNotFoundException;
import java.text.MessageFormat;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class ATMSystemTest {
    public ATMSystem system;

    @Before
    public void initATMSystem() {
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
    public void enterAccountInfoRaisesExceptionIfAccountDoesNotExistOnDataStore() throws AccountDoesNotExist, DataStoreError {
        system.enterAccountInfo(Bank.WOORI, "DOESNOTEXIST");
    }

    @Test
    public void shouldCountBillCorrectlyWhenWithdrawAsDollars() {
        try {
            system.selectFunction(FunctionType.ForeignWithdraw);
        } catch (NoneOfFunctionSelected e) {

        }

        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (Exception e) {

        }

        try {
            system.enterBillAmountToWithdrawAsDollar(170);
        } catch (DataStoreError | NegativeBalanceError | OverflowBillException e ) {
            fail(e.getClass().getSimpleName());
        }
        int[] billAmount = system.getBillAmount();

        assertEquals(billAmount[0], 0);
        assertEquals(billAmount[1], 1);
        assertEquals(billAmount[2], 1);
        assertEquals(billAmount[3], 1);
    }

    @Test
    public void enterAccountInfoChangesProperty() {
        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (AccountDoesNotExist | DataStoreError e) {
            fail(e.getClass().getSimpleName());
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

        assertFalse(system.getAccount().isAccountEnabled());
    }

    @Test
    public void calcBillAccountCorrectlyWorking() {
        int[] result = system.calcBillAmount(10000, "WON");
        int[] expectedResult = {1 /* 10000 */, 0 /* 50000 */, 0 /* None */, 0 /* None */};

        assertArrayEquals(expectedResult, result);

        result = system.calcBillAmount(130, "Dollar");
        expectedResult = new int[]{1/* 10 */, 1 /* 20 */, 0 /* 50 */, 1/* 100 */};

        assertArrayEquals(expectedResult, result);
    }

    @Test(expected = TooFewUser.class)
    public void enterNumberOfUsersInputIsZero() throws TooFewUser {
        try {
            system.enterNumberOfUsers(0);
        } catch (TooManyUsers e) {
            fail("throw TooManyUsers" + e.getMessage());
        }
    }

    @Test(expected = TooManyUsers.class)
    public void enterNumberOfUsersUserNumberIsLargerThanCashAmount() throws TooManyUsers {
        system.setCashAmount(100000);
        try {
            system.enterNumberOfUsers(2000000);
        } catch (TooFewUser e) {
            fail("throw TooFewUser" + e.getMessage());
        }
    }

    @Test
    public void enterNumberOfUsersSuccessfullyDivideCashAmount() throws NoneOfFunctionSelected {
        Transaction[] transactions;

        system.selectFunction(FunctionType.SplitPay);
        system.setCashAmount(50);

        try {
            system.enterNumberOfUsers(5);
        } catch (TooFewUser e) {
            fail("throw TooFewUser" + e.getMessage());
        } catch (TooManyUsers e) {
            fail("throw TooManyUsers" + e.getMessage());
        }

        transactions = system.getSplitToTransaction();

        assertEquals(5, transactions.length);

        for (int i = 0; i < transactions.length; i++) {
            assertEquals(10, transactions[i].getAmount());
        }
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
        try {
            system.enterAdminInfo("1234", "01012341234");
        } catch (DataStoreError er) {

        }
        Admin[] admins = system.getAdmins();

        assertEquals("1234", admins[admins.length - 1].getPassword());
        assertEquals("01012341234", admins[admins.length - 1].getContact());

        try {
            system.authorizeAdmin(admins[admins.length - 1].getId(), "1234");
            system.selectFunction(FunctionType.RemoveAdmin);
        } catch (InvalidAdminException ex) {
            fail("DeleteFailed");
        } catch (NoneOfFunctionSelected ex) {

        }
    }
}
