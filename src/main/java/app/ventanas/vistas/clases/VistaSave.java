package app.ventanas.vistas.clases;

import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorSave;
import app.ventanas.vistas.abstractas.VistaArchivo;

public class VistaSave extends VistaArchivo {
    public VistaSave() {
        super();
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorSave;
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
