package app;

import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaPrincipal;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static helpers.estaticos.Arguments.referenceNotNull;

public class Manejador {
    private final Stack<Vista> stack;
    private Runnable closeOperation;

    public Manejador() {
        stack = new Stack<>();
        setCloseOperation(() -> System.exit(0));
    }

    public void setCloseOperation(final Runnable closeOperation) {
        this.closeOperation = referenceNotNull("Close operation", closeOperation);
    }

    final public void close() {
        stack.clear();
        closeOperation.run();
    }

    final public List<Vista> getVistas() {
        return Collections.unmodifiableList(stack);
    }

    public void connectMVC(final Modelo modelo, final Vista vista, final Controlador controlador) {
        referenceNotNull("Modelo", modelo);
        referenceNotNull("Vista", vista);
        referenceNotNull("Controlador", controlador);

        modelo.setManejador(this);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        controlador.setManejador(this);
        controlador.setModelo(modelo);
        controlador.setVista(vista);
    }

    final public void connectMVC(final Modelo modelo, final Vista vista) {
        connectMVC(modelo, vista, vista.getControladorDefault());
    }

    final public void vistaNext(final Vista vista) {
        if (vista != null) {
            stack.push(vista);
        } else {
            stack.pop();
        }

        if (stack.isEmpty()) {
            closeOperation.run();
        } else {
            stack.peek().show();
        }
    }

    final public void vistaBack() {
        vistaNext(null);
    }

    public void vistaDefault() {
        final Modelo modelo = new Modelo();
        final Vista vista = new VistaPrincipal();
        connectMVC(modelo, vista);
        vistaNext(vista);
    }

    @Override
    public String toString() {
        return "Manejador{" +
                "vistas=" + stack +
                '}';
    }
}
