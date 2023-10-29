import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageMenuExample {
    private static String currentLanguage = "English"; // Defina o idioma padrão

    public static void main(String[] args) {
        JFrame frame = new JFrame("Language Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JMenuBar menuBar = new JMenuBar();
        JMenu languageMenu = new JMenu("Language");

        JMenuItem englishMenuItem = new JMenuItem("English");
        JMenuItem portugueseMenuItem = new JMenuItem("Portuguese");

        englishMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLanguage("English");
            }
        });

        portugueseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLanguage("Portuguese");
            }
        });

        languageMenu.add(englishMenuItem);
        languageMenu.add(portugueseMenuItem);
        menuBar.add(languageMenu);

        frame.setJMenuBar(menuBar);

        JLabel label = new JLabel(getWelcomeMessage());
        frame.add(label);

        frame.setVisible(true);
    }

    private static void setLanguage(String language) {
        currentLanguage = language;
    }

    private static String getWelcomeMessage() {
        if (currentLanguage.equals("English")) {
            return "Welcome!";
        } else if (currentLanguage.equals("Portuguese")) {
            return "Bem-vindo!";
        } else {
            return "Welcome!";
        }
    }
}

