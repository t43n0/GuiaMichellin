package model;

public class Restaurante {
	private int id;
	private String nombre;
	private String region;
	private String ciudad;
	private int distincion;
	private String direccion;
	private float precioMin;
	private float precioMax;
	private String cocina;
	private String telefono;
	private String web;
	
	public Restaurante(int id, String nombre, String region, String ciudad, int distincion, String direccion,
			float precioMin, float precioMax, String cocina, String telefono, String web) {
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.ciudad = ciudad;
		this.distincion = distincion;
		this.direccion = direccion;
		this.precioMin = precioMin;
		this.precioMax = precioMax;
		this.cocina = cocina;
		this.telefono = telefono;
		this.web = web;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getRegion() {
		return region;
	}

	public String getCiudad() {
		return ciudad;
	}

	public int getDistincion() {
		return distincion;
	}

	public String getDireccion() {
		return direccion;
	}

	public float getPrecioMin() {
		return precioMin;
	}

	public float getPrecioMax() {
		return precioMax;
	}

	public String getCocina() {
		return cocina;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getWeb() {
		return web;
	}
}
