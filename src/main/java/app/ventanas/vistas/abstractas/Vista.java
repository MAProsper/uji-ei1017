package app.ventanas.vistas.abstractas;

import app.helpers.clases.Modelo;
import app.ventanas.controladores.abstractas.Controlador;
import helpers.estaticos.Arguments;

import java.util.concurrent.Semaphore;

import static helpers.estaticos.Arguments.validate;

public abstract class Vista {
    private final static Semaphore running = new Semaphore(1);
    private Modelo modelo;
    private Controlador controlador;

    public Vista() {
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

    protected abstract boolean validateControlador(final Controlador controlador);

    public final Controlador getControlador() {
        return validate("Controlador no esta asignado", controlador, hasControlador());
    }

    public void setControlador(final Controlador controlador) {
        if (validateControlador(controlador)) {
            this.controlador = controlador;
        } else {
            throw new Arguments.ValidationException("Controlador no valido para esta vista");
        }
    }

    final public boolean hasControlador() {
        return controlador != null;
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

    @Override
    public String toString() {
        return "Vista{" +
                "modelo=" + modelo +
                ", controlador=" + controlador +
                '}';
    }
}
