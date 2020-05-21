package app.ventanas.vistas.clases;

import app.componentes.Textbox;
import app.componentes.acciones.AccionClientes;
import app.componentes.tables.TableClientes;
import app.helpers.estaticos.Formatter;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorClientes;
import app.ventanas.vistas.abstractas.VistaPropia;

import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.validate;


public class VistaClientes extends VistaPropia {
    public VistaClientes() {
        super(
                "Clientes",
                "Listado de clientes, selecione uno para verlo",
                TableClientes.values(), Textbox.empty(), AccionClientes.values());
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorClientes);
    }

    @Override
    public void update() {
        setTable(getModelo().getClientes().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClientes();
    }
}
