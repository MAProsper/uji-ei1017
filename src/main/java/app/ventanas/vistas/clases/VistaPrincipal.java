package app.ventanas.vistas.clases;

import app.componentes.Table;
import app.componentes.Textbox;
import app.componentes.acciones.AccionPrincipal;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorPrincipal;
import app.ventanas.vistas.abstractas.VistaPropia;

import static helpers.estaticos.Arguments.validate;

public class VistaPrincipal extends VistaPropia {
    public VistaPrincipal() {
        super(
                "Menu principal",
                "Bienvenido a gestor de clientes AkiCode",
                Table.empty(), Textbox.empty(), AccionPrincipal.values());
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorPrincipal);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorPrincipal();
    }
}
