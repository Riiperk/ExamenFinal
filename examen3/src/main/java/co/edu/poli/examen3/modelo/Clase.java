package co.edu.poli.examen3.modelo;

import java.time.LocalDate;

/**
 * Representa una sesión de clase dentro del semestre.
 * <p>
 * Esta clase extiende {@link Actividad} e incluye el
 * número de sesión específico para cada clase.
 * </p>
 */
public class Clase extends Actividad {

    /** Número de sesión de la clase en el semestre. */
    private int numeroSesion;

    /**
     * Construye una nueva clase con los datos básicos y el número de sesión.
     *
     * @param codigo           código único de la clase
     * @param nombre           nombre descriptivo de la clase
     * @param fechaRealizacion fecha en la que se imparte la clase
     *                         (instancia de {@link LocalDate})
     * @param duracionHoras    duración en horas de la clase
     * @param numeroSesion     número de sesión en el semestre
     */
    public Clase(String codigo, String nombre, LocalDate fechaRealizacion, int duracionHoras, int numeroSesion) {
        super(codigo, nombre, fechaRealizacion, duracionHoras);
        this.numeroSesion = numeroSesion;
    }

    /**
     * Obtiene el número de sesión de la clase.
     *
     * @return número de sesión asignado a esta clase
     */
    public int getNumeroSesion() {
        return numeroSesion;
    }

    /**
     * Establece un nuevo número de sesión para la clase.
     *
     * @param numeroSesion número de sesión a asignar
     */
    public void setNumeroSesion(int numeroSesion) {
        this.numeroSesion = numeroSesion;
    }

    /**
     * Devuelve una representación en cadena de la clase, incluyendo todos sus atributos.
     *
     * @return cadena con valores de código, nombre, fecha, duración y número de sesión
     */
    @Override
    public String toString() {
        return "Clase [numeroSesion=" + numeroSesion
               + ", codigo=" + getCodigo()
               + ", nombre=" + getNombre()
               + ", fechaRealizacion=" + getFechaRealizacion()
               + ", duracionHoras=" + getDuracionHoras()
               + "]";
    }
}
