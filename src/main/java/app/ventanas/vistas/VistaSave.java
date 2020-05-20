package app.ventanas.vistas;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaArchivo;
import app.ventanas.controladores.ControladorSave;

import static helpers.estaticos.Arguments.validate;

// Relacion Vista-Controlador
public class VistaSave extends VistaArchivo {
    // Vista (define la vista contreta)
    public VistaSave() {
        super();
    }

    @Override
    protected Controlador validateControlador(Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorSave);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorSave();
    }

    @Override
    final protected int showDialog() {
        return jFile.showSaveDialog(jFrame);
    }
}