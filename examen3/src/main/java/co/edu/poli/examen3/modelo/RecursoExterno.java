package co.edu.poli.examen3.modelo;

import java.io.Serializable;

/**
 * Representa un recurso externo utilizado en un taller.
 * <p>
 * Contiene atributos básicos como identificador, nombre del recurso
 * y nombre del proveedor.
 * </p>
 */
public class RecursoExterno implements Serializable {

    /**
     * Identificador de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /** Identificador único del recurso. */
    private String id;
    /** Nombre descriptivo del recurso. */
    private String nombreRecurso;
    /** Nombre de la entidad proveedora del recurso. */
    private String proveedor;

    /**
     * Construye un nuevo recurso externo con los datos especificados.
     *
     * @param id             identificador único del recurso
     * @param nombreRecurso  nombre descriptivo del recurso
     * @param proveedor      nombre de la entidad proveedora
     */
    public RecursoExterno(String id, String nombreRecurso, String proveedor) {
        this.id = id;
        this.nombreRecurso = nombreRecurso;
        this.proveedor = proveedor;
    }

    /**
     * Obtiene el identificador del recurso.
     *
     * @return identificador único
     */
    public String getId() {
        return id;
    }

    /**
     * Establece un nuevo identificador para el recurso.
     *
     * @param id identificador único a asignar
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del recurso.
     *
     * @return nombre descriptivo del recurso
     */
    public String getNombreRecurso() {
        return nombreRecurso;
    }

    /**
     * Actualiza el nombre descriptivo del recurso.
     *
     * @param nombreRecurso nuevo nombre a asignar
     */
    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    /**
     * Obtiene el nombre del proveedor del recurso.
     *
     * @return nombre de la entidad proveedora
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Define un nuevo proveedor para el recurso.
     *
     * @param proveedor nombre de la entidad proveedora a asignar
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Devuelve una representación en cadena con los datos del recurso externo.
     *
     * @return cadena con id, nombre de recurso y proveedor
     */
    @Override
    public String toString() {
        return "RecursoExterno [id=" + id + ", nombreRecurso=" + nombreRecurso + ", proveedor=" + proveedor + "]";
    }
}
