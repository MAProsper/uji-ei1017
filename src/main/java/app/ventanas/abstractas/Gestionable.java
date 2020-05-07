package app.ventanas.abstractas;

import app.Gestor;

import java.util.concurrent.Semaphore;

import static helpers.estaticos.Arguments.validate;

public abstract class Gestionable {
    private final static Semaphore running = new Semaphore(1);
    private Gestor gestor;

    public Gestionable() {
        gestor = null;
    }

    public final Gestor getGestor() {
        return validate("Gestor no esta asignado", gestor, hasGestor());
    }

    public void setGestor(final Gestor gestor) {
        this.gestor = gestor;
    }

    final public boolean hasGestor() {
        return gestor != null;
    }

    protected void showNext(final Gestionable ventana) {
        running.release();
        getGestor().showNext(ventana);
    }

    public void show() {
        if (!running.tryAcquire())
            throw new OverlappingException();
    }

    public static class OverlappingException extends IllegalStateException {
        private static final long serialVersionUID = -4234327239149123858L;
    }
}
