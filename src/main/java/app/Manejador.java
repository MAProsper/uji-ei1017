package app;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;

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

    public Runnable getCloseOperation() {
        return closeOperation;
    }

    final public List<Vista> getVistas() {
        return Collections.unmodifiableList(stack);
    }

    public void connectar(final Modelo modelo, final Vista vista, final Controlador controlador) {
        modelo.setManejador(this);
        vista.setManejador(this);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        controlador.setManejador(this);
        controlador.setModelo(modelo);
        controlador.setVista(vista);
    }

    public void connectar(final Modelo modelo, final Vista vista) {
        connectar(modelo, vista, vista.getControladorDefault());
    }

    final public void show(final Vista ventana) {
        if (ventana != null) {
            stack.push(ventana);
        } else {
            stack.pop();
        }

        if (stack.isEmpty()) {
            closeOperation.run();
        } else {
            stack.peek().show();
        }
    }
}
