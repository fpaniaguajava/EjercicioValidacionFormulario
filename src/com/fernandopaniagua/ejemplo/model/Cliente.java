package com.fernandopaniagua.ejemplo.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fernandopaniagua.ejemplo.exceptions.DNIException;
import com.fernandopaniagua.ejemplo.exceptions.EmailException;
import com.fernandopaniagua.ejemplo.exceptions.FormularioException;
import com.fernandopaniagua.ejemplo.exceptions.NombreCortoException;

public class Cliente {
	private String nombre;
	private String dni;
	private String email;
	public Cliente(String nombre, String dni, String email) throws NombreCortoException, DNIException, EmailException {
		super();
		if (nombre.length()<2) throw new NombreCortoException();
		if (!isDNIValido(dni)) throw new DNIException();
		if (!isEmailValido(email)) throw new EmailException();
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private boolean isDNIValido(String dni) {
		return dni.substring(dni.length()-1, dni.length()).equals(calcularLetraDNI(dni));
	}
	
	private boolean isEmailValido(String email) {
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(email);
		return m.matches();	
	}
	
	private static String calcularLetraDNI(String dni) {
		String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";
		int digitosDNI = Integer.parseInt(dni.substring(0, dni.length()-1));
		String letraDNI = dni.substring(dni.length()-1,dni.length());
		int resto = digitosDNI%23;
		String letraCalculada = String.valueOf(letrasDNI.charAt(resto));
		return letraCalculada;
	}
	//Borrar
	public static void main(String[] args) {
		String dniPrueba = "52101101Y";
		try {
			new Cliente("Fernando","52101101Y","pepe@.com");
			System.out.println("Ok");
		} catch (NombreCortoException | DNIException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			e.printStackTrace();
		} catch (FormularioException e) {//El editor detecta que es código no ejecutable (nunca se va a producir)
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
