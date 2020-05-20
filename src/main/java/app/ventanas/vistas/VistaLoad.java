package app.ventanas.vistas;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaArchivo;
import app.ventanas.controladores.ControladorLoad;

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
    final protected int showDialog() {
        return jFile.showOpenDialog(jFrame);
    }
}
