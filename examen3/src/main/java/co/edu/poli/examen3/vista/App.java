package co.edu.poli.examen3.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Punto de entrada principal para la aplicación de Gestión de Actividades.
 * Esta clase extiende de {@link Application} de JavaFX y carga el fichero FXML
 * asociado para crear el árbol visual (scene graph) de la aplicación.
 * 
 * Para lanzar la aplicación, ejecute el método {@link #main(String[])}.
 * 
 * @author 
 */
public class App extends Application {

    /** Escena de JavaFX que muestra la interfaz de usuario de la aplicación. */
    private static Scene scene;

    /**
     * Inicia la aplicación JavaFX configurando la ventana principal (stage).
     * Este método carga el fichero "ActividadAplicacion.fxml" y establece las dimensiones de la escena.
     *
     * @param stage La ventana principal para esta aplicación.
     * @throws IOException Si no se puede cargar el fichero FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ActividadAplicacion"), 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Gestión de Actividades");
        stage.show();
    }

    /**
     * Establece el nodo raíz de la escena actual cargando un fichero FXML.
     *
     * @param fxml El nombre del fichero FXML (sin la extensión ".fxml") que se desea cargar.
     * @throws IOException Si no se puede cargar el fichero FXML.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga y devuelve el nodo raíz a partir de un fichero FXML.
     *
     * @param fxml El nombre del fichero FXML (sin la extensión ".fxml") que se desea cargar.
     * @return El nodo raíz del diseño definido en el fichero FXML.
     * @throws IOException Si no se puede cargar el fichero FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    /**
     * Método principal que inicia la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        launch();
    }
}
