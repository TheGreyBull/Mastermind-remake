import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainGame extends JFrame {
    double WIDTH = 0.7;
    double HEIGHT = 0.685;
    OptionPane option;
    Color darkFrameBackground;
    Color darkPanelBackground;
    JPanel rowsPanel;
    JPanel guessingPanel;
    JPanel resultPanel;
    JPanel colorsPanel;

    protected MainGame () {
        frameSettings();
        initializations();
    }

    // Initializations of global variables
    private void initializations() {
        option = new OptionPane();
        darkFrameBackground = new Color(0x0A192F);
        darkPanelBackground = new Color(0x172A46);

        this.getContentPane().setBackground(darkFrameBackground);

        rowsPanel = new JPanel();
        guessingPanel = new JPanel();
        resultPanel = new JPanel();
        colorsPanel = new JPanel();

        rowsPanel.setBackground(darkPanelBackground);
        guessingPanel.setBackground(darkPanelBackground);
        resultPanel.setBackground(darkPanelBackground);
        colorsPanel.setBackground(darkPanelBackground);

        // Adding every component
        this.add(rowsPanel);
        this.add(guessingPanel);
        this.add(resultPanel);
        this.add(colorsPanel);

        resizeVariables();
    }

    private void frameSettings() {
        this.setResizable(true);
        this.setTitle("Mastermind");
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLayout(null);
    }

    private void resizeVariables() {
        rowsPanel.setBounds((int) (WIDTH * 280), (int) (HEIGHT * 26), (int) (WIDTH * 100), (int) (HEIGHT * 1028));
        guessingPanel.setBounds((int) (WIDTH * 400), (int) (HEIGHT * 26), (int) (WIDTH * 800), (int) (HEIGHT * 1028));
        resultPanel.setBounds((int) (WIDTH * 1220), (int) (HEIGHT * 26), (int) (WIDTH * 250), (int) (HEIGHT * 1028));
        colorsPanel.setBounds((int) (WIDTH * 1490), (int) (HEIGHT * 26), (int) (WIDTH * 150), (int) (HEIGHT * 1028));
    }
}
