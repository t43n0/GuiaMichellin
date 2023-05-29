package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import view.*;

import model.Restaurante;

public class RestaurantesPersistencia {

	private static final String ESTRELLA1 = "1 estrella";
	private static final String ESTRELLA2 = "2 estrellas";
	private static final String ESTRELLA3 = "3 estrellas";
	private static final String TODAS = "TODAS";
	VConsulta vC;

	public RestaurantesPersistencia(VConsulta vC) {
		this.vC = vC;
	}
	
	public void registrarRestaurante(Restaurante rt) {
		AccesoDB adb = new AccesoDB();
		
		String sql = "INSERT INTO RESTAURANTES (NOMBRE, REGION, CIUDAD, DISTINCION, DIRECCION," + 
		" PRECIO_MIN, PRECIO_MAX, COCINA, TELEFONO, WEB) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
		    Connection conn = adb.getConexion();
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, rt.getNombre());
		    stmt.setString(2, rt.getRegion());
		    stmt.setString(3, rt.getCiudad());
		    stmt.setInt(4, rt.getDistincion());
		    stmt.setString(5, rt.getDireccion());
		    stmt.setFloat(6, rt.getPrecioMin());
		    stmt.setFloat(7, rt.getPrecioMax());
		    stmt.setString(8, rt.getCocina());
		    stmt.setString(9, rt.getTelefono());
		    stmt.setString(10, rt.getWeb());

		    stmt.executeUpdate();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException | SQLException e) {
		    e.printStackTrace();
		}
	}

	public ArrayList<Restaurante> rellenarModelo() {

		String sentancia = "";
		String region = (String) vC.getCbRegion();
		String distincionSt = (String) vC.getCbDistincion();
		int distincion = 0;

		if (distincionSt.equals(ESTRELLA1)) {
			distincion = 1;
		} else if (distincionSt.equals(ESTRELLA2)) {
			distincion = 2;
		} else if (distincionSt.equals(ESTRELLA3)) {
			distincion = 3;
		}
		
		if (!region.equals(TODAS) && distincion != 0) {
			sentancia = "WHERE REGION LIKE '" + region + "' AND DISTINCION LIKE " + distincion;
		} else if (region.equals(TODAS) && distincion != 0) {
			sentancia = "WHERE DISTINCION LIKE " + distincion;
		} else if (!region.equals(TODAS) && distincion == 0) {
			sentancia = "WHERE REGION LIKE '" + region + "'";
		}

		return cargarDatosBDTabla(sentancia);
	}

	public ArrayList<Restaurante> cargarDatosBDTabla(String statement) {

		AccesoDB adb = new AccesoDB();
		ArrayList<Restaurante> rts = new ArrayList<Restaurante>();

		String sql = "SELECT * FROM RESTAURANTES " + statement;

		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = adb.getConexion();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (!rs.isBeforeFirst()) {
				vC.ocultarPanel();
				JOptionPane.showMessageDialog(null, "No se han encontrado datos para el filtro introducido",
						"Resultado de Consulta", JOptionPane.INFORMATION_MESSAGE);
			}

			while (rs.next()) {
				int id = rs.getInt("ID");
				String nombre = rs.getString("NOMBRE");
				String region = rs.getString("REGION");
				String ciudad = rs.getString("CIUDAD");
				int distincion = rs.getInt("DISTINCION");
				String direccion = rs.getString("DIRECCION");
				float precioMin = rs.getFloat("PRECIO_MIN");
				float precioMax = rs.getFloat("PRECIO_MAX");
				String cocina = rs.getString("COCINA");
				String telefono = rs.getString("TELEFONO");
				String web = rs.getString("WEB");
				Restaurante rt = new Restaurante(id, nombre, region, ciudad, distincion, direccion, precioMin,
						precioMax, cocina, telefono, web);
				rts.add(rt);
			}

			rs.close();
			stmt.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rts;
	}
	
	public void modificarRestaurante(Restaurante rt) {
	    AccesoDB adb = new AccesoDB();

	    String sql = "UPDATE RESTAURANTES SET REGION = ?, CIUDAD = ?, DISTINCION = ?, " + 
	    "DIRECCION = ?, PRECIO_MIN = ?, PRECIO_MAX = ?, COCINA = ?, TELEFONO = ?, WEB = ? WHERE NOMBRE = ?";

	    try {
	        Connection conn = adb.getConexion();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, rt.getRegion());
	        stmt.setString(2, rt.getCiudad());
	        stmt.setInt(3, rt.getDistincion());
	        stmt.setString(4, rt.getDireccion());
	        stmt.setFloat(5, rt.getPrecioMin());
	        stmt.setFloat(6, rt.getPrecioMax());
	        stmt.setString(7, rt.getCocina());
	        stmt.setString(8, rt.getTelefono());
	        stmt.setString(9, rt.getWeb());
	        stmt.setString(10, rt.getNombre());

	        stmt.executeUpdate();
	        stmt.close();
	        conn.close();
	        
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public DefaultComboBoxModel<String> cargarCombo() {

		AccesoDB adb = new AccesoDB();
		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();

		String sql = "SELECT DISTINCT REGION FROM RESTAURANTES ";

		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = adb.getConexion();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			cbModel.addElement("TODAS");

			while (rs.next()) {
				cbModel.addElement(rs.getString("REGION"));
			}

			rs.close();
			stmt.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return cbModel;
	}
	
	public void eliminarSeleccion() {
		int selectRow = vC.getSelectedRow();
		if(selectRow < 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar el registro a eliminar",
					"Error de seleccion",
					JOptionPane.ERROR_MESSAGE);
		} else {
			int respuesta = JOptionPane.showConfirmDialog(null, 
					"Se va a eliminar el registro seleccionado ¿Desea continuar?",
					"Confirmación", JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION) {
				String nombre = vC.getNombreFila(selectRow);
				AccesoDB adb = new AccesoDB();
				String sql = "DELETE FROM RESTAURANTES WHERE NOMBRE LIKE '" + nombre + "'";
				Connection conn;
				Statement stmt;

				try {
					conn = adb.getConexion();
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				vC.cargarTabla(rellenarModelo());
				JOptionPane.showMessageDialog(null, 
						"Se ha eliminado el restaurante con éxito",
						"Resultado de Operación", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}