package org.tp2.seccion2.ejercicio3.cliente;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.tp2.seccion2.ejercicio3.paises.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


@Component
public class UIPaises implements ActionListener {
    static Logger logger = LogManager.getLogger(UIPaises.class);
    private final ClientePaises clientePaises;

    private JButton btnListado;
    private JButton btnCapital;
    private JButton btnMoneda;
    private JButton btnBandera;
    private JButton btnTelefono;
    private JTextField inputISPO;
    private JLabel labelResultado;
    private JTextArea outputResultado;

    @Autowired
    public UIPaises(ClientePaises clientePaises) {
        this.clientePaises = clientePaises;
    }

    public void crearVentana() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Paises");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            iniciarComponentes(frame);
            frame.setSize(450, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private void iniciarComponentes(JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel labelIntro;
        labelIntro = new JLabel("ISO-CODE");
        inputISPO = new JTextField();
        btnListado = new JButton("Listado de paises");
        btnCapital = new JButton("Capital");
        btnMoneda = new JButton("Moneda");
        btnBandera = new JButton("Bandera");
        btnTelefono = new JButton("Codigo Internacional");
        labelResultado = new JLabel("");
        outputResultado = new JTextArea();

        inputISPO.setPreferredSize(new Dimension(150, 30));

        JScrollPane scrollPane = new JScrollPane(outputResultado);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(btnListado);
        buttonPanel.add(btnCapital);
        buttonPanel.add(btnMoneda);
        buttonPanel.add(btnBandera);
        buttonPanel.add(btnTelefono);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(labelIntro);
        inputPanel.add(inputISPO);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(labelResultado, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.EAST);

        btnListado.addActionListener(this);
        btnCapital.addActionListener(this);
        btnMoneda.addActionListener(this);
        btnBandera.addActionListener(this);
        btnTelefono.addActionListener(this);

        frame.add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(Objects.equals(e.getActionCommand(), btnListado.getActionCommand())){
                StringBuilder stringBuilder = new StringBuilder();
                ListOfCountryNamesByNameResponse getLista = clientePaises.getListOfCountryNamesByName();
                ArrayOftCountryCodeAndName listaPaises = getLista.getListOfCountryNamesByNameResult();
                for (TCountryCodeAndName elemento : listaPaises.getTCountryCodeAndName()) {
                    stringBuilder.append(elemento.getSName()).append("\n");
                }
                outputResultado.setText(stringBuilder.toString());

            }
            if(Objects.equals(e.getActionCommand(), btnCapital.getActionCommand())){
                CapitalCityResponse getCapital = clientePaises.getCapitalCity(inputISPO.getText());
                outputResultado.setText(getCapital.getCapitalCityResult());
            }

            if(Objects.equals(e.getActionCommand(), btnMoneda.getActionCommand())){
                CountryCurrencyResponse responseCountryCurrency = clientePaises.getCountryCurrency(inputISPO.getText());
                TCurrency currency = responseCountryCurrency.getCountryCurrencyResult();
                outputResultado.setText(currency.getSName());

            }
            if(Objects.equals(e.getActionCommand(), btnBandera.getActionCommand())){
                CountryFlagResponse responseCountryFlag = clientePaises.getCountryFlag(inputISPO.getText());
                outputResultado.setText(responseCountryFlag.getCountryFlagResult());
            }
            if(Objects.equals(e.getActionCommand(), btnTelefono.getActionCommand())){
                CountryIntPhoneCodeResponse responseCountryIntPhoneCode = clientePaises.getCountryIntPhoneCode(inputISPO.getText());
                outputResultado.setText(responseCountryIntPhoneCode.getCountryIntPhoneCodeResult());
            }


        } catch (RuntimeException ex) {
            logger.error("Error al realizar la consulta: {}", ex.getMessage());
            labelResultado.setText("Error al realizar la operacion");
        }
    }

}

