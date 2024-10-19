import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Game");

        // Ustawienie rozmiaru okna na rozmiar ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        window.setSize(screenWidth, screenHeight);

        // Opcjonalnie ustawienie pełnego ekranu
        // window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // window.setUndecorated(true);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // Dodanie GamePanel
        GamePanel gp = new GamePanel();
        gp.setPreferredSize(screenSize);  // Dopasowanie rozmiaru GamePanel do ekranu
        window.add(gp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gp.LaunchGame();
    }
}
