package co.edu.poli.examen3.servicios;

/**
 * Interfaz que define las operaciones de archivo para la serialización y deserialización
 * de las actividades académicas.
 */
public interface OperacionArchivo {
    
    /**
     * Serializa las actividades y las guarda en un archivo.
     *
     * @param path la ruta del archivo destino
     * @return un mensaje indicando el resultado de la serialización
     */
    String serializar(String path);
    
    /**
     * Deserializa las actividades a partir de un archivo.
     *
     * @param path la ruta del archivo origen
     * @return un mensaje indicando el resultado de la deserialización
     */
    String deserializar(String path);
}
