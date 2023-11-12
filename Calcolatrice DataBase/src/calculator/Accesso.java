package calculator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.WindowConstants;

public class Accesso {
    private JPanel Panel;
    private JTextField txtFUser;
    private JTextField txtFPssw;
    private JLabel lblUser;
    private JLabel lblPssw;
    private JButton btnReg;
    private JButton btnLog;

    private String username;

    static Connection conn1 = null;
    public Accesso() {
        btnLog.addActionListener(actionEvent -> {
            String username = txtFUser.getText();
            String password = txtFPssw.getText();

            try {
                String query = "SELECT * FROM users WHERE Username = ? AND Password = ?";
                PreparedStatement preparedStatement = conn1.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    //Accesso riuscito chiude il form
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(Panel);
                    currentFrame.setVisible(false);

                    //Apre la calcolatrice e passa l'user
                    Calcolatrice calculatorForm = new Calcolatrice(username);
                    calculatorForm.showForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenziali non valide o utente non esistente. Riprova.");
                }

                //Chiudi JDBC
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Errore durante l'accesso al database.");
            }
        });

        btnReg.addActionListener(actionEvent -> {
            String username = txtFUser.getText();
            String password = txtFPssw.getText();

            try {
                //Verifica se l'utente esiste già
                String checkUserQuery = "SELECT * FROM users WHERE Username = ?";
                PreparedStatement checkUserStatement = conn1.prepareStatement(checkUserQuery);
                checkUserStatement.setString(1, username);
                ResultSet checkUserResult = checkUserStatement.executeQuery();

                if (checkUserResult.next()) {
                    JOptionPane.showMessageDialog(null, "Utente già esistente. Scegli un altro nome utente.");
                } else {
                    //Inserisci nuovo utente
                    String insertUserQuery = "INSERT INTO users (Username, Password) VALUES (?, ?)";
                    PreparedStatement insertUserStatement = conn1.prepareStatement(insertUserQuery);
                    insertUserStatement.setString(1, username);
                    insertUserStatement.setString(2, password);
                    int rowsAffected = insertUserStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        //Registrazione riuscita chiudi il form
                        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(Panel);
                        currentFrame.setVisible(false);

                        //Apri la calcolatrice e passa l'user
                        Calcolatrice calculatorForm = new Calcolatrice(username);
                        calculatorForm.showForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore durante la registrazione. Riprova.");
                    }

                    //Chiudi JDBC
                    insertUserStatement.close();
                }

                //Chiudi JDBC
                checkUserResult.close();
                checkUserStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Errore durante l'accesso al database.");
            }
        });

    }

    public static void main(String[] args) throws SQLException {
        conn1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/DBCalc", "root", "");
        JFrame frame = new JFrame("Accesso");
        frame.setContentPane(new Accesso().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}