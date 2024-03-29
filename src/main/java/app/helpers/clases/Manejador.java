package app.helpers.clases;

import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.abstractas.VistaPropia;
import app.ventanas.vistas.clases.VistaCliente;
import app.ventanas.vistas.clases.VistaClienteEmpresa;
import app.ventanas.vistas.clases.VistaClienteParticular;
import app.ventanas.vistas.clases.VistaPrincipal;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static helpers.estaticos.Arguments.referenceNotNull;

public class Manejador {
    private final Stack<Vista> stack;
    private Runnable closeOperation;

    public Manejador() {
        stack = new Stack<>();
        setCloseOperation(() -> System.exit(0));
    }

    public void setCloseOperation(final Runnable closeOperation) {
        this.closeOperation = referenceNotNull("Close operation", closeOperation);
    }

    final public void close() {
        stack.clear();
        closeOperation.run();
    }

    final public List<Vista> getVistas() {
        return Collections.unmodifiableList(stack);
    }

    public static VistaPropia getVisor(final Cliente cliente) {
        referenceNotNull("Cliente", cliente);
        if (cliente instanceof ClientePaticular) return new VistaClienteParticular((ClientePaticular) cliente);
        else if (cliente instanceof ClienteEmpresa) return new VistaClienteEmpresa((ClienteEmpresa) cliente);
        else return new VistaCliente(cliente);
    }

    public Vista connectMVC(final Modelo modelo, final Vista vista, final Controlador controlador) {
        referenceNotNull("Modelo", modelo);
        referenceNotNull("Vista", vista);
        referenceNotNull("Controlador", controlador);

        modelo.setManejador(this);
        vista.setControlador(controlador);
        vista.setModelo(modelo);
        controlador.setManejador(this);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        return vista;
    }

    final public Vista connectMVC(final Modelo modelo, final Vista vista) {
        return connectMVC(modelo, vista, vista.getControladorDefault());
    }

    final public Vista connectMVC(final Modelo modelo) {
        return connectMVC(modelo, getVistaDefault());
    }

    final public void vistaNext(final Vista vista) {
        if (vista != null) {
            stack.push(vista);
        } else {
            stack.pop();
        }

        if (stack.isEmpty()) {
            closeOperation.run();
        } else {
            stack.peek().show();
        }
    }

    final public void vistaBack() {
        vistaNext(null);
    }

    public Vista getVistaDefault() {
        return new VistaPrincipal();
    }

    public void vistaDefault() {
        vistaNext(connectMVC(new Modelo()));
    }

    @Override
    public String toString() {
        return "Manejador{" +
                "vistas=" + stack +
                '}';
    }
}
