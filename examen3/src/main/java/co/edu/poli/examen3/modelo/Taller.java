package co.edu.poli.examen3.modelo;

import java.io.*;
import java.time.LocalDate;

/**
 * Representa un taller académico.
 * <p>
 * Esta clase extiende {@link Actividad} e incluye atributos
 * específicos de nivel práctico y recurso externo asociado.
 * </p>
 */
public class Taller extends Actividad {

    /** Nivel práctico del taller (por ejemplo, básico, intermedio o avanzado). */
    private String nivelPractico;
    /** Recurso externo asociado al taller. */
    private RecursoExterno recursoExterno;

    /**
     * Crea un nuevo taller con los atributos especificados.
     *
     * @param codigo           código único del taller
     * @param nombre           nombre descriptivo del taller
     * @param fechaRealizacion fecha en que se realiza el taller
     *                         (instancia de {@link LocalDate})
     * @param duracionHoras    duración en horas del taller
     * @param nivelPractico    nivel práctico del taller
     * @param recursoExterno   recurso externo asociado al taller
     */
    public Taller(String codigo, String nombre, LocalDate fechaRealizacion, int duracionHoras,
                  String nivelPractico, RecursoExterno recursoExterno) {
        super(codigo, nombre, fechaRealizacion, duracionHoras);
        this.nivelPractico = nivelPractico;
        this.recursoExterno = recursoExterno;
    }

    /**
     * Obtiene el nivel práctico del taller.
     *
     * @return nivel práctico configurado
     */
    public String getNivelPractico() {
        return nivelPractico;
    }

    /**
     * Establece el nivel práctico del taller.
     *
     * @param nivelPractico nuevo nivel práctico a asignar
     */
    public void setNivelPractico(String nivelPractico) {
        this.nivelPractico = nivelPractico;
    }

    /**
     * Obtiene el recurso externo asociado al taller.
     *
     * @return instancia de {@link RecursoExterno}
     */
    public RecursoExterno getRecursoExterno() {
        return recursoExterno;
    }

    /**
     * Define un nuevo recurso externo para el taller.
     *
     * @param recursoExterno nueva instancia de recurso externo
     */
    public void setRecursoExterno(RecursoExterno recursoExterno) {
        this.recursoExterno = recursoExterno;
    }

    /**
     * Devuelve una representación en cadena de los detalles del taller.
     *
     * @return cadena con código, nombre, fecha, duración, nivel práctico y recurso externo
     */
    @Override
    public String toString() {
        return "Taller [nivelPractico=" + nivelPractico
               + ", codigo=" + getCodigo()
               + ", nombre=" + getNombre()
               + ", fechaRealizacion=" + getFechaRealizacion()
               + ", duracionHoras=" + getDuracionHoras()
               + ", recursoExterno=" + recursoExterno
               + "]";
    }
}
