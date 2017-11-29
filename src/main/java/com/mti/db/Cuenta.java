package com.mti.db;

import java.util.Date;
import java.util.List;

import twitter4j.Status;

public class Cuenta {
	
	private String idCuenta;
	private String nombre;
	private String longitud;
	private String latitud;
	private String url;
	private String maxId;
	private List <Status> tweets;
	private Date lastModified;
	private String enabled;
	
	
	public Cuenta() {
		super();
	}

	public Cuenta(String idCuenta, String nombre) {
		super();
		this.idCuenta = idCuenta;
		this.nombre = nombre;
	}
	
	public Cuenta(String idCuenta, String nombre, String longitud, String latitud, String url, String maxId) {
		super();
		this.idCuenta = idCuenta;
		this.nombre = nombre;
		this.longitud = longitud;
		this.latitud = latitud;
		this.url = url;
		this.maxId = maxId;
	}

	public Cuenta(String idCuenta, String nombre, String longitud, String latitud, String url, String maxId, List <Status> tweets) {
		super();
		this.idCuenta = idCuenta;
		this.nombre = nombre;
		this.longitud = longitud;
		this.latitud = latitud;
		this.url = url;
		this.maxId = maxId;
		this.tweets = tweets;
	}

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

	public String getMaxId() {
		return maxId;
	}

	public void setMaxId(String maxId) {
		this.maxId = maxId;
	}
	
	public List<Status> getTweets() {
		return tweets;
	}

	public void setTweets(List<Status> tweets) {
		this.tweets = tweets;
	}
	
	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String toString() {
		StringBuffer salida = new StringBuffer("");
		salida
		.append("idCuenta: ").append(getIdCuenta())
		.append(", nombre: ").append(getNombre())
		.append(", latitud: ").append(getLatitud())
		.append(", longitud: ").append(getLongitud())
		.append(", url: ").append(getUrl())
		.append(", maxId: ").append(getMaxId())
		.append(", tweets: ").append(getTweets())
		;

		return salida.toString();
	}
	

}
