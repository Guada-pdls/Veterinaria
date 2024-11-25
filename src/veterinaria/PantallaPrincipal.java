package veterinaria;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private ConexionBD conexion = new ConexionBD();
    private LogicaMascotas logicaMascotas;
    private LogicaSocios logicaSocios;
    private LogicaConsultas logicaConsultas;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaPrincipal frame = new PantallaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

     public PantallaPrincipal() {
        // Inicializar las lógicas con la conexión
    	
    
    	logicaMascotas = new LogicaMascotas(conexion.estaConectado(), conexion.getSentencia(), conexion.getConexion());
        logicaSocios = new LogicaSocios(conexion.estaConectado(), conexion.getSentencia());
//      logicaConsultas = new LogicaConsultas(conexion.estaConectado(), conexion.getSentencia());
       
        
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
        contentPane.setLayout(null);
                                                                            
        JLabel lblVet = new JLabel("Veterinaria");
        lblVet.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblVet.setHorizontalAlignment(SwingConstants.CENTER);
        lblVet.setBounds(224, 104, 138, 13);
        contentPane.add(lblVet);
                                                                
		JButton btnAdmSoc = new JButton("Administrar Socios");
		btnAdmSoc.setBackground(new Color(255, 255, 255));
		btnAdmSoc.setBounds(201, 159, 183, 21);
		btnAdmSoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaAdministracionSocios pantAdmSoc = new PantallaAdministracionSocios(logicaSocios, logicaMascotas);
				pantAdmSoc.setVisible(true);
			}
     	});
		contentPane.add(btnAdmSoc);
		
		JButton btnRegCon = new JButton("Agregar Consulta");
		btnRegCon.setBackground(new Color(255, 255, 255));
		btnRegCon.setBounds(201, 190, 183, 21);
		btnRegCon.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Mostrar mensaje indicando que la funcionalidad está en construcción
		        JOptionPane.showMessageDialog(null, "En construcción", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		contentPane.add(btnRegCon);

	    
	    JButton btnSalir = new JButton("Salir");
	    btnSalir.setBounds(419, 301, 138, 23);
	    btnSalir.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0); 
	        }
	    });
	   
	    contentPane.add(btnSalir);
	    
	    JButton btnAdministracionFuncionarios = new JButton("Administrar funcionarios");
	    btnAdministracionFuncionarios.setBackground(new Color(255, 255, 255));
	    btnAdministracionFuncionarios.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "En construcción", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
	    btnAdministracionFuncionarios.setBounds(201, 221, 183, 21);
	    contentPane.add(btnAdministracionFuncionarios);
	    
	    if(!conexion.estaConectado()) {
	    	JLabel lblAvisoConexion = new JLabel("No conectado a la base de datos, guardando en la memoria");
	    	lblAvisoConexion.setBounds(39, 305, 357, 14);

	    	lblAvisoConexion.setForeground(java.awt.Color.RED);
	    	contentPane.add(lblAvisoConexion);
                                                 
        }
    }
}
