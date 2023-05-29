package view;

import javax.swing.JFrame;

import control.GuiaMichelinListener;
import db.RestaurantesPersistencia;
import model.Restaurante;

import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class VConsulta extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	public static final String CONSULTAR = "Consultar";
	public static final String ELIMINAR = "Eliminar";
	public static final String SALIR = "Salir";
	public static final String MODIFICACION = "Modificación de Restaurante";
	public static final String REGISTRO = "Registro de Restaurante";
	public static final String CONSULTA = "Consulta de Restaurantes";
	private static final String MANTENIMIENTO = "Mantenimiento Restaurantes";
	private DefaultTableModel modeloTabla;
	private JMenuItem menuItemSalir;
	private JMenuItem menuItemConsulta;
	private JMenuItem menuItemRegistro;
	private JMenuItem menuItemModificacion;
	private JButton btnConsultar;
	private JComboBox<String> cbRegion;
	private JComboBox<String> cbDistincion;
	private JTable table;
	private JLabel lblListadoRestaurantes;
	private JScrollPane scrpContenedor;
	private JButton btnEliminar;

	public VConsulta() {
		super();
		init();
	}

	private void init() {
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Consulta de Restaurantes");
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(10, 10, 318, 56);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtro:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(106, 76, 112, 21);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Regi\u00F3n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(142, 107, 51, 21);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Distinci\u00F3n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(416, 107, 76, 21);
		getContentPane().add(lblNewLabel_3);
		
		cbRegion = new JComboBox<String>();
		RestaurantesPersistencia rP = new RestaurantesPersistencia(this);
		DefaultComboBoxModel<String> dcbm = rP.cargarCombo();
		cbRegion.setModel(dcbm);
		cbRegion.setBounds(203, 109, 173, 19);
		getContentPane().add(cbRegion);
		
		cbDistincion = new JComboBox<String>();
		cbDistincion.setModel(new DefaultComboBoxModel<String>(new String[] {"TODAS", "1 estrella", 
				"2 estrellas", "3 estrellas"}));
		cbDistincion.setBounds(492, 109, 146, 19);
		getContentPane().add(cbDistincion);
		
		btnConsultar = new JButton(CONSULTAR);
		btnConsultar.setBounds(646, 140, 103, 21);
		getContentPane().add(btnConsultar);
		
		lblListadoRestaurantes = new JLabel("Listado de Restaurantes");
		lblListadoRestaurantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListadoRestaurantes.setBounds(10, 140, 150, 21);
		getContentPane().add(lblListadoRestaurantes);
		
		scrpContenedor = new JScrollPane();
		scrpContenedor.setBounds(38, 171, 711, 323);
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpContenedor.setViewportView(table);
		
		btnEliminar = new JButton(ELIMINAR);
		btnEliminar.setBounds(691, 506, 85, 21);
		getContentPane().add(btnEliminar);
		configurarTabla();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu(MANTENIMIENTO);
		menuBar.add(menu);
		
		menuItemConsulta = new JMenuItem(CONSULTA);
		menu.add(menuItemConsulta);
		
		menuItemRegistro = new JMenuItem(REGISTRO);
		menu.add(menuItemRegistro);
		
		menuItemModificacion = new JMenuItem(MODIFICACION);
		menu.add(menuItemModificacion);
		
		menuItemSalir = new JMenuItem(SALIR);
		menuBar.add(menuItemSalir);

		setSize(ANCHO, ALTO);
		centrarVentana();
		cargarTabla(new ArrayList<Restaurante>());
	}
	
	public ComboBoxModel<String> cargarCombo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCbRegion() {
		return cbRegion.getSelectedItem();
	}
	
	public Object getCbDistincion() {
		return cbDistincion.getSelectedItem();
	}
	
	public void configurarTabla() {
		modeloTabla = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			@Override
			public void setValueAt(Object aValue, int row, int column) {
				
			}
		};
		modeloTabla.addColumn("NOMBRE");
		modeloTabla.addColumn("CIUDAD");
		modeloTabla.addColumn("DISTINCÍON");
		modeloTabla.addColumn("COCINA");
		modeloTabla.addColumn("PRECIO");
		table.setModel(modeloTabla);
	}
	
	public void cargarTabla(ArrayList<Restaurante> rts) {
		modeloTabla.setRowCount(0);
		
		Object [] fila = new Object[5];
		
		for (Restaurante rt : rts) {
			fila[0] = rt.getNombre();
			fila[1] = rt.getCiudad();
			fila[2] = contarEstrellas(rt.getDistincion());
			fila[3] = rt.getCocina();
			fila[4] = (rt.getPrecioMin() + " - " + rt.getPrecioMax());
			
			modeloTabla.addRow(fila);
		}
	}

	public String contarEstrellas(int distincion) {
		String estrellas = "";
		for(int i = 0; i < distincion; i++) {
			estrellas = estrellas + "*";
		}
		return estrellas;
	}

	private void centrarVentana() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		Dimension ventana = new Dimension(ANCHO, ALTO);               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}

	public void hacerVisible(boolean b) {
		setVisible(b);
	}

	public void setListener(GuiaMichelinListener l) {
		menuItemConsulta.addActionListener(l);
		menuItemRegistro.addActionListener(l);
		menuItemModificacion.addActionListener(l);
		menuItemSalir.addActionListener(l);
		btnConsultar.addActionListener(l);
		btnEliminar.addActionListener(l);
	}
	
	public void limpiarCampos() {
		cbDistincion.setSelectedIndex(0);
		cbRegion.setSelectedIndex(0);
	}
	
	public void ocultarPanel() {
		table.setVisible(false);
		table.repaint();
	}

	public int getSelectedRow() {
		return table.getSelectedRow();
	}

	public String getNombreFila(int Row) {
		return (String) modeloTabla.getValueAt(Row, 0);
	}

	public void cambiarVentana(JFrame v1, JFrame v2) {
		v1.dispose();
		v2.setVisible(true);	
	}

	public void salir(JFrame v) {
		int respuesta = JOptionPane.showConfirmDialog(null, 
				"Se va a cerrar la aplicación ¿Desea continuar?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			v.dispose();
		}
	}
}
