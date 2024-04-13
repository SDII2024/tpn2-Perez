package org.tp2.seccion2.ejercicio1.cliente;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tp2.seccion2.ejercicio1.calculadora.AddResponse;
import org.tp2.seccion2.ejercicio1.calculadora.DivideResponse;
import org.tp2.seccion2.ejercicio1.calculadora.MultiplyResponse;
import org.tp2.seccion2.ejercicio1.calculadora.SubtractResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class UiCalculadora extends JFrame implements ActionListener {
    static Logger logger = LogManager.getLogger(UiCalculadora.class);
    private final ClienteCalculadora clienteCalculadora;
    private JTextField textField;
    private int op1 = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    @Autowired
    public UiCalculadora(ClienteCalculadora clienteCalculadora) {
        this.clienteCalculadora = clienteCalculadora;
    }

    public void crearVentana() {
        SwingUtilities.invokeLater(() -> {
        	setTitle("Calculadora");
        	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        	iniciarComponentes();
        	setSize(250, 250);
        	setLocationRelativeTo(null);
        	setVisible(true);
        });
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel(new BorderLayout());

        textField = new JTextField(15);
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 7, 7));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "=" -> calcularResultado();
            case "C" -> clear();
            default -> {
                if (command.matches("[0-9]+") || command.equals(".")) {
                    if (isOperatorClicked) {
                        textField.setText("");
                        isOperatorClicked = false;
                    }
                    textField.setText(textField.getText() + command);
                } else {
                    if (!operator.isEmpty()) {
                        calcularResultado();
                    }
                    op1 = Integer.parseInt(textField.getText());
                    operator = command;
                    isOperatorClicked = true;
                }
            }
        }
    }

    private void calcularResultado() {
        int op2 = Integer.parseInt(textField.getText());
        double resultado = 0;
        switch (operator) {
            case "+" -> {
                AddResponse responseSumar = clienteCalculadora.sumar(op1, op2);
                resultado = responseSumar.getAddResult();
            }
            case "-" -> {
                SubtractResponse responseRestar = clienteCalculadora.restar(op1, op2);
                resultado = responseRestar.getSubtractResult();
            }
            case "*" -> {
                MultiplyResponse responseMultiplicar = clienteCalculadora.multiplicar(op1, op2);
                resultado = responseMultiplicar.getMultiplyResult();
            }
            case "/" -> {
                if (op2 == 0) {
                    JOptionPane.showMessageDialog(this, "No se puede dividir por cero", "Error", JOptionPane.ERROR_MESSAGE);
                    clear();
                    return;
                }
                DivideResponse responseDividir = clienteCalculadora.dividir(op1, op2);
                resultado = responseDividir.getDivideResult();
            }
            default -> textField.setText("Operacion no valida");
        }
        textField.setText(String.valueOf(resultado));
        operator = "";
    }

    private void clear() {
        textField.setText("");
        op1 = 0;
        operator = "";
        isOperatorClicked = false;
    }
}

