package app;

import app.ventanas.abstractas.Ventana;
import app.ventanas.claeses.*;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.clases.Factura;
import helpers.clases.Llamada;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static helpers.estaticos.ValidatorArguments.referenceNotNull;
import static helpers.estaticos.ValidatorArguments.validate;

public class Gestor {
    final Stack<Ventana> stack;
    HashMap<String, Cliente> id2cliente;
    HashMap<Integer, Cliente> factura2cliente;
    List<Cliente> clientes;
    List<Factura> facturas;
    List<Llamada> llamadas;

    public Gestor() {
        stack = new Stack<>();
        clearClientes();
    }

    public void clearClientes() {
        id2cliente = new HashMap<>();
        factura2cliente = new HashMap<>();
        clientes = new LinkedList<>();
        facturas = new LinkedList<>();
        llamadas = new LinkedList<>();
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

    public void addCliente(final Cliente cliente) {
        referenceNotNull("cliente", cliente);
        id2cliente.put(cliente.getNIF(), cliente);
        clientes.add(cliente);
        for (final Llamada llamada : cliente.getLlamadas()) addLlamada(cliente, llamada);
        for (final Factura factura : cliente.getFacturas()) addFactura(cliente, factura);
    }

    public void addLlamada(final Cliente cliente, final Llamada llamada) {
        referenceNotNull("cliente", cliente);
        referenceNotNull("llamada", llamada);
        llamadas.add(llamada);
    }

    public void addFactura(final Cliente cliente, final Factura factura) {
        referenceNotNull("cliente", cliente);
        referenceNotNull("factura", factura);
        factura2cliente.put(factura.getCodigo(), cliente);
        facturas.add(factura);
    }

    public Ventana getVisor(final Cliente cliente) {
        if (cliente instanceof ClientePaticular) return new VentanaClienteParticular((ClientePaticular) cliente);
        else if (cliente instanceof ClienteEmpresa) return new VentanaClienteEmpresa((ClienteEmpresa) cliente);
        else if (cliente != null) return new VentanaCliente(cliente);
        else return new VentanaError("cliente no encotrado");
    }

    public void removeCliente(final Cliente cliente) {
        referenceNotNull("cliente", cliente);
        id2cliente.remove(cliente.getNIF());
        clientes.remove(cliente);

        for (Factura factura : cliente.getFacturas()) {
            factura2cliente.remove(factura.getCodigo());
            facturas.remove(factura);
        }

        for (Llamada llamada : cliente.getLlamadas())
            llamadas.remove(llamada);
    }

    public void run() {
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

    public void load(final Path path) {
        Object datos = null;

        try {
            final InputStream stream = Files.newInputStream(path);
            final ObjectInputStream ois = new ObjectInputStream(stream);
            datos = ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ignored) {
        }

        if (datos instanceof Cliente[]) {
            clearClientes();
            for (Cliente cliente : (Cliente[]) datos) addCliente(cliente);
        }

        validate("la ruta no contiene un archivo valido", datos != null);
    }

    public void save(final Path path) {
        boolean saved = false;

        try {
            final OutputStream stream = Files.newOutputStream(path);
            final ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(getClientes().toArray(new Cliente[0]));
            oos.close();
            saved = true;
        } catch (IOException ignored) {
        }

        validate("no se ha podido guardar en la ruta", saved);
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