package org.tp2.seccion2.ejercicio1.cliente;


import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tp2.seccion2.ejercicio1.calculadora.AddResponse;
import org.tp2.seccion2.ejercicio1.calculadora.DivideResponse;
import org.tp2.seccion2.ejercicio1.calculadora.MultiplyResponse;
import org.tp2.seccion2.ejercicio1.calculadora.SubtractResponse;

import static java.lang.Integer.parseInt;

@Component
public class UiCalculadora {
    static Logger logger = LogManager.getLogger(UiCalculadora.class);
    private final ClienteCalculadora clienteCalculadora;
    private JTextField inputA;
    private JTextField inputB;
    private ButtonGroup btnOperadores;
    private JLabel labelResultado;

    @Autowired
    public UiCalculadora(ClienteCalculadora clienteCalculadora) {
        this.clienteCalculadora = clienteCalculadora;
    }

    public void crearVentana() {
        SwingUtilities.invokeLater(() -> {
        	JFrame frame = new JFrame("Calculadora");
        	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        	iniciarComponentes(frame);
        	frame.setSize(250, 250);
        	frame.setLocationRelativeTo(null);
        	frame.setVisible(true);
        });
    }

    private void iniciarComponentes(JFrame frame) {
        JLabel labelA = new JLabel("Numero A:");
        JLabel labelB = new JLabel("Numero B:");
        labelResultado = new JLabel("  ", SwingConstants.LEFT);
        inputA = new JTextField();
        inputB = new JTextField();
        JButton btnCalcular = new JButton("Calcular");

        Box boxOperadores = Box.createHorizontalBox();
        btnOperadores = new ButtonGroup();
        for (String operador : new String[]{"+", "-", "*", "/"}) {
            JToggleButton btnOperador = new JToggleButton(operador);
            btnOperador.setActionCommand(operador);
            boxOperadores.add(btnOperador);
            btnOperadores.add(btnOperador);
        }

        btnCalcular.addActionListener(e -> calcularResultado());

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(labelA)
                .addComponent(inputA)
                .addComponent(labelB)
                .addComponent(inputB)
                .addComponent(boxOperadores)
                .addComponent(btnCalcular)
                .addComponent(labelResultado));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(labelA)
                .addComponent(inputA)
                .addComponent(labelB)
                .addComponent(inputB)
                .addComponent(boxOperadores)
                .addComponent(btnCalcular)
                .addComponent(labelResultado));

        frame.add(panel);
    }

    private void calcularResultado() {
        try {
            String num1 = inputA.getText();
            String num2 = inputB.getText();

            if (num1.isEmpty() || num2.isEmpty()) {
                labelResultado.setText("Ingrese ambos valores");
                return;
            }

            int op1 = parseInt(num1);
            int op2 = parseInt(num2);

            String oper = btnOperadores.getSelection().getActionCommand();

            int resultado;
            switch (oper) {
                case "+" -> {
                    AddResponse responseSumar = clienteCalculadora.sumar(op1,op2);
                    resultado = responseSumar.getAddResult();
                }
                case "-" -> {
                    SubtractResponse responseRestar = clienteCalculadora.restar(op1,op2);
                    resultado = responseRestar.getSubtractResult();
                }
                case "*" -> {
                    MultiplyResponse responseMultiplicar = clienteCalculadora.multiplicar(op1,op2);
                    resultado = responseMultiplicar.getMultiplyResult();
                }
                case "/" -> {
                    DivideResponse responseDividir = clienteCalculadora.dividir(op1,op2);
                    resultado = responseDividir.getDivideResult();
                }
                default -> {
                    labelResultado.setText("Operador no valido");
                    return;
                }
            }

            labelResultado.setText("Resultado: " + resultado);
        } catch (NumberFormatException ex) {
            labelResultado.setText("Los valores deben ser numeros");
        } catch (RuntimeException ex) {
            logger.error("Error al realizar la operacion: {}", ex.getMessage());
            labelResultado.setText("Error al realizar la operacion");
        }
    }
}

