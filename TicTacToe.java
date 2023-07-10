import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    
    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        currentPlayer = 'X';
        
        JPanel panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.MONOSPACED, Font.BOLD, 80));
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }
        
        add(panel);
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        
        if (source.getText().equals("")) {
            source.setText(Character.toString(currentPlayer));
            source.setEnabled(false);
            
            if (isWinner(currentPlayer)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }
    
    public boolean isWinner(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(Character.toString(player)) &&
                    buttons[i][1].getText().equals(Character.toString(player)) &&
                    buttons[i][2].getText().equals(Character.toString(player))) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(Character.toString(player)) &&
                    buttons[1][j].getText().equals(Character.toString(player)) &&
                    buttons[2][j].getText().equals(Character.toString(player))) {
                return true;
            }
        }
        
        // Check diagonals
        if (buttons[0][0].getText().equals(Character.toString(player)) &&
                buttons[1][1].getText().equals(Character.toString(player)) &&
                buttons[2][2].getText().equals(Character.toString(player))) {
            return true;
        }
        if (buttons[0][2].getText().equals(Character.toString(player)) &&
                buttons[1][1].getText().equals(Character.toString(player)) &&
                buttons[2][0].getText().equals(Character.toString(player))) {
            return true;
        }
        
        return false;
    }
    
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void resetGame() {
        currentPlayer = 'X';
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("");
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TicTacToe game = new TicTacToe();
                game.setVisible(true);
            }
        });
    }
}
