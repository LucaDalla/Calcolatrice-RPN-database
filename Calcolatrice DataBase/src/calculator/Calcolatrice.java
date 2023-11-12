package calculator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.sql.*;
import java.util.Stack;
//------------------------------------------
public class Calcolatrice {
    public static void main(String[] args) {
    }
    private JButton btn0;
    private JButton btn1;
    private JButton btnEq;
    private JButton btnRPN;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn8;
    private JButton btn9;
    private JButton btn6;
    private JButton btn5;
    private JButton btn7;
    private JButton btnPiu;
    private JButton btnMeno;
    private JButton btnPer;
    private JButton btnDiv;
    private JTextField txtF;
    private JPanel Panel;
    private JButton btnOpen;
    private JButton btnClose;
    private JButton btnC;
    private JButton btnEqRPN;
    private JButton btnHistory;
    private String username;

    public Calcolatrice(String username) {
        this.username = username;
        btn0.addActionListener(new ActionListener() {   //scrive il numero 0
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn0text = txtF.getText() + btn0.getText();
                txtF.setText(btn0text);
            }
        });
        btn1.addActionListener(new ActionListener() {   //scrive il numero 1
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn1text = txtF.getText() + btn1.getText();
                txtF.setText(btn1text);
            }
        });
        btn2.addActionListener(new ActionListener() {   //scrive il numero 2
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn2text = txtF.getText() + btn2.getText();
                txtF.setText(btn2text);
            }
        });
        btn3.addActionListener(new ActionListener() {   //scrive il numero 3
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn3text = txtF.getText() + btn3.getText();
                txtF.setText(btn3text);
            }
        });
        btn4.addActionListener(new ActionListener() {   //scrive il numero 4
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn4text = txtF.getText() + btn4.getText();
                txtF.setText(btn4text);
            }
        });
        btn5.addActionListener(new ActionListener() {   ////scrive il numero 5
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn5text = txtF.getText() + btn5.getText();
                txtF.setText(btn5text);
            }
        });
        btn6.addActionListener(new ActionListener() {   //scrive il numero 6
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn6text = txtF.getText() + btn6.getText();
                txtF.setText(btn6text);
            }
        });
        btn7.addActionListener(new ActionListener() {   //scrive il numero 7
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn7text = txtF.getText() + btn7.getText();
                txtF.setText(btn7text);
            }
        });
        btn8.addActionListener(new ActionListener() {   //scrive il numero 8
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn8text = txtF.getText() + btn8.getText();
                txtF.setText(btn8text);
            }
        });
        btn9.addActionListener(new ActionListener() {   //scrive il numero 9
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn9text = txtF.getText() + btn9.getText();
                txtF.setText(btn9text);
            }
        });
        btnPiu.addActionListener(new ActionListener() { //scrive il segno +
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = txtF.getText();
                String btnPiuText = btnPiu.getText();

                //verifica se l'ultimo carattere nel campo di testo è lo stesso del pulsante
                if (currentText.isEmpty() || !currentText.endsWith(btnPiuText)) {
                    currentText += btnPiuText;
                    txtF.setText(currentText);
                }
            }
        });
        btnMeno.addActionListener(new ActionListener() {    //scrive il segno -
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = txtF.getText();
                String btnMenoText = btnMeno.getText();

                //verifica se l'ultimo carattere nel campo di testo è lo stesso del pulsante
                if (currentText.isEmpty() || !currentText.endsWith(btnMenoText)) {
                    currentText += btnMenoText;
                    txtF.setText(currentText);
                }
            }
        });
        btnPer.addActionListener(new ActionListener() { //scrive il segno *
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = txtF.getText();
                String btnPerText = btnPer.getText();

                //verifica se l'ultimo carattere nel campo di testo è lo stesso del pulsante
                if (currentText.isEmpty() || !currentText.endsWith(btnPerText)) {
                    currentText += btnPerText;
                    txtF.setText(currentText);
                }
            }
        });
        btnDiv.addActionListener(new ActionListener() { //scrive il segno :
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = txtF.getText();
                String btnDivText = btnDiv.getText();

                //verifica se l'ultimo carattere nel campo di testo è lo stesso del pulsante
                if (currentText.isEmpty() || !currentText.endsWith(btnDivText)) {
                    currentText += btnDivText;
                    txtF.setText(currentText);
                }
            }
        });
        btnRPN.addActionListener(new ActionListener() { //converte l'operazione nel txtF in notazione RPN e stampa
            @Override
            public void actionPerformed(ActionEvent e) {
                String operazione = txtF.getText();
                String risultato = trasformaRPN(operazione);
                txtF.setText(risultato);
            }
        });
        btnEq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operazione = txtF.getText();
                String risultato;
                risultato = trasformaRPN(operazione);
                risultato = calcolaRPN(risultato);
                txtF.setText(risultato);

                //Salvataggio nella cronologia
                try {
                    salvaCronologia(username, operazione, risultato);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Errore durante il salvataggio della cronologia", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnC.addActionListener(new ActionListener() {   //cancella tutto il testo dal textF
            @Override
            public void actionPerformed(ActionEvent e) {
                txtF.setText("");
            }
        });
        btnOpen.addActionListener(new ActionListener() {    //scrive la parentesi (
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = txtF.getText();
                if (!currentText.endsWith("(")) {   //evita la ripetizione
                    txtF.setText(currentText + "(");
                }
            }
        });
        btnClose.addActionListener(new ActionListener() {   //scrive la parentesi )
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = txtF.getText();
                if (!currentText.endsWith(")")) {   //evita la ripetizione
                    txtF.setText(currentText + ")");
                }
            }
        });
        btnEqRPN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operazione = txtF.getText();
                String risultato = calcolaRPN(operazione);
                txtF.setText(risultato);

                //Salvataggio nella cronologia
                try {
                    salvaCronologia(username, operazione, risultato);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Errore durante il salvataggio della cronologia", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Recupera cronologia per l'utente
                    String userHistory = recuperaCronologia(username);

                    //Mostra cronologia in una finestra
                    JOptionPane.showMessageDialog(null, userHistory, "Cronologia di " + username, JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Errore durante il recupero della cronologia", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    //---------------------------------------------------------------------------------
    public static String trasformaRPN(String operazione) {
        StringBuilder ris = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < operazione.length(); i++) {
            char carattere = operazione.charAt(i);

            if (Character.isDigit(carattere)) {
                StringBuilder num = new StringBuilder();
                while (i < operazione.length() && Character.isDigit(operazione.charAt(i))) {
                    num.append(operazione.charAt(i));
                    i++;
                }
                ris.append(num);
                ris.append(" ");
                i--;
            } else if (carattere == '(') {
                stack.push(carattere);
            } else if (carattere == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    ris.append(stack.pop());
                    ris.append(" ");
                }
                stack.pop(); //rimuove la parentesi aperta
            } else {
                while (!stack.isEmpty() && segnoPrima(carattere) <= segnoPrima(stack.peek())) {
                    ris.append(stack.pop());
                    ris.append(" ");
                }
                stack.push(carattere);
            }
        }
        while (!stack.isEmpty()) {
            ris.append(stack.pop());
            ris.append(" ");
        }
        return ris.toString();
    }

    private static int segnoPrima(char segno) {
        if (segno == '+' || segno == '-') {
            return 1;
        } else if (segno == '*' || segno == '/') {
            return 2;
        }
        return 0;
    }
    //-----------------------------------------------
    public static String calcolaRPN(String operazioneRPN) {
        Stack<Integer> stack = new Stack<>();

        String[] pz = operazioneRPN.split(" ");

        for (String p : pz) {
            if (isNumero(p)) {
                stack.push(Integer.parseInt(p));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();

                int risultato = operazione(p, num1, num2);
                stack.push(risultato);
            }
        }

        return stack.pop().toString();
    }

    private static boolean isNumero(String pz) {
        try {
            Integer.parseInt(pz);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int operazione(String segno, int n1, int n2) {
        switch (segno) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                return n1 / n2;
            default:
                throw new IllegalArgumentException("Segno impossibile = " + segno);
        }
    }

    public void salvaCronologia(String username, String expression, String result) throws SQLException {
        //Connessione separata per evitare conflitti
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/DBCalc", "root", "")) {
            String query = "INSERT INTO History (username, operation, result) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, expression);
                preparedStatement.setString(3, result);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String recuperaCronologia(String username) throws SQLException {
        StringBuilder history = new StringBuilder();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/DBCalc", "root", "")) {
            String query = "SELECT operation, result FROM History WHERE username = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String expression = resultSet.getString("operation");
                        String result = resultSet.getString("result");
                        history.append("Expression: ").append(expression).append("  Result: ").append(result).append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return history.toString();
    }

    public void showForm() {
        JFrame frame = new JFrame("Calcolatrice di " + username);  //Imposta titolo con l'username
        frame.setContentPane(Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}