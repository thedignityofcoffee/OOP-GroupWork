package BankingTaskListGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(1000.0, 0.12); // 12% annual interest
    }

    @Test
    void testInitialValues() {
        assertEquals(1000.0, account.getBalance());
        assertEquals(0.12, account.getAnnualInterestRate());
    }

    @Test
    void testDepositIncreasesBalance() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    void testDepositWithZeroAmount() {
        account.deposit(0);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void testDepositWithNegativeAmount() {
        account.deposit(-100.0);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void testWithdrawValidAmount() {
        account.withdraw(200.0);
        assertEquals(800.0, account.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        account.withdraw(2000.0);
        assertEquals(1000.0, account.getBalance()); // no change
    }

    @Test
    void testWithdrawInvalidAmountZero() {
        account.withdraw(0);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void testWithdrawFromInactiveAccount() {
        BankAccount lowBalanceAccount = new BankAccount(20.0, 0.1);
        lowBalanceAccount.withdraw(10.0);
        assertEquals(20.0, lowBalanceAccount.getBalance());
    }

    @Test
    void testCalcInterest() {
        account.calcInterest(); // 1% monthly = 1000 * 0.01 = 10.0
        assertEquals(1010.0, account.getBalance(), 0.001);
    }

    @Test
    void testMonthlyProcess() {
        account.deposit(100);
        account.withdraw(50);
        account.monthlyProcess();
        assertEquals(1050.0 * (1 + 0.01), account.getBalance(), 0.001); // After service charges (0) and interest
    }

    @Test
    void testGetMonthlyInterest() {
        double expectedInterest = 1000.0 * (0.12 / 12);
        assertEquals(expectedInterest, account.getMonthlyInterest(), 0.001);
    }
}
