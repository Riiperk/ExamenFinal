package co.edu.poli.examen3.servicios;

import co.edu.poli.examen3.modelo.Actividad;

/**
 * Interfaz que define las operaciones CRUD para gestionar actividades académicas.
 */
public interface CRUD {
    
    /**
     * Crea y agrega una actividad.
     *
     * @param actividad la actividad a crear
     * @return un mensaje indicando el resultado de la operación
     */
    String create(Actividad actividad);
    
    /**
     * Lee y retorna una actividad a partir de su código.
     *
     * @param codigo el código de la actividad a buscar
     * @return la actividad encontrada; null si no se encuentra
     */
    Actividad read(String codigo);
    
    /**
     * Retorna todas las actividades almacenadas.
     *
     * @return un arreglo con todas las actividades
     */
    Actividad[] readAll();
    
    /**
     * Actualiza una actividad existente.
     *
     * @param actividad la actividad con los datos actualizados
     * @return un mensaje indicando el resultado de la operación
     */
    String update(Actividad actividad);
    
    /**
     * Elimina una actividad a partir de su código.
     *
     * @param codigo el código de la actividad a eliminar
     * @return la actividad eliminada; null si no se encuentra
     */
    Actividad delete(String codigo);
}
