package app.ventanas.abstractas;

import app.Manejador;
import app.Modelo;
import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.validate;

public abstract class Controlador {
    private Manejador manejador;
    private Modelo modelo;
    private Vista vista;

    public Controlador() {
        manejador = null;
        modelo = null;
        vista = null;
    }

    public final Modelo getModelo() {
        return validate("Modelo no esta asignado", modelo, hasModelo());
    }

    public void setModelo(final Modelo modelo) {
        this.modelo = modelo;
    }

    final public boolean hasModelo() {
        return modelo != null;
    }

    protected abstract Vista validateVista(final Vista vista);

    public final Vista getVista() {
        return validate("Vista no esta asignado", vista, hasVista());
    }

    public void setVista(final Vista vista) {
        this.vista = validateVista(vista);
    }

    final public boolean hasVista() {
        return vista != null;
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

    public abstract void gestionaAccion(final Accion accion); // Gestiona la accion del usuario

    protected void showNext(final Vista vista, final Controlador controlador) {
        final Manejador manejador = getManejador();
        manejador.connectar(getModelo(), vista, controlador);
        manejador.show(vista);
    }

    protected void showNext(final Vista vista) {
        showNext(vista, vista.getControladorDefault());
    }
}
