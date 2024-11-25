package veterinaria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAdministracionMascotas extends JDialog {

	private final static boolean esModal = true;
	
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel modelo;

    public PantallaAdministracionMascotas(LogicaMascotas logicaMascotas, Socio socio) {
        this.setModal(esModal);
        setTitle("Administración de Mascotas de " + socio.getNombre());
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Títulos de las columnas para la tabla de mascotas
        Object[] nombresColumnas = {"ID", "Nombre", "Edad", "Tipo", "Raza", "Especie", "Enfermedad"};
        DefaultTableModel modelo = new DefaultTableModel(nombresColumnas, 0);

        // Cargar los datos de las mascotas del socio en el modelo
        for (Mascota mascota : logicaMascotas.obtenerMascotas(socio)) {
            Object[] fila = {
                mascota.getId(),
                mascota.getNombre(),
                mascota.getEdad(),
                mascota.getTipoAnimal(),
                mascota.getRaza(),
                mascota.getEspecie(),
                mascota.getEnfermedad()
            };
            modelo.addRow(fila);
        }

        table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 760, 350);
        contentPanel.add(scrollPane);
        scrollPane.setViewportView(table);

        // Botón para agregar nueva mascota
        JButton btnAgregarMascota = new JButton("Agregar Mascota");
        btnAgregarMascota.setBackground(new Color(128, 255, 0));
        btnAgregarMascota.setBounds(50, 380, 150, 30);
        btnAgregarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaRegistroMascota pantRegMasc = new PantallaRegistroMascota(PantallaAdministracionMascotas.this, logicaMascotas, modelo, null, socio, 0);
                pantRegMasc.setVisible(true);
            }
        });
        contentPanel.add(btnAgregarMascota);

        // Botón para modificar mascota seleccionada
        JButton btnModificarMascota = new JButton("Modificar Mascota");
        btnModificarMascota.setBackground(new Color(255, 255, 255));
        btnModificarMascota.setBounds(220, 380, 150, 30);
        btnModificarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada > -1) {
                    int id = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
                    Mascota mascotaAModificar = logicaMascotas.buscarMascotaPorId(id);
                    System.out.println(id + mascotaAModificar.getNombre());            
                    PantallaRegistroMascota pantModMasc = new PantallaRegistroMascota(PantallaAdministracionMascotas.this, logicaMascotas, modelo, mascotaAModificar, socio, filaSeleccionada);
                    pantModMasc.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una mascota de la tabla");
                }
            }
        });
        contentPanel.add(btnModificarMascota);

        // Botón para eliminar mascota seleccionada
        JButton btnEliminarMascota = new JButton("Eliminar Mascota");
        btnEliminarMascota.setBackground(new Color(225, 0, 0));
        btnEliminarMascota.setBounds(390, 380, 150, 30);
        btnEliminarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada > -1) {
                    int id = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
                    boolean eliminado = logicaMascotas.eliminarMascota(id, socio);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Mascota eliminada exitosamente");
                        modelo.removeRow(filaSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar la mascota");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una mascota de la tabla");
                }
            }
        });
        contentPanel.add(btnEliminarMascota);

        // Botones de OK y Cancel para cerrar
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());
        buttonPane.add(okButton);
    }
}
