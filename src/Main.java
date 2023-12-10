import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ButtonPanel buttonPanel = new ButtonPanel(4, 4, "graphqlSchemaUrl");

            // Panel'in kendisi için JFrame
            JFrame frame = new JFrame("Button Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(buttonPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
class ButtonPanel extends JPanel {
    private JButton[][] buttons;
    private boolean[][] buttonStates;
    private String graphqlSchemaUrl;

    public ButtonPanel(int rows, int cols, String graphqlSchemaUrl) {
        this.graphqlSchemaUrl = graphqlSchemaUrl;
        buttons = new JButton[rows][cols];
        buttonStates = new boolean[rows][cols];
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = createButton();
                add(buttons[i][j]);
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(row, col);
                    }
                });
            }
        }
    }
    private JButton createButton() {
        JButton button = new JButton();
        button.setBackground(Color.BLACK);
        button.setIcon(new ImageIcon());
        return button;
    }
    private void buttonClicked(int row, int col) {
        if (buttonStates[row][col]) {
            // Buton aktifse, pasif hale getirir
            // Eğer buton pasifse, aktif hale getirir ve durumunu günceller
            buttons[row][col].setBackground(Color.BLACK);
            buttons[row][col].setIcon(new ImageIcon());
        } else {
            buttons[row][col].setBackground(Color.PINK);
            buttons[row][col].setIcon(new ImageIcon());
            // GraphQL şemasında bir mutation çalıştır
            runGraphQLMutation(graphqlSchemaUrl);
        }
        updateButtonStates(row, col);
    }
    private void updateButtonStates(int clickedRow, int clickedCol) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (i != clickedRow || j != clickedCol) {
                    buttonStates[i][j] = false;
                    buttons[i][j].setBackground(Color.BLACK);
                    buttons[i][j].setIcon(new ImageIcon());
                } else {
                    buttonStates[i][j] = true;
                }
            }
        }
    }

    private void runGraphQLMutation(String graphqlSchemaUrl) {
        // GraphQL için gerekli void.
    }
}