package veterinaria;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.DefaultComboBoxModel;

public class PantallaRegistroMascota extends JDialog {

	private final static boolean esModal = true;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public PantallaRegistroMascota(Dialog padre, LogicaMascotas logicaMascotas, DefaultTableModel modelo, Mascota mascotaAModificar, Socio duenio, int fila) {
	    super(padre, esModal);
		setBounds(100, 100, 580, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				ColumnSpec.decode("240px"),},
			new RowSpec[] {
				RowSpec.decode("43px"),
				RowSpec.decode("20px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),}));
	    
	    JLabel lblTitulo = new JLabel("");
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblTitulo, "4, 1");

	    JLabel lblNombre = new JLabel("Nombre");
	    lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
	    contentPanel.add(lblNombre, "2, 2, fill, center");

	    JTextField textFieldNombre = new JTextField();
	    contentPanel.add(textFieldNombre, "4, 2, fill, fill");

	    JLabel lblRaza = new JLabel("Raza");
	    lblRaza.setHorizontalAlignment(SwingConstants.LEFT);
	    contentPanel.add(lblRaza, "2, 4, fill, center");

	    JTextField textFieldRaza = new JTextField();
	    contentPanel.add(textFieldRaza, "4, 4, fill, fill");

	    JLabel lblEspecie = new JLabel("Especie");
	    lblEspecie.setHorizontalAlignment(SwingConstants.LEFT);
	    contentPanel.add(lblEspecie, "2, 6, fill, center");

	    JTextField textFieldEspecie = new JTextField();
	    contentPanel.add(textFieldEspecie, "4, 6, fill, fill");

	    JLabel lblEdad = new JLabel("Edad");
	    lblEdad.setHorizontalAlignment(SwingConstants.LEFT);
	    contentPanel.add(lblEdad, "2, 8, fill, center");

	    JTextField textFieldEdad = new JTextField();
	    contentPanel.add(textFieldEdad, "4, 8, fill, fill");
	    
	    	    JLabel lblTipoAnim = new JLabel("Tipo Animal");
	    	    lblTipoAnim.setHorizontalAlignment(SwingConstants.LEFT);
	    	    contentPanel.add(lblTipoAnim, "2, 10, fill, center");
	    
	    	    JComboBox<Departamento> comboBoxTipoAnim = new JComboBox<>(Departamento.values());
	    	    comboBoxTipoAnim.setModel(new DefaultComboBoxModel(TipoAnimal.values()));
	    	    contentPanel.add(comboBoxTipoAnim, "4, 10, fill, fill");
	    
	    	    JLabel lblEnfermedad = new JLabel("Enfermedad");
	    	    lblEnfermedad.setHorizontalAlignment(SwingConstants.LEFT);
	    	    contentPanel.add(lblEnfermedad, "2, 12, fill, center");
	    
	    	    JTextField textFieldEnfermedad = new JTextField();
	    	    contentPanel.add(textFieldEnfermedad, "4, 12, fill, fill");
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		    JButton okRegistrar = new JButton("Registrar");
		    okRegistrar.setActionCommand("OK");

		    JButton okModificar = new JButton("Modificar");
		    okModificar.setActionCommand("Modificar");
		    okModificar.setVisible(false);  // Oculto inicialmente

		    if (mascotaAModificar == null) {
	            lblTitulo.setText("Registro de Mascota");
	        } else {
	            lblTitulo.setText("Modificación de Mascota");

	            // Prellenar los campos con los datos de la mascota
	            textFieldNombre.setText(mascotaAModificar.getNombre());
	            textFieldNombre.setEnabled(false);
	            comboBoxTipoAnim.setSelectedItem(mascotaAModificar.getTipoAnimal());
	            textFieldRaza.setText(mascotaAModificar.getRaza());
	            textFieldEspecie.setText(mascotaAModificar.getEspecie());
	            textFieldEdad.setText(String.valueOf(mascotaAModificar.getEdad()));

	            // Ocultar el botón de Registro y mostrar el de Modificar
	            okRegistrar.setVisible(false);
	            okModificar.setVisible(true);
	        }

		    
		    // Accion boton registro
		    okRegistrar.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	String nombre = textFieldNombre.getText();
		            String raza = textFieldRaza.getText();
		            String especie = textFieldEspecie.getText();
		            String edadString = textFieldEdad.getText();
		            TipoAnimal tipoAnim = (TipoAnimal) comboBoxTipoAnim.getSelectedItem();
		            String enfermedad = textFieldEnfermedad.getText();
		            
		            // verifico si algun campo esta vacio
		            if (nombre.isEmpty() || especie.isEmpty() || edadString.isEmpty() ||
		                tipoAnim == null) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: Todos los campos deben estar completos.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Convierto edad a entero, manejando exepciones
		            int edad;
		            try {
		                edad = Integer.parseInt(edadString);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: edad debe ser un numero.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            String error = logicaMascotas.validarDatosMascota(edad, nombre, tipoAnim, raza, especie, enfermedad, duenio);
		            
		            if ("edad".equals(error)) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: edad invalida.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            } else if ("nombre".equals(error)) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: nombre invalido.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            } else if ("tipoAnimal".equals(error)) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: tipo de animal invalido.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            } else if ("especie".equals(error)) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: especie invalida.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            } else if ("duenio".equals(error)) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: socio invalido.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            } 
		            
		            if (!error.equals("") && !error.equals("nombre duplicado")) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error en el campo: " + error, "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

					if ("nombre duplicado".equals(error)) {
					    int opcion = JOptionPane.showConfirmDialog(
					        PantallaRegistroMascota.this,
					        "Ya tienes una mascota registrada con ese nombre. ¿Deseas continuar?",
					        "Advertencia de Registro",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.WARNING_MESSAGE
					    );
					    
					    if (opcion == JOptionPane.NO_OPTION) {
					        // El usuario eligió cancelar, detener la ejecución
					        return;
					    }
					}		   
					
	                Mascota nuevaMasc = logicaMascotas.registrarMascota(edad, nombre, tipoAnim, raza, especie, enfermedad, duenio);
	                
		            JOptionPane.showMessageDialog(null, "Mascota registrada");  	
		            
		            Object[] fila = {
		                nuevaMasc.getId(),
		                nuevaMasc.getNombre(),
		                nuevaMasc.getEdad(),
		                nuevaMasc.getTipoAnimal(),
		                nuevaMasc.getRaza(),
		                nuevaMasc.getEspecie(),
		                nuevaMasc.getEnfermedad()
		            };
		            modelo.addRow(fila);

		            dispose();
		        }
		    
	    	});
		 // Acción del botón de modificación
	        okModificar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String nombre = textFieldNombre.getText();
	                TipoAnimal tipoAnimal = (TipoAnimal) comboBoxTipoAnim.getSelectedItem();
	                String raza = textFieldRaza.getText();
	                String especie = textFieldEspecie.getText();
	                String edadString = textFieldEdad.getText();
	                
	                // Convierto edad a entero, manejando exepciones
		            int edad;
		            try {
		                edad = Integer.parseInt(edadString);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Error: edad debe ser un numero.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            
		            boolean modificado = logicaMascotas.modificarMascota(mascotaAModificar.getId(), edad, nombre, tipoAnimal, raza, especie, edadString, duenio);

		            if (modificado) {
		            	// Modificar los datos en la fila del modelo existente
		            	modelo.setValueAt(nombre, fila, 1);
		            	modelo.setValueAt(edad, fila, 2);		            	
		            	modelo.setValueAt(tipoAnimal, fila, 3);
		            	modelo.setValueAt(raza, fila, 4);
		            	modelo.setValueAt(especie, fila, 5);
		            	
		            	JOptionPane.showMessageDialog(null, "Mascota registrada");  	
		            } else {
		                JOptionPane.showMessageDialog(PantallaRegistroMascota.this, "Ha ocurrido un error inesperado: no se ha podido modificar la mascota.", "Error de Modificacion", JOptionPane.ERROR_MESSAGE);
		            }

	                dispose();
	            }
	        });

	        buttonPane.add(okRegistrar);
	        buttonPane.add(okModificar);

	        JButton cancelButton = new JButton("Cancelar");
	        cancelButton.setActionCommand("Cancel");
	        cancelButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	        buttonPane.add(cancelButton);
		}
	}

}
