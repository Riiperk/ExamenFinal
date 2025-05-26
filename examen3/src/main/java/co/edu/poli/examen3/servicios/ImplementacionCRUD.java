package co.edu.poli.examen3.servicios;

import java.io.*;
import java.util.*;

import co.edu.poli.examen3.servicios.OperacionArchivo;
import co.edu.poli.examen3.modelo.Actividad;

/**
 * Implementación de las interfaces CRUD y OperacionArchivo utilizando un arreglo para gestionar actividades.
 */
public class ImplementacionCRUD implements CRUD, OperacionArchivo {
    private Actividad[] actividades;
    private int count;
    
    /**
     * Constructor que inicializa el arreglo de actividades.
     */    
    public ImplementacionCRUD() {
        actividades = new Actividad[10];
        count = 0;
    }
    
    /**
     * Método privado para expandir el arreglo en caso de ser necesario.
     */
    private void expandArray() {
        actividades = Arrays.copyOf(actividades, actividades.length * 2);
    }
    
    /**
     * Crea una actividad y la almacena en el arreglo.
     *
     * @param actividad la actividad a crear
     * @return mensaje de confirmación
     */   
    @Override
    public String create(Actividad actividad) {
        if (actividad == null) {
            return "No se puede agregar una actividad nula.";
        }
        if (count == actividades.length) {
            expandArray();
        }
        actividades[count++] = actividad;
        return "Actividad creada exitosamente.";
    }
    
    /**
     * Busca una actividad por su código.
     *
     * @param codigo el código de búsqueda
     * @return la actividad encontrada o null si no existe
     */
    @Override
    public Actividad read(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (int i = 0; i < count; i++) {
            if (actividades[i].getCodigo().equals(codigo)) {
                return actividades[i];
            }
        }
        return null;
    }
    
    /**
     * Retorna un arreglo con todas las actividades almacenadas.
     *
     * @return arreglo de actividades
     */
    @Override
    public Actividad[] readAll() {
        return Arrays.copyOf(actividades, count);
    }
    
    /**
     * Actualiza una actividad existente.
     *
     * @param actividad la actividad con datos actualizados
     * @return mensaje indicando el resultado de la actualización
     */
    @Override
    public String update(Actividad actividad) {
        if (actividad == null || actividad.getCodigo() == null) {
            return "Información inválida para actualizar.";
        }
        for (int i = 0; i < count; i++) {
            if (actividades[i].getCodigo().equals(actividad.getCodigo())) {
                actividades[i] = actividad;
                return "Actividad actualizada exitosamente.";
            }
        }
        return "No se encontró la actividad a actualizar.";
    }
    
    /**
     * Elimina una actividad a partir de su código.
     *
     * @param codigo el código de la actividad a eliminar
     * @return la actividad eliminada o null si no se encuentra
     */
    @Override
    public Actividad delete(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (int i = 0; i < count; i++) {
            if (actividades[i].getCodigo().equals(codigo)) {
                Actividad deleted = actividades[i];
                // Desplazamos los elementos para eliminar la actividad del arreglo
                for (int j = i; j < count - 1; j++) {
                    actividades[j] = actividades[j + 1];
                }
                actividades[--count] = null;
                return deleted;
            }
        }
        return null;
    }
    
    /**
     * Serializa el arreglo de actividades y lo guarda en un archivo.
     *
     * @param path la ruta del archivo donde se guardará la información
     * @return mensaje de resultado de la serialización
     */
    @Override
    public String serializar(String path) {
        if (path == null || path.trim().isEmpty()) {
            return "La ruta del archivo es inválida.";
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(readAll());
            return "Serialización exitosa. Archivo guardado en " + path;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error durante la serialización: " + e.getMessage();
        }
    }
    
    /**
     * Deserializa el arreglo de actividades a partir de un archivo.
     *
     * @param path la ruta del archivo a leer
     * @return mensaje de resultado de la deserialización
     */    
    @Override
    public String deserializar(String path) {
        if (path == null || path.trim().isEmpty()) {
            return "La ruta del archivo es inválida.";
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Actividad[] loaded = (Actividad[]) ois.readObject();
            if (loaded != null) {
                actividades = loaded;
                count = loaded.length;
                return "Deserialización exitosa. Datos cargados desde " + path;
            } else {
                return "No se encontraron datos en el archivo.";
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Error durante la deserialización: " + e.getMessage();
        }
    }
}
