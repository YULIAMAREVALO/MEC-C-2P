import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Reloj extends JFrame {
    private JTextField horasField;
    private JTextField minutosField;
    private JTextField segundosField;
    private JButton iniciarButton;
    private JButton detenerButton;
    private JSlider velocidadSlider;
    private Timer timer;

    public Reloj() {
        // Crear los componentes de la interfaz gráfica
        horasField = new JTextField(2);
        horasField.setEditable(false);
        minutosField = new JTextField(2);
        minutosField.setEditable(false);
        segundosField = new JTextField(2);
        segundosField.setEditable(false);
        iniciarButton = new JButton("Iniciar");
        iniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        detenerButton = new JButton("Detener");
        detenerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        velocidadSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        velocidadSlider.setMajorTickSpacing(1);
        velocidadSlider.setPaintTicks(true);
        velocidadSlider.setPaintLabels(true);

        // Crear el panel para colocar los componentes
        JPanel panel = new JPanel(new GridLayout(2, 3));
        panel.add(horasField);
        panel.add(minutosField);
        panel.add(segundosField);
        panel.add(iniciarButton);
        panel.add(detenerButton);
        panel.add(velocidadSlider);

        // Agregar el panel a la ventana y configurar la ventana
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Reloj");
        pack();
        setVisible(true);

        // Configurar el temporizador para actualizar la hora cada segundo
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarHora();
            }
        });
    }

    private void actualizarHora() {
        // Obtener la hora actual del sistema
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);

        // Actualizar las casillas de la interfaz gráfica con la hora actual
        horasField.setText(String.format("%02d", hora));
        minutosField.setText(String.format("%02d", minutos));
        segundosField.setText(String.format("%02d", segundos));

        // Obtener la velocidad del reloj del control deslizante y actualizar el temporizador
        int velocidad = velocidadSlider.getValue();
        timer.setDelay(1000 / velocidad);
    }

    public static void main(String[] args) {
        // Crear una instancia de la clase Reloj y mostrar la ventana
        new Reloj();
    }
}
