package com.swad.cppatm;

import com.swad.cppatm.application.*;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class ATMSystemTest {
    public ATMSystem system;

    @Before
    public void initATMSystem() throws Exception {
        this.system = new ATMSystem();
        this.system.selectFunction(FunctionType.Deposit);
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
    public void enterAccountInfoRaisesExceptionIfAccountDoesNotExistOnDataStore() throws AccountDoesNotExist, DataStoreError, NoneOfFunctionSelected {
        system.enterAccountInfo(Bank.WOORI, "DOESNOTEXIST");
    }

    @Test
    public void enterBillAmountToWithdrawAsDollarCorrectlyWorking() {
        try {
            system.selectFunction(FunctionType.ForeignWithdraw);
        } catch (NoneOfFunctionSelected e) {

        }

        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (Exception e) {

        }

        int []initialAmount = {1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000};

        try {
            system.getBalance().setATMBalance(initialAmount);
        } catch (OverflowBillException ex) {

        }

        try {
            system.enterBillAmountToWithdrawAsDollar(200);
        } catch (DataStoreError | NegativeBalanceError | OverflowBillException e) {
            fail(e.getClass().getSimpleName());
        }

        assertEquals(998, system.getBalance().getATMBalance()[10]);
    }

    @Test
    public void enterAccountInfoChangesProperty() {
        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (AccountDoesNotExist | DataStoreError e) {
            fail(e.getClass().getSimpleName());
        } catch (NoneOfFunctionSelected e) {

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
        } catch (NoneOfFunctionSelected e) {

        }

        try {
            system.enterPassword(5555);
        } catch (InvalidPasswordException e) {
            fail("throw InvalidPasswordException " + e.getMessage());
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        } catch(DataStoreError | NegativeBalanceError e){

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
        } catch (NoneOfFunctionSelected e) {

        }

        try {
            system.enterPassword(0000);
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        } catch(DataStoreError | NegativeBalanceError e){

        }

        assertFalse(system.getAccount().isAccountEnabled());
    }

    @Test
    public void calcBillAccountCorrectlyWorking() {
        int[] result = system.calcBillAmount(100000, "WON");
        int[] expectedResult = {0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, };

        assertArrayEquals(expectedResult, result);

        result = system.calcBillAmount(130, "Dollar");
        expectedResult = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1};

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
        system.selectFunction(FunctionType.SplitPay);
        system.setCashAmount(50);

        try {
            system.enterNumberOfUsers(5);
        } catch (TooFewUser e) {
            fail("throw TooFewUser" + e.getMessage());
        } catch (TooManyUsers e) {
            fail("throw TooManyUsers" + e.getMessage());
        }

        assertEquals(system.getCashAmount(), 50/5);


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

    @Test
    public void shouldGetCardListCorrectly() throws UserDoestNotExist {
        this.system.enterUserId("123456789012356");
        String[] string;
        string = this.system.getUser().getCardList();
        assertTrue(string[0].equals("123456789012345678"));
    }

    @Test
    public void enterPeriodToQueryDoesGetTransactions() throws NoneOfFunctionSelected {
        int[] billAmount = {1,1,1,1,0,0,0,0,0,0,0};
        system.selectFunction(FunctionType.Deposit);

        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
            system.enterBill(billAmount);
        } catch (AccountDoesNotExist |
            DataStoreError |
            OverflowBillException |
            InvalidBillException ex) {

        }

        system.selectFunction(FunctionType.QueryTransactionList);

        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
            system.enterPeriodToQuery(new Date(2016,5,1),
                new Date(2018,5,30));
        } catch (AccountDoesNotExist | DataStoreError ex) {

        }

        assertTrue(system.getTransactionList().length > 0);
    }
}
