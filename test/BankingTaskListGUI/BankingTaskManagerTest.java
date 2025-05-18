package BankingTaskListGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BankingTaskManagerTest {

    private BankingTaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new BankingTaskManager();
        manager.createAccount("123", 100.0, 0.12); // 12% annual interest
    }

    @Test
    void testCreateAccountAndViewDetails() {
        manager.viewAccountDetails("123"); // Should print without errors

        BankAccount account = manager.getAccount("123");
        assertNotNull(account);
        assertEquals(100.0, account.getBalance(), 0.001);
        assertEquals(0.12, account.getAnnualInterestRate(), 0.001);
    }

    @Test
    void testDepositValid() {
        manager.deposit("123", 50.0);
        assertEquals(150.0, manager.getAccount("123").getBalance(), 0.001);
        ArrayList<String> tasks = manager.getLowPriorityTasks();
        assertTrue(tasks.get(0).contains("Deposit RMB 50.0 to Account: 123"));
    }

    @Test
    void testDepositToInvalidAccount() {
        manager.deposit("999", 50.0); // Should show account not found
        assertTrue(manager.getLowPriorityTasks().isEmpty());
    }

    @Test
    void testWithdrawValid() {
        manager.withdraw("123", 30.0);
        assertEquals(70.0, manager.getAccount("123").getBalance(), 0.001);
        ArrayList<String> tasks = manager.getLowPriorityTasks();
        assertTrue(tasks.get(0).contains("Withdrew RMB 30.0 from account: 123"));
    }

    @Test
    void testWithdrawInsufficientFunds() {
        manager.withdraw("123", 1000.0);
        assertEquals(100.0, manager.getAccount("123").getBalance(), 0.001); // No change
        ArrayList<String> tasks = manager.getLowPriorityTasks();
        assertTrue(tasks.get(0).contains("Failed withdrawal attempt of RMB 1000.0 from account: 123"));
    }

    @Test
    void testMonthlyProcessing() {
        manager.monthlyProcess("123");
        BankAccount account = manager.getAccount("123");
        double expectedInterest = 100.0 * (0.12 / 12); // 1% monthly
        assertEquals(100.0 + expectedInterest, account.getBalance(), 0.001);
        assertTrue(manager.getLowPriorityTasks().get(0).contains("Performed monthly process for account: 123"));
    }

    @Test
    void testTaskOperations() {
        manager.addTask("Pay rent *");
        manager.addTask("Buy groceries");
        assertEquals(1, manager.getHighPriorityTasks().size());
        assertEquals(1, manager.getLowPriorityTasks().size());

        manager.promoteTask(0); // Only one high-priority task, nothing should change
        assertEquals("Pay rent *", manager.getHighPriorityTasks().get(0));

        manager.changePriority("high", 0); // Move to low priority
        assertEquals(2, manager.getLowPriorityTasks().size());
        assertEquals(0, manager.getHighPriorityTasks().size());

        manager.removeTask("low", 1);
        assertEquals(1, manager.getLowPriorityTasks().size());
    }

    @Test
    void testInvalidAccountOperations() {
        manager.withdraw("invalid", 50.0); // Should not throw
        manager.deposit("invalid", 50.0);  // Should not throw
        manager.viewAccountDetails("invalid"); // Should not throw
        manager.monthlyProcess("invalid"); // Should not throw

        assertTrue(manager.getLowPriorityTasks().isEmpty()); // No tasks logged
    }
}

