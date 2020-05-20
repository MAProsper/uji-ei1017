package app.ventanas.abstractas;

import app.Manejador;
import app.Modelo;

import java.util.concurrent.Semaphore;

import static helpers.estaticos.Arguments.validate;

public abstract class Vista {
    private final static Semaphore running = new Semaphore(1);
    private Manejador manejador;
    private Modelo modelo;
    private Controlador controlador;

    public Vista() {
        manejador = null;
        modelo = null;
        controlador = null;
    }

    public final Modelo getModelo() {
        return validate("Modelo no esta asignado", modelo, hasModelo());
    }

    public void setModelo(final Modelo modelo) {
        this.modelo = modelo;
        update();
    }

    final public boolean hasModelo() {
        return modelo != null;
    }

    protected abstract Controlador validateControlador(final Controlador controlador);

    public final Controlador getControlador() {
        return validate("Controlador no esta asignado", controlador, hasControlador());
    }

    public void setControlador(final Controlador controlador) {
        this.controlador = validateControlador(controlador);
    }

    final public boolean hasControlador() {
        return controlador != null;
    }

    public Manejador getManejador() {
        return validate("Manajador no esta asignado", manejador, hasManajador());
    }

    public void setManejador(final Manejador manejador) {
        this.manejador = manejador;
    }

    final public boolean hasManajador() {
        return manejador != null;
    }

    public void update() {
    }

    public static class OverlappingException extends IllegalStateException {
        private static final long serialVersionUID = -4234327239149123858L;
    }

    public abstract Controlador getControladorDefault();

    public void show() {
        if (!running.tryAcquire())
            throw new Vista.OverlappingException();
    }

    public void hide() {
        running.release();
    }
}
