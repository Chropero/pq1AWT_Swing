package vista;
/**
 *
 * @author CHRISTIAN
 */
import modelo.Cliente;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/** Ventana basada en los paneles y layouts del ejemplo Doble. */
public class Aplicacion extends JFrame {
    private final List<Cliente> clientes = new ArrayList<>();
    private int indiceActual = 0;

    private final JTextField numeroField = new JTextField(18);
    private final JTextField nombreField = new JTextField(18);
    private final JTextField edadField = new JTextField(18);
    private final JTextField puntosField = new JTextField(18);
    private final JButton anteriorButton = new JButton("Anterior");
    private final JButton siguienteButton = new JButton("Siguiente");
    private final JLabel posicionLabel = new JLabel();
    
public Aplicacion(){
    super("Gestión de clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarClientes();
        crearInterfaz();
        registrarEventos();
        mostrarCliente();
        pack();
        setLocationRelativeTo(null);
}
private void cargarClientes() {
        clientes.add(new Cliente(1, "Ana García", 25, 120.5));
        clientes.add(new Cliente(2, "Luis Pérez", 34, 85.75));
        clientes.add(new Cliente(3, "Marta López", 42, 200.0));
    }

private void crearInterfaz() {
        // Como en Doble: etiquetas y campos en dos paneles separados.
        JPanel etiquetas = new JPanel(new GridLayout(4, 1, 0, 8));
        JPanel campos = new JPanel(new GridLayout(4, 1, 0, 8));
        agregarCampo(etiquetas, campos, "Número:", numeroField);
        agregarCampo(etiquetas, campos, "Nombre:", nombreField);
        agregarCampo(etiquetas, campos, "Edad:", edadField);
        agregarCampo(etiquetas, campos, "Puntos:", puntosField);

        JPanel datos = new JPanel(new BorderLayout(12, 0));
        datos.add(etiquetas, BorderLayout.WEST);
        datos.add(campos, BorderLayout.CENTER);

        JPanel navegacion = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        navegacion.add(anteriorButton);
        navegacion.add(posicionLabel);
        navegacion.add(siguienteButton);

        JPanel contenido = new JPanel(new BorderLayout(0, 16));
        contenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contenido.add(new JLabel("Clientes de la empresa"), BorderLayout.NORTH);
        contenido.add(datos, BorderLayout.CENTER);
        contenido.add(navegacion, BorderLayout.SOUTH);
        setContentPane(contenido);
    }
private void agregarCampo(JPanel etiquetas, JPanel campos,
                              String texto, JTextField campo) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setLabelFor(campo);
        campo.setEditable(false); // El enunciado pide visualizar los datos.
        etiquetas.add(etiqueta);
        campos.add(campo);
    }

private void registrarEventos() {
        anteriorButton.addActionListener(e -> {
            // También comprobamos el límite dentro del evento.
            if (indiceActual > 0) {
                indiceActual--;
                mostrarCliente();
            }
        });
        siguienteButton.addActionListener(e -> {
            if (indiceActual < clientes.size() - 1) {
                indiceActual++;
                mostrarCliente();
            }
        });
    }

private void mostrarCliente() {
        Cliente cliente = clientes.get(indiceActual);
        // Accedemos a los datos mediante getters, nunca a sus atributos.
        numeroField.setText(String.valueOf(cliente.getNumero()));
        nombreField.setText(cliente.getNombre());
        edadField.setText(String.valueOf(cliente.getEdad()));
        puntosField.setText(String.valueOf(cliente.getPuntos()));
        posicionLabel.setText((indiceActual + 1) + " de " + clientes.size());

        anteriorButton.setEnabled(indiceActual > 0);
        siguienteButton.setEnabled(indiceActual < clientes.size() - 1);
    }

public static void main(String[] args) {
        // Igual que en Doble: Swing se crea en el hilo de eventos.
        SwingUtilities.invokeLater(() -> new Aplicacion().setVisible(true));
    }
}
