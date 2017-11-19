package com.mti.db;

public class Cuenta {
	
	private String idCuenta;
	private String nombre;
	private String longitud;
	private String latitud;
	private String url;
	private long maxId;
	
	
	public Cuenta() {
		super();
	}

	public Cuenta(String idCuenta, String nombre, String longitud, String latitud, String url, int maxId) {
		super();
		this.idCuenta = idCuenta;
		this.nombre = nombre;
		this.longitud = longitud;
		this.latitud = latitud;
		this.url = url;
		this.maxId = maxId;
	}

	public Cuenta(String idCuenta, String nombre) {
		super();
		this.idCuenta = idCuenta;
		this.nombre = nombre;
	}

	//hacer metodo del set max id
	
	
	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getMaxId() {
		return maxId;
	}

	public void setMaxId(long maxId) {
		this.maxId = maxId;
	}

	public String toString() {
		StringBuffer salida = new StringBuffer("");
		salida
		.append("idCuenta: ").append(getIdCuenta())
		.append(", nombre: ").append(getNombre())
		.append(", latitud: ").append(getLatitud())
		.append(", longitud: ").append(getLongitud())
		.append(", url: ").append(getUrl())
		.append(", maxId: ").append(getMaxId());

		return salida.toString();
	}
	

}
