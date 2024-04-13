package org.tp2.seccion2.ejercicio2.cliente;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tp2.seccion2.ejercicio2.temperatura.CelsiusToFahrenheitResponse;
import org.tp2.seccion2.ejercicio2.temperatura.FahrenheitToCelsiusResponse;

import javax.swing.*;

@Component
public class UiTemperatura {
    static Logger logger = LogManager.getLogger(UiTemperatura.class);
    private final ClienteTemperatura clienteTemperatura;
    private JTextField input;
    private ButtonGroup btnOperadores;
    private JLabel labelResultado;

    @Autowired
    public UiTemperatura(ClienteTemperatura clienteTemperatura) {
        this.clienteTemperatura = clienteTemperatura;
    }

    public void crearVentana() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setTitle("Convertidor temperaturas");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        	iniciarComponentes(frame);
            frame.setSize(380, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private void iniciarComponentes(JFrame frame) {
        JLabel labelA = new JLabel("Temperatura:");
        labelResultado = new JLabel("  ", SwingConstants.LEFT);
        input = new JTextField();
        JButton btnCalcular = new JButton("Calcular");

        Box boxOperadores = Box.createHorizontalBox();
        btnOperadores = new ButtonGroup();
        for (String operador : new String[]{"Fahrenheit a Celsius", "Celsius a Fahrenheit"}) {
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
                .addComponent(input)
                .addComponent(boxOperadores)
                .addComponent(btnCalcular)
                .addComponent(labelResultado));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(labelA)
                .addComponent(input)
                .addComponent(boxOperadores)
                .addComponent(btnCalcular)
                .addComponent(labelResultado));

        frame.add(panel);
    }

    private void calcularResultado() {
        try {
            String temp = input.getText();

            if (temp.isEmpty()) {
                labelResultado.setText("Ingrese la temperatura");
                return;
            }

            String oper = btnOperadores.getSelection().getActionCommand();

            String resultado;
            switch (oper) {
                case "Fahrenheit a Celsius" -> {
                    FahrenheitToCelsiusResponse response1 = clienteTemperatura.getFahrenheitToCelsius(temp);
                    resultado = temp + " grados Fahrenheit equivalen a " + response1.getFahrenheitToCelsiusResult() + " Celsius";
                }
                case "Celsius a Fahrenheit" -> {
                    CelsiusToFahrenheitResponse response1 = clienteTemperatura.getCelsiusToFahrenheit(temp);
                    resultado = temp + " grados Celsius equivalen a " + response1.getCelsiusToFahrenheitResult() + " Fahrenheit";
                }
                default -> {
                    labelResultado.setText("No valido");
                    return;
                }
            }

            labelResultado.setText(resultado);
        } catch (RuntimeException ex) {
            logger.error("Error al realizar la operacion: {}", ex.getMessage());
            labelResultado.setText("Error al realizar la operacion");
        }
    }
}

