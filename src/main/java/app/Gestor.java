package app;

import app.ventanas.abstractas.Ventana;
import app.ventanas.claeses.VentanaCliente;
import app.ventanas.claeses.VentanaClienteEmpresa;
import app.ventanas.claeses.VentanaClienteParticular;
import app.ventanas.claeses.VentanaPrincipal;
import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import helpers.clases.Factura;
import helpers.clases.Llamada;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class Gestor {
    private HashMap<String, Cliente> id2cliente;
    private HashMap<Integer, Cliente> factura2cliente;
    private List<Cliente> clientes;
    private List<Factura> facturas;
    private List<Llamada> llamadas;

    public Gestor() {
        clearClientes();
    }

    public void clearClientes() {
        id2cliente = new HashMap<>();
        factura2cliente = new HashMap<>();
        clientes = new LinkedList<>();
        facturas = new LinkedList<>();
        llamadas = new LinkedList<>();
    }

    final public List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }

    final public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }

    final public List<Llamada> getLlamadas() {
        return Collections.unmodifiableList(llamadas);
    }

    public Cliente buscarCliente(final String NIF) {
        referenceNotNull("NIF", NIF);
        validate("Cliente no encontrado", id2cliente.containsKey(NIF));
        return id2cliente.get(NIF);
    }

    public Cliente buscarCliente(final int codigo) {
        validate("Cliente no encontrado", factura2cliente.containsKey(codigo));
        return factura2cliente.get(codigo);
    }

    public void addCliente(final Cliente cliente) {
        referenceNotNull("Cliente", cliente);
        id2cliente.put(cliente.getNIF(), cliente);
        clientes.add(cliente);
        for (final Llamada llamada : cliente.getLlamadas()) addLlamada(cliente, llamada);
        for (final Factura factura : cliente.getFacturas()) addFactura(cliente, factura);
    }

    public void addLlamada(final Cliente cliente, final Llamada llamada) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Llamada", llamada);
        llamadas.add(llamada);
    }

    public void addFactura(final Cliente cliente, final Factura factura) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Factura", factura);
        factura2cliente.put(factura.getCodigo(), cliente);
        facturas.add(factura);
    }

    public Ventana getVisor(final Cliente cliente) {
        referenceNotNull("Cliente", cliente);
        if (cliente instanceof ClientePaticular) return new VentanaClienteParticular((ClientePaticular) cliente);
        else if (cliente instanceof ClienteEmpresa) return new VentanaClienteEmpresa((ClienteEmpresa) cliente);
        else return new VentanaCliente(cliente);
    }

    public void removeCliente(final Cliente cliente) {
        referenceNotNull("Cliente", cliente);
        id2cliente.remove(cliente.getNIF());
        clientes.remove(cliente);

        for (Factura factura : cliente.getFacturas()) {
            factura2cliente.remove(factura.getCodigo());
            facturas.remove(factura);
        }

        for (Llamada llamada : cliente.getLlamadas())
            llamadas.remove(llamada);
    }

    public void load(final Path path) {
        referenceNotNull("Path", path);
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

        validate("La ruta no contiene un archivo valido", datos != null);
    }

    public void save(final Path path) {
        referenceNotNull("Path", path);
        boolean saved = true;

        try {
            final OutputStream stream = Files.newOutputStream(path);
            final ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(getClientes().toArray(new Cliente[0]));
            oos.close();
        } catch (IOException ignored) {
            saved = false;
        }

        validate("No se ha podido guardar en la ruta", saved);
    }

    final public void show() {
        final Stack<Ventana> stack = new Stack<>();
        stack.push(new VentanaPrincipal());

        while (!stack.empty()) {
            Ventana current = stack.peek();
            current.setGestor(this);
            Optional<Ventana> next = current.show();
            if (next.isPresent()) stack.push(next.get());
            else stack.pop();
        }
    }

    @Override
    public String toString() {
        return "Gestor{" +
                "clientes=" + getClientes() +
                '}';
    }
}