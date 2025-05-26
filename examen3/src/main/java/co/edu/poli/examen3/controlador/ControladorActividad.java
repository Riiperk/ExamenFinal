package co.edu.poli.examen3.controlador;

import co.edu.poli.examen3.modelo.Actividad;
import co.edu.poli.examen3.modelo.Clase;
import co.edu.poli.examen3.modelo.RecursoExterno;
import co.edu.poli.examen3.modelo.Taller;
import co.edu.poli.examen3.servicios.ImplementacionCRUD;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Controlador para la aplicación de gestión de actividades académicas.
 * Esta clase administra la creación, lectura, actualización y eliminación de
 * actividades, así como la serialización y deserialización de las mismas.
 * La interfaz gráfica se enlaza mediante FXML.
 * 
 * @author 
 */
public class ControladorActividad {

    /** Campo de texto para el código de la actividad. */
    @FXML
    private TextField txtCodigo;
    /** Campo de texto para el nombre de la actividad. */
    @FXML
    private TextField txtNombre;
    /** Selector de fecha para la realización de la actividad. */
    @FXML
    private DatePicker dpFecha;
    /** Campo de texto para la duración en horas de la actividad. */
    @FXML
    private TextField txtDuracion;
    /** ComboBox para seleccionar el tipo de actividad (Clase o Taller). */
    @FXML
    private ComboBox<String> cbTipoActividad;
    /** Campo de texto para el número de sesión (solo para Clase). */
    @FXML
    private TextField txtNumeroSesion;
    /** ComboBox para seleccionar el nivel práctico (solo para Taller). */
    @FXML
    private ComboBox<String> cbNivelPractico;
    /** Campo de texto para el ID del recurso (solo para Taller). */
    @FXML
    private TextField txtRecursoID;
    /** Campo de texto para el nombre del recurso (solo para Taller). */
    @FXML
    private TextField txtRecursoNombre;
    /** Campo de texto para el proveedor del recurso (solo para Taller). */
    @FXML
    private TextField txtProveedor;

    /** Tabla que muestra las actividades. */
    @FXML
    private TableView<Actividad> tableActividades;
    /** Columna de la tabla para el código de la actividad. */
    @FXML
    private TableColumn<Actividad, String> colCodigo;
    /** Columna de la tabla para el nombre de la actividad. */
    @FXML
    private TableColumn<Actividad, String> colNombre;
    /** Columna de la tabla para la fecha de realización. */
    @FXML
    private TableColumn<Actividad, String> colFecha;
    /** Columna de la tabla para la duración en horas. */
    @FXML
    private TableColumn<Actividad, Integer> colDuracion;
    /** Columna de la tabla para el tipo de actividad. */
    @FXML
    private TableColumn<Actividad, String> colTipoActividad;
    /** Columna de la tabla para el número de sesión (para Clase). */
    @FXML
    private TableColumn<Actividad, Integer> colNumeroSesion;
    /** Columna de la tabla para el nivel práctico (para Taller). */
    @FXML
    private TableColumn<Actividad, String> colNivelPractico;
    /** Columna de la tabla para el ID del recurso (para Taller). */
    @FXML
    private TableColumn<Actividad, String> colRecursoID;
    /** Columna de la tabla para el nombre del recurso (para Taller). */
    @FXML
    private TableColumn<Actividad, String> colRecursoNombre;
    /** Columna de la tabla para el proveedor del recurso (para Taller). */
    @FXML
    private TableColumn<Actividad, String> colProveedor;

    /** Lista observable que contiene las actividades. */
    private final ObservableList<Actividad> lista = FXCollections.observableArrayList();
    
    /** Objeto CRUD para operaciones sobre actividades. */
    private final ImplementacionCRUD crud = new ImplementacionCRUD();

    /** Formateador para fechas en el formato "dd/MM/yyyy". */
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Inicializa los componentes de la interfaz, establece los elementos de los 
     * ComboBox y configura los listeners para la interacción.
     */
    @FXML
    public void initialize() {
        cbTipoActividad.setItems(FXCollections.observableArrayList("Clase", "Taller"));
        cbTipoActividad.setPromptText("Seleccione tipo");
        cbNivelPractico.setItems(FXCollections.observableArrayList("Básico", "Intermedio", "Avanzado"));
        cbNivelPractico.setPromptText("Seleccione nivel");

        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigo()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colFecha.setCellValueFactory(c -> {
            LocalDate f = c.getValue().getFechaRealizacion();
            return new SimpleStringProperty(f != null ? f.format(dateFormatter) : "");
        });
        colDuracion.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getDuracionHoras()));
        colTipoActividad.setCellValueFactory(c ->
            new SimpleStringProperty(c.getValue() instanceof Clase ? "Clase" : "Taller")
        );
        colNumeroSesion.setCellValueFactory(c -> {
            if (c.getValue() instanceof Clase)
                return new SimpleObjectProperty<>(((Clase) c.getValue()).getNumeroSesion());
            return new SimpleObjectProperty<>(null);
        });
        colNivelPractico.setCellValueFactory(c -> {
            if (c.getValue() instanceof Taller)
                return new SimpleStringProperty(((Taller) c.getValue()).getNivelPractico());
            return new SimpleStringProperty("");
        });
        colRecursoID.setCellValueFactory(c -> {
            if (c.getValue() instanceof Taller)
                return new SimpleStringProperty(((Taller) c.getValue()).getRecursoExterno().getId());
            return new SimpleStringProperty("");
        });
        colRecursoNombre.setCellValueFactory(c -> {
            if (c.getValue() instanceof Taller)
                return new SimpleStringProperty(((Taller) c.getValue()).getRecursoExterno().getNombreRecurso());
            return new SimpleStringProperty("");
        });
        colProveedor.setCellValueFactory(c -> {
            if (c.getValue() instanceof Taller)
                return new SimpleStringProperty(((Taller) c.getValue()).getRecursoExterno().getProveedor());
            return new SimpleStringProperty("");
        });

        tableActividades.setItems(lista);

        // Habilita o deshabilita campos según se seleccione "Clase" o "Taller"
        cbTipoActividad.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            boolean esClase = "Clase".equals(n);
            txtNumeroSesion.setDisable(!esClase);
            cbNivelPractico.setDisable(esClase);
            txtRecursoID.setDisable(esClase);
            txtRecursoNombre.setDisable(esClase);
            txtProveedor.setDisable(esClase);
        });
        cbTipoActividad.getSelectionModel().clearSelection();
        cbNivelPractico.getSelectionModel().clearSelection();

        // Rellena los campos al seleccionar una fila de la tabla
        tableActividades.getSelectionModel().selectedItemProperty().addListener((obs, o, sel) -> {
            if (sel != null) rellenarCampos(sel);
        });
    }

    /**
     * Crea una nueva actividad utilizando los datos ingresados.
     * Si ocurre alguna validación incorrecta, se muestra una alerta de error.
     *
     * @param e Evento de acción disparado al pulsar el botón "Crear".
     */
    @FXML
    void crear(ActionEvent e) {
        try {
            Actividad a = construirActividad();
            lista.add(a);
            limpiarCampos();
        } catch (IllegalArgumentException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }

    /**
     * Busca y muestra en la tabla la actividad cuyo código coincide con el ingresado.
     * Si no se encuentra, se muestra una alerta indicándolo.
     *
     * @param e Evento de acción disparado al pulsar el botón "Leer".
     */
    @FXML
    void leer(ActionEvent e) {
        String cod = txtCodigo.getText();
        List<Actividad> found = new ArrayList<>();
        for (Actividad a : lista) {
            if (a.getCodigo().equals(cod)) found.add(a);
        }
        if (found.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Código de Actividad no existente. Digite nuevamente").showAndWait();
        } else {
            tableActividades.setItems(FXCollections.observableArrayList(found));
        }
    }

    /**
     * Muestra todas las actividades en la tabla y limpia los campos de entrada.
     *
     * @param e Evento de acción disparado al pulsar el botón "Leer Todo".
     */
    @FXML
    void leerTodo(ActionEvent e) {
        tableActividades.setItems(lista);
        limpiarCampos();
    }

    /**
     * Filtra y muestra solo las actividades de tipo Clase en la tabla.
     *
     * @param e Evento de acción disparado al pulsar el botón "Leer Clase".
     */
    @FXML
    void leerclase(ActionEvent e) {
        List<Actividad> clases = new ArrayList<>();
        for (Actividad a : lista) {
            if (a instanceof Clase) clases.add(a);
        }
        tableActividades.setItems(FXCollections.observableArrayList(clases));
    }

    /**
     * Filtra y muestra solo las actividades de tipo Taller en la tabla.
     *
     * @param e Evento de acción disparado al pulsar el botón "Leer Taller".
     */
    @FXML
    void leertaller(ActionEvent e) {
        List<Actividad> tallers = new ArrayList<>();
        for (Actividad a : lista) {
            if (a instanceof Taller) tallers.add(a);
        }
        tableActividades.setItems(FXCollections.observableArrayList(tallers));
    }

    /**
     * Actualiza una actividad existente cuyo código coincide con el ingresado.
     * Si no se encuentra la actividad, se muestra una alerta.
     *
     * @param e Evento de acción disparado al pulsar el botón "Actualizar".
     */
    @FXML
    void actualizar(ActionEvent e) {
        String cod = txtCodigo.getText();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(cod)) {
                try {
                    lista.set(i, construirActividad());
                    limpiarCampos();
                    return;
                } catch (IllegalArgumentException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                    return;
                }
            }
        }
        new Alert(Alert.AlertType.WARNING, "Código de Actividad no existente. Digite nuevamente").showAndWait();
    }

    /**
     * Elimina la actividad cuyo código coincide con el ingresado.
     * Si no se encuentra la actividad, se muestra una alerta.
     *
     * @param e Evento de acción disparado al pulsar el botón "Eliminar".
     */
    @FXML
    void eliminar(ActionEvent e) {
        String cod = txtCodigo.getText();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(cod)) {
                lista.remove(i);
                return;
            }
        }
        new Alert(Alert.AlertType.WARNING, "Código de Actividad no existente. Digite nuevamente").showAndWait();
    }

    /**
     * Serializa (guarda) las actividades en un archivo. Se solicita la ruta al usuario.
     * Si la operación es exitosa, se muestra una alerta informativa.
     *
     * @param e Evento de acción disparado al pulsar el botón "Serializar".
     */
    @FXML
    void serializar(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog("actividades.dat");
        dialog.setTitle("Serializar");
        dialog.setHeaderText("Guardar actividades a archivo");
        dialog.setContentText("Ruta del archivo:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(path -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
                oos.writeObject(lista.toArray(new Actividad[0]));
                new Alert(Alert.AlertType.INFORMATION, "Archivo guardado en " + path).showAndWait();
            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, "Error al guardar: " + ex.getMessage()).showAndWait();
            }
        });
    }

    /**
     * Deserializa (carga) las actividades desde un archivo. Se solicita la ruta al usuario.
     * Si la operación es exitosa, se actualiza la lista y se informa al usuario mediante una alerta.
     *
     * @param e Evento de acción disparado al pulsar el botón "Deserializar".
     */
    @FXML
    void deserializar(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog("actividades.dat");
        dialog.setTitle("Deserializar");
        dialog.setHeaderText("Cargar actividades desde archivo");
        dialog.setContentText("Ruta del archivo:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(path -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
                Actividad[] loaded = (Actividad[]) ois.readObject();
                lista.setAll(loaded);
                new Alert(Alert.AlertType.INFORMATION, "Archivo deserializado correctamente en " + path).showAndWait();
            } catch (IOException | ClassNotFoundException ex) {
                new Alert(Alert.AlertType.ERROR, "Error al cargar: " + ex.getMessage()).showAndWait();
            }
        });
    }

    /**
     * Construye un objeto Actividad a partir de los datos ingresados en la interfaz.
     * Realiza validaciones sobre los campos numéricos, el tipo de actividad y la fecha.
     *
     * @return La actividad construida.
     * @throws IllegalArgumentException si la duración o el número de sesión son negativos,
     * o si no se selecciona el tipo de actividad.
     */
    private Actividad construirActividad() {
        String cod = txtCodigo.getText();
        String nom = txtNombre.getText();
        LocalDate fecha = parseFecha();
        int dur = txtDuracion.getText().isBlank() ? 0 : Integer.parseInt(txtDuracion.getText());
        if (dur < 0) throw new IllegalArgumentException("Duración no puede ser negativa");

        String tipo = cbTipoActividad.getValue();
        if (tipo == null) throw new IllegalArgumentException("Por favor seleccionar el tipo de actividad");

        if ("Clase".equals(tipo)) {
            int ses = txtNumeroSesion.getText().isBlank() ? 0 : Integer.parseInt(txtNumeroSesion.getText());
            if (ses < 0) throw new IllegalArgumentException("Número de sesión no puede ser negativo");
            return new Clase(cod, nom, fecha, dur, ses);
        } else {
            String nivel = cbNivelPractico.getValue();
            if (nivel == null) throw new IllegalArgumentException("Seleccione el nivel práctico");
            RecursoExterno r = new RecursoExterno(txtRecursoID.getText(), txtRecursoNombre.getText(), txtProveedor.getText());
            return new Taller(cod, nom, fecha, dur, nivel, r);
        }
    }

    /**
     * Parsea la fecha ingresada en el DatePicker. Se admite el formato por defecto
     * o el formato "dd/MM/yyyy". Si el formato es inválido, se lanza una excepción.
     *
     * @return La fecha parseada.
     * @throws IllegalArgumentException si el formato de la fecha es inválido.
     */
    private LocalDate parseFecha() {
        LocalDate v = dpFecha.getValue();
        if (v != null) return v;
        String t = dpFecha.getEditor().getText();
        if (t == null || t.isBlank()) return null;
        try {
            return LocalDate.parse(t);
        } catch (DateTimeParseException ex1) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(t, fmt);
            } catch (DateTimeParseException ex2) {
                throw new IllegalArgumentException("Formato de fecha inválido");
            }
        }
    }

    /**
     * Rellena los campos de la interfaz con los datos de la actividad dada.
     *
     * @param a La actividad de la cual se obtendrán los datos.
     */
    private void rellenarCampos(Actividad a) {
        txtCodigo.setText(a.getCodigo());
        txtNombre.setText(a.getNombre());
        dpFecha.setValue(a.getFechaRealizacion());
        txtDuracion.setText(String.valueOf(a.getDuracionHoras()));
        if (a instanceof Clase) {
            cbTipoActividad.setValue("Clase");
            txtNumeroSesion.setText(String.valueOf(((Clase) a).getNumeroSesion()));
        } else {
            cbTipoActividad.setValue("Taller");
            Taller t = (Taller) a;
            cbNivelPractico.setValue(t.getNivelPractico());
            txtRecursoID.setText(t.getRecursoExterno().getId());
            txtRecursoNombre.setText(t.getRecursoExterno().getNombreRecurso());
            txtProveedor.setText(t.getRecursoExterno().getProveedor());
        }
    }

    /**
     * Limpia todos los campos de entrada de la interfaz.
     */
    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        dpFecha.setValue(null);
        dpFecha.getEditor().clear();
        txtDuracion.clear();
        cbTipoActividad.getSelectionModel().clearSelection();
        txtNumeroSesion.clear();
        cbNivelPractico.getSelectionModel().clearSelection();
        txtRecursoID.clear();
        txtRecursoNombre.clear();
        txtProveedor.clear();
    }
}
