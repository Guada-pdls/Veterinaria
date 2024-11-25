package veterinaria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaAdministracionSocios extends JDialog {

	private final static boolean esModal = true;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Create the dialog.
	 * @param logicaMascotas 
	 */
	public PantallaAdministracionSocios(LogicaSocios logicaSocios, LogicaMascotas logicaMascotas) {
		this.setModal(esModal);
		setBounds(100, 100, 920, 520);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		// Titulos de las columnas
		Object nombresColumnas[] = {"CI", "Nombre", "Apellido", "Telefono", "Departamento", "Direccion", "Fecha nacimiento", "Fecha inscripcion"};
		// Crear modelo con los titlos
		DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);
		// Carga los datos en el modelo
		for (Socio socio : logicaSocios.obtenerSocios()) {
		    Object[] fila = {
		        socio.getCi(),
		        socio.getNombre(),
		        socio.getApellido(),
		        socio.getTelefono(),
		        socio.getDepartamento(),
		        socio.getDireccion(),
		        socio.getFechaNacimiento(),
		        socio.getFechaInscripcion()
		    };
		    modelo.addRow(fila);
		}
				
		
		JTable table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 886, 379);
		contentPanel.add(scrollPane);
		scrollPane.setViewportView(table);
	
		JButton btnEliminarSocio = new JButton("Eliminar Socio");
		btnEliminarSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tomar num de fila seleccionada
				int filaSeleccionada = table.getSelectedRow();

				// Verificar si habia una fila seleccionada
				if (filaSeleccionada > -1) {
					// De esa fila tomar el contenido de la columna ci (la 0) 
					String ci = String.valueOf(modelo.getValueAt(filaSeleccionada, 0));
					
					boolean eliminado = logicaSocios.eliminarSocio(Integer.parseInt(ci));
					
					if (eliminado) {
						JOptionPane.showMessageDialog(null,"Socio eliminado exitosamente");
						modelo.removeRow(filaSeleccionada);
					} else {
						JOptionPane.showMessageDialog(null,"Hubo un error inesperado: No se ha podido eliminar el socio");
					}
					
				}else {
					// Dar un mensaje de error 
					JOptionPane.showMessageDialog(null,"Error: Debe seleccionar una fila de la tabla");
				}
			}
		});
		btnEliminarSocio.setBackground(new Color(210, 0, 0));
		btnEliminarSocio.setBounds(773, 421, 123, 21);
		contentPanel.add(btnEliminarSocio);
		
		JButton btnModificarSocio = new JButton("Modificar Socio");
		btnModificarSocio.setBackground(new Color(255, 255, 255));
		btnModificarSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Tomar num de fila seleccionada
				int filaSeleccionada = table.getSelectedRow();

				// Verificar si habia una fila seleccionada
				if (filaSeleccionada > -1) {
					// De esa fila tomar el contenido de la columna ci (la 0) 
					String ci = String.valueOf(modelo.getValueAt(filaSeleccionada, 0));
					
					// Traemos el socio 
					Socio socioAModificar = logicaSocios.buscarSocioPorCi(Integer.parseInt(ci));
					
					PantallaRegistroSocio pantModSoc = new PantallaRegistroSocio(PantallaAdministracionSocios.this, logicaSocios, modelo, socioAModificar, filaSeleccionada);
					pantModSoc.setVisible(true);
					
					
				}else {
					// Dar un mensaje de error 
					JOptionPane.showMessageDialog(null,"Error: Debe seleccionar una fila de la tabla");
				}
			}
		});
		btnModificarSocio.setBounds(633, 421, 130, 21);
		contentPanel.add(btnModificarSocio);
		
		JButton btnAgregarMascota = new JButton("Gestionar Mascotas");
		btnAgregarMascota.setBackground(new Color(255, 255, 255));
		btnAgregarMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada > -1) {
					String ci = String.valueOf(modelo.getValueAt(filaSeleccionada, 0));
					
					Socio socio = logicaSocios.buscarSocioPorCi(Integer.parseInt(ci));					
					
					PantallaAdministracionMascotas pantAdmMasc = new PantallaAdministracionMascotas(logicaMascotas, socio);
					pantAdmMasc.setVisible(true);
				}else {
					// Dar un mensaje de error 
					JOptionPane.showMessageDialog(null,"Error: Debe seleccionar una fila de la tabla");
				}
			}
		});
		btnAgregarMascota.setBounds(473, 421, 150, 21);
		contentPanel.add(btnAgregarMascota);
		
		JButton btnAgregarSocio = new JButton("Agregar Socio");
		btnAgregarSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaRegistroSocio pantRegSoc = new PantallaRegistroSocio(PantallaAdministracionSocios.this, logicaSocios, modelo, null, 0);
				pantRegSoc.setVisible(true);
				
			}
		});
		btnAgregarSocio.setBackground(new Color(128, 255, 0));
		btnAgregarSocio.setBounds(346, 421, 117, 21);
		contentPanel.add(btnAgregarSocio);
		
	}
}
