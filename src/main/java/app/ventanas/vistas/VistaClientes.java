package app.ventanas.vistas;

import app.Modelo;
import app.helpers.Formatter;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionClientes;
import app.ventanas.controladores.ControladorCliente;
import app.ventanas.controladores.ControladorClientes;
import app.ventanas.interfaces.Textbox;
import app.ventanas.tables.TableClientes;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.clases.Direccion;
import helpers.interfaces.Factory;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.*;


public class VistaClientes extends VistaPropia {

    // Vista (define la vista contreta)
    public VistaClientes() {
        super(
                "Clientes",
                "Listado de clientes, selecione uno para verlo",
                TableClientes.values(), Textbox.empty(), AccionClientes.values());
    }

    @Override
    protected Controlador validateControlador(Controlador controlador) {
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
