/**
 * Módulo principal de la aplicación "co.edu.poli.examen3".
 * <p>
 * Este módulo requiere los siguientes módulos de JavaFX:
 * <ul>
 *   <li><code>javafx.controls</code>: Para controles de interfaz gráfica.</li>
 *   <li><code>javafx.fxml</code>: Para cargar ficheros FXML y gestionar la inyección de dependencias en la interfaz.</li>
 * </ul>
 * </p>
 * <p>
 * Se abren los paquetes internos a <code>javafx.fxml</code> para que los componentes sean inyectados correctamente:
 * <ul>
 *   <li><code>co.edu.poli.examen3.modelo</code> se abre a <code>javafx.base</code> y <code>javafx.fxml</code>.</li>
 *   <li><code>co.edu.poli.examen3.controlador</code> se abre a <code>javafx.fxml</code>.</li>
 *   <li><code>co.edu.poli.examen3.vista</code> se abre a <code>javafx.fxml</code>.</li>
 * </ul>
 * </p>
 * <p>
 * Además, se exporta el paquete <code>co.edu.poli.examen3.vista</code> para que pueda ser utilizado desde otros módulos si fuera necesario.
 * </p>
 */
module co.edu.poli.examen3 {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens co.edu.poli.examen3.modelo to javafx.base, javafx.fxml;
    opens co.edu.poli.examen3.controlador to javafx.fxml;
    opens co.edu.poli.examen3.vista to javafx.fxml;
    exports co.edu.poli.examen3.vista;
}