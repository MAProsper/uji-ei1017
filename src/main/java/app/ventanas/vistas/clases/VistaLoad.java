package app.ventanas.vistas.clases;

import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorLoad;
import app.ventanas.vistas.abstractas.VistaArchivo;

import static helpers.estaticos.Arguments.validate;

public class VistaLoad extends VistaArchivo {
    public VistaLoad() {
        super();
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorLoad);
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
