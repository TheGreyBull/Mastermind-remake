import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class OptionPane extends JFrame {
    JTextArea messageArea;
    JPanel buttonsPanel;
    // All dark theme colors
    Color darkBackground;
    Color darkButtonBackground;
    Color darkButtonText;
    // All light theme colors
    Color lightBackground;
    Color lightButtonBackground;
    Color lightButtonText;
    int returnValue;

    protected OptionPane() {
        defaultSettings();
    }

    protected void okOption(boolean isDarkTheme, String title, String message) {
        reUsableSettings();
        this.setTitle(title);
        messageArea.setText(message);
        JButton okButton = new JButton("OK");
        buttonSettings(okButton, isDarkTheme);
        textAreaResize();
        okButton.addActionListener(e -> this.dispose());
        if (isDarkTheme) {
            this.getContentPane().setBackground(darkBackground);
            messageArea.setBackground(darkBackground);
            messageArea.setForeground(Color.WHITE);
            buttonsPanel.setBackground(darkBackground);
            okButton.setBackground(darkButtonBackground);
            okButton.setForeground(darkButtonText);
        }
        else {
            this.getContentPane().setBackground(lightBackground);
            messageArea.setBackground(lightBackground);
            messageArea.setForeground(lightButtonText);
            buttonsPanel.setBackground(lightBackground);
            okButton.setBackground(lightButtonBackground);
            okButton.setForeground(lightButtonText);
        }
        blockUntilClosed();
    }

    // choices return values between 0 (the first button) and choices.length-1 (the last button)
    protected void multiOption(boolean isDarkTheme, String title, String message, String[] choices) {
        reUsableSettings();
        this.setTitle(title);
        messageArea.setText(message);
        textAreaResize();

        if (isDarkTheme) {
            this.getContentPane().setBackground(darkBackground);
            messageArea.setBackground(darkBackground);
            messageArea.setForeground(Color.WHITE);
            buttonsPanel.setBackground(darkBackground);
        }
        else {
            this.getContentPane().setBackground(lightBackground);
            messageArea.setBackground(lightBackground);
            messageArea.setForeground(lightButtonText);
            buttonsPanel.setBackground(lightBackground);
        }

        JButton[] buttons = new JButton[choices.length];
        for (int i = 0; i < choices.length; i++) {
            buttons[i] = new JButton(choices[i]);
            buttonSettings(buttons[i], isDarkTheme);
            if (isDarkTheme) {
                buttons[i].setBackground(darkButtonBackground);
                buttons[i].setForeground(darkButtonText);
            }
            else {
                buttons[i].setBackground(lightButtonBackground);
                buttons[i].setForeground(lightButtonText);
            }
            int finalI = i;
            buttons[i].addActionListener(e -> {
                returnValue = finalI;
                this.dispose();
            });
        }
        blockUntilClosed();
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

        // Declaration
        darkBackground = new Color(0x172A46);
        darkButtonBackground = new Color(0x0A192F);
        darkButtonText = new Color(0xBAC5E6);

        lightBackground = new Color(0xDDE1FA);
        lightButtonBackground = new Color(0xF1F1FB);
        lightButtonText = new Color(0x523EB9);
    }
    private void reUsableSettings() {
        messageArea.setText("");
        buttonsPanel.removeAll();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(450, 225);
        messageArea.setBounds(20, 20, 400, 115);
        buttonsPanel.setBounds(0, 145, 450, 45);
    }

    private void buttonSettings(JButton button, boolean isDarkTheme) {
        buttonsPanel.add(button);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEmptyBorder(7, 15, 7, 15));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (isDarkTheme)
                    button.setBackground(new Color(0x03132C));
                else
                    button.setBackground(new Color(0xECECFC));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (isDarkTheme)
                    button.setBackground(darkButtonBackground);
                else
                    button.setBackground(lightButtonBackground);
            }
        });
    }

    private void textAreaResize() {
        int linesCount = countLines(messageArea);
        linesCount += (messageArea.getLineCount() - 1);
        /*
         * Default frame size supports up to 7 lines length
         * Increases by 15 for each line
         */
        if (linesCount > 7) {
            linesCount -= 7;
            this.setSize(450, 225 + (linesCount * 15));
            messageArea.setBounds(20, 20, 400, 115 + (linesCount * 15));
            buttonsPanel.setBounds(0, 145 + (linesCount * 15), 450, 45);
            this.setLocationRelativeTo(null);
        }
    }

    private int countLines(JTextArea textArea) {
        AttributedString text = new AttributedString(textArea.getText());
        text.addAttribute(TextAttribute.FONT, textArea.getFont());
        FontRenderContext frc = textArea.getFontMetrics(textArea.getFont()).getFontRenderContext();
        AttributedCharacterIterator charIt = text.getIterator();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(charIt, frc);
        Insets textAreaInsets = textArea.getInsets();
        float formatWidth = textArea.getWidth() - textAreaInsets.left - textAreaInsets.right;
        lineMeasurer.setPosition(charIt.getBeginIndex());

        int noLines = 0;
        while (lineMeasurer.getPosition() < charIt.getEndIndex())
        {
            lineMeasurer.nextLayout(formatWidth);
            noLines++;
        }

        return noLines;
    }

    private void blockUntilClosed() {
        while (isDisplayable()) {}
        System.out.println("s");
    }
}
