package co.edu.poli.examen3.modelo;

import java.time.LocalDate;
import java.io.*;
import java.util.*;
import java.io.Serializable;

/**
 * Clase abstracta que representa una actividad académica.
 * Contiene atributos comunes como el código, nombre, fecha de realización y duración en horas.
 *
 * @author 
 */

public abstract class Actividad implements Serializable{
	private String codigo;
    private String nombre;
    private LocalDate fechaRealizacion;
    private int duracionHoras;
    
    /**
     * Crea una actividad con los datos básicos.
     *
     * @param codigo          el código de la actividad
     * @param nombre          el nombre de la actividad
     * @param fechaRealizacion la fecha en que se realiza la actividad
     * @param duracionHoras   la duración en horas de la actividad
     */
    public Actividad(String codigo, String nombre, LocalDate fechaRealizacion, int duracionHoras) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaRealizacion = fechaRealizacion;
        this.duracionHoras = duracionHoras;
    }
    /**
     * Retorna el código de la actividad.
     *
     * @return el código
     */
	public String getCodigo() {
		return codigo;
	}
    /**
     * Establece el código de la actividad.
     *
     * @param codigo el nuevo código
     */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
    
    /**
     * Retorna el nombre de la actividad.
     *
     * @return el nombre
     */
	public String getNombre() {
		return nombre;
	}
    
    /**
     * Establece el nombre de la actividad.
     *
     * @param nombre el nuevo nombre
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    /**
     * Retorna la fecha de realización de la actividad.
     *
     * @return la fecha de realización (LocalDate)
     */
	public LocalDate getFechaRealizacion() {
		return fechaRealizacion;
	}
    
    /**
     * Establece la fecha de realización.
     *
     * @param fechaRealizacion la nueva fecha (LocalDate)
     */
	public void setFechaRealizacion(LocalDate fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
    
    /**
     * Retorna la duración en horas de la actividad.
     *
     * @return la duración en horas
     */
	public int getDuracionHoras() {
		return duracionHoras;
	}
    
    /**
     * Establece la duración en horas.
     *
     * @param duracionHoras la nueva duración
     */
	public void setDuracionHoras(int duracionHoras) {
		this.duracionHoras = duracionHoras;
	}
    
    /**
     * Representación en cadena del objeto Actividad.
     *
     * @return una cadena con los detalles de la actividad
     */
	@Override
	public String toString() {
		return "Actividad [codigo=" + codigo + ", nombre=" + nombre + ", fechaRealizacion=" + fechaRealizacion
				+ ", duracionHoras=" + duracionHoras + "]";
	}
    
}