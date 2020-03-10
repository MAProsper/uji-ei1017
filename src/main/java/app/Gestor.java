package app;

import app.ventanas.*;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.Factura;
import helpers.Llamada;

import java.nio.file.Path;
import java.util.*;

import static helpers.ValidatorArguments.referenceNotNull;

public class Gestor {
    final Stack<Ventana> stack;
    final HashMap<String, Cliente> id2cliente;
    final HashMap<Integer, Cliente> factura2cliente;
    final List<Cliente> clientes;
    final List<Factura> facturas;
    final List<Llamada> llamadas;
    Cliente clienteSelecionado;

    public Gestor() {
        stack = new Stack<>();
        id2cliente = new HashMap<>();
        factura2cliente = new HashMap<>();
        clientes = new LinkedList<>();
        facturas = new LinkedList<>();
        llamadas = new LinkedList<>();
        clienteSelecionado = null;
    }

    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }

    public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }

    public List<Llamada> getLlamadas() {
        return Collections.unmodifiableList(llamadas);
    }

    public Cliente getCliente(final String NIF) {
        return id2cliente.get(NIF);
    }

    public Cliente getCliente(final int codigo) {
        return factura2cliente.get(codigo);
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(final Cliente cliente) {
        this.clienteSelecionado = cliente;
    }

    public void addCliente(final Cliente cliente) {
        referenceNotNull("cliente", cliente);
        id2cliente.put(cliente.getNIF(), cliente);
        clientes.add(cliente);
        for (final Llamada llamada : cliente.getLlamadas()) gestionarLlamada(cliente, llamada);
        for (final Factura factura : cliente.getFacturas()) gestionarFactura(cliente, factura);
    }

    void gestionarLlamada(final Cliente cliente, final Llamada llamada) {
        referenceNotNull("cliente", clienteSelecionado);
        referenceNotNull("llamada", llamada);
        llamadas.add(llamada);
    }

    public void gestionarLlamada(final Llamada llamada) {
        gestionarLlamada(clienteSelecionado, llamada);
        clienteSelecionado.addLlamada(llamada);
    }

    void gestionarFactura(final Cliente cliente, final Factura factura) {
        referenceNotNull("cliente", cliente);
        referenceNotNull("factura", factura);
        factura2cliente.put(factura.getCodigo(), cliente);
        facturas.add(factura);
    }

    public void gestionarFactura(final Factura factura) {
        gestionarFactura(clienteSelecionado, factura);
        clienteSelecionado.addFactura(factura);
    }

    public Ventana getVisor() {
        if (clienteSelecionado instanceof ClientePaticular) return new VentanaClienteParticular();
        else if (clienteSelecionado instanceof ClienteEmpresa) return new VentanaClienteEmpresa();
        else if (clienteSelecionado != null) return new VentanaCliente();
        else return new VentanaError();
    }

    public void removeCliente() {
        final Cliente cliente = getClienteSelecionado();
        id2cliente.remove(cliente.getNIF());
        clientes.remove(cliente);
        for (Factura factura : cliente.getFacturas()) {
            factura2cliente.remove(factura.getCodigo());
            facturas.remove(factura);
        }
        for (Llamada llamada : cliente.getLlamadas())
            llamadas.remove(llamada);
        setClienteSelecionado(null);
    }

    public void run(final Path path) {
        if (Ventana.hasGestor()) throw new OverlappingVentanaException();

        Ventana.setGestor(this);
        stack.push(new VentanaPrincipal());
        while (!stack.empty()) {
            Ventana result = stack.peek().run();
            if (result == null) stack.pop();
            else stack.push(result);
        }
        Ventana.setGestor(null);
    }

    @Override
    public String toString() {
        return "Gestor{" +
                "clientes=" + getClientes() +
                '}';
    }

    public static class OverlappingVentanaException extends IllegalStateException {
        private static final long serialVersionUID = -4234327239149123858L;
    }
}