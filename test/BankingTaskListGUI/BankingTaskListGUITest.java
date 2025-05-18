package BankingTaskListGUI;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class BankingTaskListGUITest {

    @Test
    void testGUIInitialization() {
        SwingUtilities.invokeLater(() -> {
            BankingTaskListGUI gui = new BankingTaskListGUI();
            // Simple assertions can be done here if components are made package-private or accessible
            assertNotNull(gui);
        });
    }
}
