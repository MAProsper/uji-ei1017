package app.ventanas.controladores.abstractas;

import app.componentes.Button;
import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import app.ventanas.vistas.abstractas.Vista;

import static helpers.estaticos.Arguments.*;

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

    protected abstract boolean validateVista(final Vista vista);

    public final Vista getVista() {
        return validate("Vista no esta asignado", vista, hasVista());
    }

    public void setVista(final Vista vista) {
        if (validateVista(vista)) {
            this.vista = vista;
        } else {
            throw new ValidationException("Vista no valida para este controlador");
        }
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

    protected abstract boolean validateButton(final Button button);

    public void gestionaButton(final Button button) {
        if (!validateButton(button)) {
            throw new ValidationException("Button no valido para este controlador");
        }
    }

    final public void gestionaClose() {
        getManejador().close();
    }

    final protected void vistaNext(final Vista vista, final Controlador controlador) {
        referenceNotNull("Vista", vista);
        referenceNotNull("Controlador", controlador);

        final Manejador manejador = getManejador();
        manejador.connectMVC(getModelo(), vista, controlador);
        manejador.vistaNext(vista);
    }

    final protected void vistaNext(final Vista vista) {
        if (vista == null) vistaBack();
        else vistaNext(vista, vista.getControladorDefault());
    }

    final protected void vistaBack() {
        getManejador().vistaBack();
    }

    @Override
    public String toString() {
        return "Controlador{" +
                "manejador=" + manejador +
                ", modelo=" + modelo +
                ", vista=" + vista +
                '}';
    }
}
