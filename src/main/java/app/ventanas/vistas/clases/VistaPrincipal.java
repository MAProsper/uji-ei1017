package app.ventanas.vistas.clases;

import app.componentes.Table;
import app.componentes.Textbox;
import app.componentes.buttons.ButtonPrincipal;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorPrincipal;
import app.ventanas.vistas.abstractas.VistaPropia;

public class VistaPrincipal extends VistaPropia {
    public VistaPrincipal() {
        super(
                "Menu principal",
                "Bienvenido a gestor de clientes AkiCode",
                Table.empty(), Textbox.empty(), ButtonPrincipal.values());
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorPrincipal;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorPrincipal();
    }
}
