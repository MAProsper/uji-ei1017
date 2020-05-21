package app.ventanas.vistas.clases;

import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorSave;
import app.ventanas.vistas.abstractas.VistaArchivo;

import static helpers.estaticos.Arguments.validate;

public class VistaSave extends VistaArchivo {
    public VistaSave() {
        super();
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorSave);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorSave();
    }

    @Override
    final protected int dialog() {
        return jFile.showSaveDialog(jFrame);
    }
}
