package view;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    public MainFrame() {
        setTitle("Recipe Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new MainPanel();
        add(mainPanel);

        pack();
        setLocationRelativeTo(null); //for centering the frame
        setVisible(true);

    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
