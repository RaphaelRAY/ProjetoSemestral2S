package TEST;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.event.ActionEvent;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Teste {
      public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("GridLayout Example");

        // Create a JPanel with GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        // The GridLayout parameters are (rows, columns, horizontal gap, vertical gap)

        // Create buttons and add them to the panel
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            panel.add(button);
        }

        // Add the panel to the frame
        frame.add(panel);

        // Set frame properties
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
