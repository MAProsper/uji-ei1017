package app.ventanas.vistas.clases;

import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorLoad;
import app.ventanas.vistas.abstractas.VistaArchivo;

public class VistaLoad extends VistaArchivo {
    public VistaLoad() {
        super();
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorLoad;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorLoad();
    }

    @Override
    final protected int dialog() {
        return jFile.showOpenDialog(jFrame);
    }
}
