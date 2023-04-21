import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Relojj extends JFrame implements ActionListener {

    private final JLabel labelHora;

    private final JLabel labelMinutos;
    private final JLabel labelSegundos;
    private final JTextField textFieldHora;
    private final JTextField textFieldMinutos;
    private final JTextField textFieldSegundos;
    private final JButton botonIniciar;
    private final JButton botonDetener;
    private final JButton botonAcelerar;
    private final JButton botonDesacelerar;
    private final Timer timer;
    private int horas, minutos, segundos, velocidad;

    public Relojj () {
        horas = 0;
        minutos = 0;
        segundos = 0;
        velocidad = 1000; // Velocidad en milisegundos (1 segundo)

        labelHora = new JLabel("Horas:");
        labelMinutos = new JLabel("Minutos:");
        labelSegundos = new JLabel("Segundos:");

        textFieldHora = new JTextField(2);
        textFieldHora.setEditable(false);
        textFieldMinutos = new JTextField(2);
        textFieldMinutos.setEditable(false);
        textFieldSegundos = new JTextField(2);
        textFieldSegundos.setEditable(false);

        botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(this);
        botonDetener = new JButton("Detener");
        botonDetener.addActionListener(this);
        botonAcelerar = new JButton("Acelerar");
        botonAcelerar.addActionListener(this);
        botonDesacelerar = new JButton("Desacelerar");
        botonDesacelerar.addActionListener(this);

        JPanel panelHora = new JPanel();
        panelHora.setLayout(new GridLayout(1, 6));
        panelHora.add(labelHora);
        panelHora.add(textFieldHora);
        panelHora.add(labelMinutos);
        panelHora.add(textFieldMinutos);
        panelHora.add(labelSegundos);
        panelHora.add(textFieldSegundos);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 4));
        panelBotones.add(botonIniciar);
        panelBotones.add(botonDetener);
        panelBotones.add(botonAcelerar);
        panelBotones.add(botonDesacelerar);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panelHora, BorderLayout.CENTER);
        container.add(panelBotones, BorderLayout.SOUTH);

        timer = new Timer(velocidad, (ActionEvent e) -> {
            actualizarHora();
        });

        setSize(300, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciar) {
            timer.start();
        } else if (e.getSource() == botonDetener) {
            timer.stop();
        } else if (e.getSource() == botonAcelerar) {
            velocidad /= 2;
            timer.setDelay(velocidad);
        } else if (e.getSource() == botonDesacelerar) {
            velocidad *= 2;
            timer.setDelay(velocidad);
        }
    }

    private void actualizarHora() {
        segundos++;
        if (segundos == 60) {
            segundos = 0;
            minutos++;
            if (minutos == 60) {
                minutos = 0;
                horas++;
                if (horas == 24) {
                    horas = 0;
                }
            }
        }
       

