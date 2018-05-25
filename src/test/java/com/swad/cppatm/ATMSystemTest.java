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
    public void enterAccountInfoRaisesExceptionIfAccountDoesNotExistOnDataStore()
        throws AccountDoesNotExist, FrozenAccountException, DataStoreError, NoneOfFunctionSelected {
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

        int []initialAmount = {500,500,500,500,500,500,500,500,500,500,500};

        try {
            system.getBalance().setATMBalance(initialAmount);
        } catch (OverflowBillException ex) {

        }

        try {
            system.enterBillAmountToWithdrawAsDollar(-200);
        } catch (DataStoreError | NegativeBalanceError | OverflowBillException e) {
            fail(e.getClass().getSimpleName());
        }

        assertEquals(500-2, system.getBalance().getATMBalance()[10]);
    }

    @Test
    public void enterAccountInfoChangesProperty() {
        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
        } catch (AccountDoesNotExist | DataStoreError e) {
            fail(e.getClass().getSimpleName());
        } catch (NoneOfFunctionSelected e) {

        } catch (FrozenAccountException e) {

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

        } catch (FrozenAccountException e) {
            fail("throw FrozenAccountError" + e.getMessage());
        }

        try {
            system.enterPassword(5555);
        } catch (InvalidPasswordException e) {
            fail("throw InvalidPasswordException " + e.getMessage());
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        } catch(DataStoreError | NegativeBalanceError e){

        } catch (FrozenAccountException e) {
            fail("Account is Frozen");
        }

        // PASS
    }

    @Test(expected = FrozenAccountException.class)
    public void enterPasswordFreezesAccountWhenInvalidInputIsRepeatForFiveTimes() throws FrozenAccountException {
        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345t");
        } catch (AccountDoesNotExist e) {
            fail("throw AccountDoesNotExist" + e.getMessage());
        } catch (DataStoreError e) {
            fail("throw DataStoreError" + e.getMessage());
        } catch (NoneOfFunctionSelected e) {

        } catch (FrozenAccountException e) {

        }

        while(true){
            System.out.println("Input Password");
            try {
                system.enterPassword(0000);
            } catch (AccountDoesNotExist e) {
                fail("throw AccountDoesNotExist" + e.getMessage());
            } catch (DataStoreError | NegativeBalanceError e) {
                fail("throw Error");
            } catch (InvalidPasswordException e){

            }
        }
    }

    @Test
    public void calcBillAmountCorrectlyWorking() {
        int[] result = system.calcBillAmount(100000, "WON");
        int[] expectedResult = {0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, };

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
    public void enterNumberOfUsersSuccessfullyDivideCashAmount() throws NoneOfFunctionSelected {
        system.selectFunction(FunctionType.SplitPay);
        system.setCashAmount(1000);

        try {
            system.enterNumberOfUsers(5);
        } catch (TooFewUser e) {
            fail("throw TooFewUser" + e.getMessage());
        }

        assertEquals(system.getCashAmount(), 1000/5);


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
        system.selectFunction(FunctionType.QueryTransactionList);

        try {
            system.enterAccountInfo(Bank.HANA, "123456789012345");
            system.enterPeriodToQuery(new Date(2016-1900,5-1,1),
                new Date(2018-1900,5-1,30));
        } catch (AccountDoesNotExist | DataStoreError ex) {

        } catch (FrozenAccountException e) {

        }
        assertTrue(system.getTransactionList().length > 0);
    }

    @After
    public void restoreFile() throws DataStoreError {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        account.saveAccount();
    }
}
