package veterinaria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dialog;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class PantallaRegistroSocio extends JDialog {

	private final static boolean esModal = true;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldFechaInscripcion;

	public PantallaRegistroSocio(Dialog padre, LogicaSocios logicaSocios, DefaultTableModel modelo, Socio socioAModificar, int fila) {

        super(padre, esModal);
	    setBounds(100, 100, 500, 422);
	    getContentPane().setLayout(new BorderLayout());
	    contentPanel.setBackground(Color.LIGHT_GRAY);
	    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    getContentPane().add(contentPanel, BorderLayout.CENTER);
	    contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
	    		FormSpecs.UNRELATED_GAP_COLSPEC,
	    		ColumnSpec.decode("231px"),
	    		FormSpecs.UNRELATED_GAP_COLSPEC,
	    		ColumnSpec.decode("180px:grow"),},
	    	new RowSpec[] {
	    		RowSpec.decode("43px"),
	    		RowSpec.decode("20px"),
	    		FormSpecs.PARAGRAPH_GAP_ROWSPEC,
	    		RowSpec.decode("20px"),
	    		FormSpecs.PARAGRAPH_GAP_ROWSPEC,
	    		RowSpec.decode("22px"),
	    		FormSpecs.PARAGRAPH_GAP_ROWSPEC,
	    		FormSpecs.DEFAULT_ROWSPEC,
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
	    lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblNombre, "2, 2, left, center");

	    JTextField textFieldNombre = new JTextField();
	    contentPanel.add(textFieldNombre, "4, 2, fill, fill");

	    JLabel lblApellido = new JLabel("Apellido");
	    lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblApellido, "2, 4, left, center");

	    JTextField textFieldApellido = new JTextField();
	    contentPanel.add(textFieldApellido, "4, 4, fill, fill");

	    JLabel lblCedula = new JLabel("Cedula  (Sin puntos ni guion)");
	    lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblCedula, "2, 6, left, center");

	    JTextField textFieldCedula = new JTextField();
	    contentPanel.add(textFieldCedula, "4, 6, fill, fill");
	    	    
	    JLabel lblDireccion = new JLabel("Direccion");
	    lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblDireccion, "2, 8, left, center");

	    JTextField textFieldDireccion = new JTextField();
	    contentPanel.add(textFieldDireccion, "4, 8, fill, fill");

	    JLabel lblFechaNac = new JLabel("Fecha Nacimiento (aaaa/mm/dd)");
	    lblFechaNac.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblFechaNac, "2, 10, left, center");

	    JTextField textFieldFechaNac = new JTextField();
	    contentPanel.add(textFieldFechaNac, "4, 10, fill, fill");
	    
	    JLabel lblDepartamento = new JLabel("Departamento");
	    lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblDepartamento, "2, 12, left, center");

	    JComboBox<Departamento> comboBoxDepartamento = new JComboBox<>(Departamento.values());
	    contentPanel.add(comboBoxDepartamento, "4, 12, fill, fill");

	    JLabel lblTelefono = new JLabel("Telefono");
	    lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblTelefono, "2, 14, left, center");

	    JTextField textFieldTelefono = new JTextField();
	    contentPanel.add(textFieldTelefono, "4, 14, fill, fill");
	    
	    JLabel lblFechaInscripcion = new JLabel("Fecha Inscripcion (aaaa-mm-dd)");
	    lblFechaInscripcion.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPanel.add(lblFechaInscripcion, "2, 16, left, center");
	    
	    textFieldFechaInscripcion = new JTextField();
	    contentPanel.add(textFieldFechaInscripcion, "4, 16, fill, default");
	   

	    JPanel buttonPane = new JPanel();
	    buttonPane.setBackground(Color.LIGHT_GRAY);
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		
		
		

	    JButton okRegistrar = new JButton("Registrar");
	    okRegistrar.setActionCommand("OK");

	    JButton okModificar = new JButton("Modificar");
	    okModificar.setActionCommand("Modificar");
	    okModificar.setVisible(false);  // Oculto inicialmente

	    okRegistrar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String nombre = textFieldNombre.getText();
	            String apellido = textFieldApellido.getText();
	            String direccion = textFieldDireccion.getText();
	            String cedulaTexto = textFieldCedula.getText();
	            String fechaNacimiento = textFieldFechaNac.getText();
	            Departamento departamento = (Departamento) comboBoxDepartamento.getSelectedItem();
	            String telefonoTexto = textFieldTelefono.getText();
	            String fechaInscripcion = textFieldFechaInscripcion.getText();
	            

	            // Convierto ci y telefono a enteros, manejando exepciones
	            int ci;
	            
	            try {
	                ci = Integer.parseInt(cedulaTexto);
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: Ci debe ser un numero.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            int telefono;
	            
	            try {
	                telefono = Integer.parseInt(telefonoTexto);
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: El telefono debe ser un número.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	                
	            
	            String error = logicaSocios.verificarDatosSocio(nombre, apellido, direccion, ci, fechaNacimiento, departamento, telefonoTexto, fechaInscripcion);
	            
	            if ("ci duplicada".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: Ci ya registrada.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if ("nombre".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: nombre invalido.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if ("apellido".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: apellido invalido.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if ("direccion".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: direccion invalida.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if ("fechaNac".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: Ingrese una fecha de nacimiento valida.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if ("departamento".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: Departamento invalido.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if ("telefono".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: Telefono invalido.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if ("fechaInscripcion".equals(error)) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error: Fecha de Inscripcion invalida.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } 
	            
	            if (!error.equals("")) {
	                JOptionPane.showMessageDialog(PantallaRegistroSocio.this, "Error en el campo: " + error, "Error de Registro", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	   
	                Socio nuevoSocio = logicaSocios.registrarSocio(
		            	    nombre, apellido, direccion, ci, fechaNacimiento,
		            	    departamento, telefono, fechaInscripcion
		            	    );
	 
	        		            JOptionPane.showMessageDialog(null, "Socio registrado");  	
	        		        
	        		            Object[] fila = {
	        		                nuevoSocio.getCi(),
	        		                nuevoSocio.getNombre(),
	        		                nuevoSocio.getApellido(),
	        		                nuevoSocio.getTelefono(),
	        		                nuevoSocio.getDepartamento(),
	        		                nuevoSocio.getDireccion(),
	        		                nuevoSocio.getFechaNacimiento(),
	        		                nuevoSocio.getFechaInscripcion()
	        		            };
	        		            modelo.addRow(fila);

	        		            dispose();
	        		        }
	    
	        		    });
	    okModificar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Código para modificar el socio existente
	            String nombre = textFieldNombre.getText();
	            String apellido = textFieldApellido.getText();
	            String direccion = textFieldDireccion.getText();
	            String fechaNacimiento = textFieldFechaNac.getText();
	            Departamento departamento = (Departamento) comboBoxDepartamento.getSelectedItem();
	            String telefono = textFieldTelefono.getText();
	            String fechaInscripcion = textFieldFechaInscripcion.getText();

	            boolean modificado = logicaSocios.modificarSocio(
	            	    socioAModificar.getCi(), nombre, apellido, direccion, fechaNacimiento, (Departamento) comboBoxDepartamento.getSelectedItem(), Integer.parseInt(telefono), fechaInscripcion
	            	);
	            
	            if (modificado) {
	                JOptionPane.showMessageDialog(null, "Socio modificado");  
	                
	                // Actualiza la fila en el modelo
	                modelo.setValueAt(socioAModificar.getCi(), fila, 0);
	                modelo.setValueAt(nombre, fila, 1);
	                modelo.setValueAt(apellido, fila, 2);
	                modelo.setValueAt(telefono, fila, 3);
	                modelo.setValueAt(departamento, fila, 4);
	                modelo.setValueAt(direccion, fila, 5);
	                modelo.setValueAt(fechaNacimiento, fila, 6);

	                dispose();
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al modificar el socio");
	            }
	        }
	    });

	    buttonPane.add(okRegistrar);
	    buttonPane.add(okModificar);

	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setActionCommand("Cancel");
	    btnCancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	        }
	    });
	    buttonPane.add(btnCancelar);

	    if (socioAModificar == null) {
	    	lblTitulo.setText("Registro de Socio");
	    } else { 
	    	lblTitulo.setText("Modificacion de Socio");

	        // Prellenar los campos con los datos del socio
	        textFieldNombre.setText(socioAModificar.getNombre());
	        textFieldApellido.setText(socioAModificar.getApellido());
	        textFieldCedula.setText(String.valueOf(socioAModificar.getCi()));
	        textFieldCedula.setEnabled(false); // Deshabilitar CI
	        textFieldDireccion.setText(socioAModificar.getDireccion());
	        textFieldFechaNac.setText(socioAModificar.getFechaNacimiento());
	        comboBoxDepartamento.setSelectedItem(socioAModificar.getDepartamento());
	        textFieldTelefono.setText(String.valueOf(socioAModificar.getTelefono()));
	        textFieldFechaInscripcion.setText(socioAModificar.getFechaInscripcion());
	        textFieldFechaInscripcion.setEnabled(false);

	        // Mostrar botón de Modificar y ocultar el de Registrar
	        okRegistrar.setVisible(false);
	        okModificar.setVisible(true);
	    }
	}

}
