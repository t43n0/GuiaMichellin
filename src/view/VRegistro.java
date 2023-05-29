package view;

import javax.swing.JFrame;

import control.GuiaMichelinListener;
import db.RestaurantesPersistencia;
import model.Restaurante;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class VRegistro extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	public static final String GUARDAR = "Modificar datos";
	public static final String LIMPIAR = "Limpiar datos";
	private static final String SALIR = "Salir";
	private static final String MODIFICACION = "Modificación de Restaurante";
	private static final String REGISTRO = "Registro de Restaurante";
	private static final String CONSULTAR = "Consulta de Restaurantes";
	private static final String MANTENIMIENTO = "Mantenimiento Restaurantes";
	private JMenuItem menuItemSalir;
	private JMenuItem menuItemConsulta;
	private JMenuItem menuItemRegistro;
	private JMenuItem menuItemModificacion;
	private JTextField campoNombre;
	private JTextField campoCiudad;
	private JTextField campoDireccion;
	private JTextField campoPrecioMin;
	private JTextField campoPrecioMax;
	private JTextField campoTelefono;
	private JTextField campoWeb;
	private JButton btnGuardarDatos;
	private JButton btnLimpiarDatos;
	private JComboBox<String> cbRegion;
	private JComboBox<String> cbCocina;
	private JSpinner spnDistincion;

	public VRegistro() {
		super();
		init();
	}

	private void init() {
		getContentPane().setLayout(null);	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel lblRegistrarRestaurante = new JLabel("Registrar Restaurante");
		lblRegistrarRestaurante.setForeground(Color.GRAY);
		lblRegistrarRestaurante.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblRegistrarRestaurante.setBounds(10, 10, 258, 53);
		getContentPane().add(lblRegistrarRestaurante);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(90, 73, 95, 30);
		getContentPane().add(lblNombre);
		
		JLabel lblCocina = new JLabel("Cocina:");
		lblCocina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCocina.setBounds(482, 78, 75, 21);
		getContentPane().add(lblCocina);
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegion.setBounds(90, 138, 56, 21);
		getContentPane().add(lblRegion);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCiudad.setBounds(333, 142, 64, 13);
		getContentPane().add(lblCiudad);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(90, 209, 83, 13);
		getContentPane().add(lblDireccion);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistincion.setBounds(90, 271, 76, 21);
		getContentPane().add(lblDistincion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(90, 352, 75, 13);
		getContentPane().add(lblTelefono);
		
		JLabel lblPrecioMin = new JLabel("Precio mínimo:");
		lblPrecioMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMin.setBounds(303, 275, 125, 13);
		getContentPane().add(lblPrecioMin);
		
		JLabel lblPrecioMax = new JLabel("Precio maximo:");
		lblPrecioMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMax.setBounds(521, 275, 114, 13);
		getContentPane().add(lblPrecioMax);
		
		JLabel lblWeb = new JLabel("Web:");
		lblWeb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeb.setBounds(372, 352, 45, 13);
		getContentPane().add(lblWeb);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(183, 81, 266, 19);
		getContentPane().add(campoNombre);
		campoNombre.setColumns(10);
		
		campoCiudad = new JTextField();
		campoCiudad.setBounds(407, 141, 296, 19);
		getContentPane().add(campoCiudad);
		campoCiudad.setColumns(10);
		
		campoDireccion = new JTextField();
		campoDireccion.setBounds(183, 208, 520, 19);
		getContentPane().add(campoDireccion);
		campoDireccion.setColumns(10);
		
		campoPrecioMin = new JTextField();
		campoPrecioMin.setBounds(415, 274, 75, 19);
		getContentPane().add(campoPrecioMin);
		campoPrecioMin.setColumns(10);
		
		campoPrecioMax = new JTextField();
		campoPrecioMax.setBounds(628, 274, 75, 19);
		getContentPane().add(campoPrecioMax);
		campoPrecioMax.setColumns(10);
		
		campoTelefono = new JTextField();
		campoTelefono.setBounds(175, 351, 162, 19);
		getContentPane().add(campoTelefono);
		campoTelefono.setColumns(10);
		
		campoWeb = new JTextField();
		campoWeb.setBounds(427, 351, 276, 19);
		getContentPane().add(campoWeb);
		campoWeb.setColumns(10);
		
		spnDistincion = new JSpinner();
		spnDistincion.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spnDistincion.setBounds(206, 274, 35, 18);
		getContentPane().add(spnDistincion);
		JFormattedTextField tf = ((JSpinner.DefaultEditor) spnDistincion.getEditor()).getTextField();
        tf.setEditable(false);
		
		cbRegion = new JComboBox<String>();
		cbRegion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbRegion.setModel(new DefaultComboBoxModel<String>(new String[] {"Andalucía", 
				"Aragón", "Asturias", "Islas Baleares", "Cantabria", "Islas Canarias", 
				"Castilla - La Mancha", "Castilla y León", "Cataluña", "Galicia", 
				"Extremadura", "Madrid", "Murcia", "Navarra", "País Vasco", "La Rioja", 
				"Comunidad Valenciana"}));
		cbRegion.setBounds(156, 140, 167, 19);
		getContentPane().add(cbRegion);
		
		cbCocina = new JComboBox<String>();
		cbCocina.setModel(new DefaultComboBoxModel<String>(new String[] {"Creativa", "Moderna", 
				"Tradicional", "Regional", "Fusión"}));
		cbCocina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbCocina.setBounds(542, 80, 161, 23);
		getContentPane().add(cbCocina);
		
		btnGuardarDatos = new JButton(GUARDAR);
		btnGuardarDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardarDatos.setBounds(206, 429, 125, 30);
		getContentPane().add(btnGuardarDatos);
		
		btnLimpiarDatos = new JButton(LIMPIAR);
		btnLimpiarDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimpiarDatos.setBounds(463, 429, 125, 30);
		getContentPane().add(btnLimpiarDatos);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu(MANTENIMIENTO);
		menuBar.add(menu);
		
		menuItemConsulta = new JMenuItem(CONSULTAR);
		menu.add(menuItemConsulta);
		
		menuItemRegistro = new JMenuItem(REGISTRO);
		menu.add(menuItemRegistro);
		
		menuItemModificacion = new JMenuItem(MODIFICACION);
		menu.add(menuItemModificacion);
		
		menuItemSalir = new JMenuItem(SALIR);
		menuBar.add(menuItemSalir);
		
		setSize(ANCHO, ALTO);
		centrarVentana();
	}

	private void centrarVentana() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		Dimension ventana = new Dimension(ANCHO, ALTO);               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}

	public void hacerVisible(boolean b) {
		setVisible(b);
	}
	
	public void limpiarDatos() {
		campoNombre.setText("");
		campoCiudad.setText("");
		campoDireccion.setText("");
		campoPrecioMin.setText("");
		campoPrecioMax.setText("");
		campoTelefono.setText("");
		campoWeb.setText("");
		cbCocina.setSelectedIndex(0);
		cbRegion.setSelectedIndex(0);
		spnDistincion.setValue(1);
	}
	
	public Restaurante guardarRestaurante() {
		String nombre = campoNombre.getText();
		String ciudad = campoCiudad.getText();
		String precioMinStr = campoPrecioMin.getText();
		String precioMaxStr = campoPrecioMax.getText();
		if(comprobarDatos(nombre, ciudad, precioMaxStr, precioMinStr)) {
			String cocina = (String) cbCocina.getSelectedItem();
			String region = (String) cbRegion.getSelectedItem();
			String direccion = campoDireccion.getText();
			int distincion = (int) spnDistincion.getValue();
			float precioMin = Float.parseFloat(precioMinStr);
			float precioMax = Float.parseFloat(precioMaxStr);
			String telefono = campoTelefono.getText();
			String web = campoWeb.getText();
			Restaurante rt = new Restaurante(0 , nombre, region, ciudad, distincion,
					direccion, precioMin, precioMax, cocina, telefono, web);
			JOptionPane.showMessageDialog(null, 
					"Se ha registrado el restaurante con éxito",
					"Resultado de Operación", JOptionPane.INFORMATION_MESSAGE);
			return rt;
		} else {
			limpiarDatos();
			return null;
		}
	}

	private boolean comprobarDatos(String nombre, String ciudad, String precioMaxStr, String precioMinStr) {
		boolean esCorrecto = false;
		if(yaExisteNombre(nombre)) {
			JOptionPane.showMessageDialog(null, 
					"Ya existe un restaurante con ese nombre",
					"Resultado de operación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(nombre.isBlank()) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar en blanco",
					"Error de datos",
					JOptionPane.ERROR_MESSAGE);
		} else if(ciudad.isBlank()) {
			JOptionPane.showMessageDialog(null, "La ciudad no puede estar en blanco",
					"Error de datos",
					JOptionPane.ERROR_MESSAGE);
		} else if(comprobarNumerico(precioMaxStr) && comprobarNumerico(precioMinStr)) {
			if(Float.parseFloat(precioMinStr) > Float.parseFloat(precioMaxStr)) {
				JOptionPane.showMessageDialog(null, "El precio min no puede ser mayor que el precio max",
						"Error de datos",
						JOptionPane.ERROR_MESSAGE);
			} else {
				esCorrecto = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "El valor de precio Min y precio Max deben ser numericos",
					"Error de datos",
					JOptionPane.ERROR_MESSAGE);
		}
		return esCorrecto;
	}

	private boolean yaExisteNombre(String nombre) {
		RestaurantesPersistencia rP = new RestaurantesPersistencia(null);
		ArrayList<Restaurante> arrRest = rP.cargarDatosBDTabla("");
		for(Restaurante rt : arrRest) {
			if(rt.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}

	private boolean comprobarNumerico(String precioStr) {
		boolean esNumerico = false;
		if(!precioStr.isBlank()) {
			try {
				@SuppressWarnings("unused")
				float valor = Float.parseFloat(precioStr);
				esNumerico = true;
			} catch (NumberFormatException e) {
				
			}
		}
		return esNumerico;
	}

	public void setListener(GuiaMichelinListener l) {
		menuItemConsulta.addActionListener(l);
		menuItemRegistro.addActionListener(l);
		menuItemModificacion.addActionListener(l);
		menuItemSalir.addActionListener(l);
		btnGuardarDatos.addActionListener(l);
		btnLimpiarDatos.addActionListener(l);
	}
}
