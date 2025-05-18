package BankingTaskListGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void testAddHighPriorityTask() {
        taskManager.addTask("Pay bills *");
        ArrayList<String> high = taskManager.getHighPriorityTasks();
        assertEquals(1, high.size());
        assertEquals("Pay bills *", high.get(0));
    }

    @Test
    void testAddLowPriorityTask() {
        taskManager.addTask("Clean room");
        ArrayList<String> low = taskManager.getLowPriorityTasks();
        assertEquals(1, low.size());
        assertEquals("Clean room", low.get(0));
    }

    @Test
    void testRemoveHighPriorityTask() {
        taskManager.addTask("Submit report *");
        taskManager.removeTask("high", 0);
        assertTrue(taskManager.getHighPriorityTasks().isEmpty());
    }

    @Test
    void testRemoveLowPriorityTask() {
        taskManager.addTask("Buy groceries");
        taskManager.removeTask("low", 0);
        assertTrue(taskManager.getLowPriorityTasks().isEmpty());
    }

    @Test
    void testRemoveInvalidIndex() {
        taskManager.addTask("Schedule meeting *");
        taskManager.removeTask("high", 5);  // Invalid index
        assertEquals(1, taskManager.getHighPriorityTasks().size());
    }

    @Test
    void testChangePriorityHighToLow() {
        taskManager.addTask("Book flight *");
        taskManager.changePriority("high", 0);
        assertTrue(taskManager.getHighPriorityTasks().isEmpty());
        assertEquals(1, taskManager.getLowPriorityTasks().size());
        assertEquals("Book flight *", taskManager.getLowPriorityTasks().get(0));
    }

    @Test
    void testChangePriorityLowToHigh() {
        taskManager.addTask("Do laundry");
        taskManager.changePriority("low", 0);
        assertTrue(taskManager.getLowPriorityTasks().isEmpty());
        assertEquals(1, taskManager.getHighPriorityTasks().size());
        assertEquals("Do laundry", taskManager.getHighPriorityTasks().get(0));
    }

    @Test
    void testPromoteTask() {
        taskManager.addTask("Task A *");
        taskManager.addTask("Task B *");
        taskManager.promoteTask(1); // Promote Task B to index 0
        assertEquals("Task B *", taskManager.getHighPriorityTasks().get(0));
        assertEquals("Task A *", taskManager.getHighPriorityTasks().get(1));
    }

    @Test
    void testPromoteTaskInvalidIndex() {
        taskManager.addTask("Only task *");
        taskManager.promoteTask(0); // Cannot promote first item
        assertEquals("Only task *", taskManager.getHighPriorityTasks().get(0));
    }

    @Test
    void testGetHighPriorityTasksReturnsCopy() {
        taskManager.addTask("Task *");
        ArrayList<String> high = taskManager.getHighPriorityTasks();
        high.clear(); // Should not affect the internal list
        assertEquals(1, taskManager.getHighPriorityTasks().size());
    }

    @Test
    void testGetLowPriorityTasksReturnsCopy() {
        taskManager.addTask("Task");
        ArrayList<String> low = taskManager.getLowPriorityTasks();
        low.clear(); // Should not affect the internal list
        assertEquals(1, taskManager.getLowPriorityTasks().size());
    }
}
