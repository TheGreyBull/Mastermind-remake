import javax.swing.*;
import java.awt.*;

public class OptionPane extends JFrame {
    JTextArea messageArea;
    JPanel buttonsPanel;
    protected OptionPane() {
        defaultSettings();
    }

    protected void okOption(boolean isDarkTheme, String title, String message) {
        reUsableSettings(isDarkTheme);
        this.setTitle(title);
        messageArea.setText(message);
        JButton okButton = new JButton("OK");
        buttonSettings(isDarkTheme, okButton);
    }

    protected int multiOption(boolean isDarkTheme, String title, String message, String[] choices) {
        reUsableSettings(isDarkTheme);
        /*new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Frame frame = (Frame) evt.getSource();
                System.out.println("Closing = "+frame.getTitle());
            }
        };*/
        return 0;
    }

    private void defaultSettings() {
        this.setVisible(false);
        this.setSize(450, 225);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        messageArea = new JTextArea();
        buttonsPanel = new JPanel();

        messageArea.setLayout(new FlowLayout());
        messageArea.setEditable(false);
        messageArea.setWrapStyleWord(true);
        messageArea.setLineWrap(true);

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setBounds(0, 145, 450, 45);

        this.getContentPane().add(messageArea);
        this.getContentPane().add(buttonsPanel);
    }
    private void reUsableSettings(boolean isDarkTheme) {
        messageArea.setText("");
        buttonsPanel.removeAll();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        messageArea.setBounds(20, 20, 400, 115);
        messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    private void buttonSettings(boolean isDarkTheme, JButton button) {
        buttonsPanel.add(button);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFocusable(false);
    }
}
