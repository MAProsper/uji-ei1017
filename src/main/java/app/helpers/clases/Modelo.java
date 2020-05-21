package app.helpers.clases;

import app.ventanas.vistas.abstractas.Vista;
import clientes.Cliente;
import helpers.clases.Factura;
import helpers.clases.Llamada;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static helpers.estaticos.Arguments.*;

public class Modelo {
    protected Manejador manejador;
    private HashMap<String, Cliente> id2cliente;
    private HashMap<Integer, Cliente> factura2cliente;
    private List<Cliente> clientes;
    private List<Factura> facturas;
    private List<Llamada> llamadas;

    public Modelo() {
        manejador = null;
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
        updateVistas();
    }

    public void addLlamada(final Cliente cliente, final Llamada llamada) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Llamada", llamada);
        llamadas.add(llamada);
        updateVistas();
    }

    public void addFactura(final Cliente cliente, final Factura factura) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Factura", factura);
        factura2cliente.put(factura.getCodigo(), cliente);
        facturas.add(factura);
        updateVistas();
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

        updateVistas();
    }

    public void load(final Path path) {
        referenceNotNull("Path", path);
        Object datos = null;

        try {
            final InputStream stream = Files.newInputStream(path);
            final ObjectInputStream ois = new ObjectInputStream(stream);
            datos = ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException | ClassCastException ignored) {
        }

        if (datos instanceof Cliente[]) {
            clearClientes();
            for (Cliente cliente : (Cliente[]) datos) addCliente(cliente);
            updateVistas();
        } else {
            throw new ValidationException("La ruta no contiene un archivo valido");
        }
    }

    public void save(final Path path) {
        referenceNotNull("Path", path);

        try {
            final OutputStream stream = Files.newOutputStream(path);
            final ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(getClientes().toArray(new Cliente[0]));
            oos.close();
        } catch (IOException ignored) {
            throw new ValidationException("No se ha podido guardar en la ruta");
        }
    }

    public Manejador getManejador() {
        return validate("Manajador no esta asignado", manejador, hasManajador());
    }

    public void setManejador(final Manejador manejador) {
        this.manejador = manejador;
    }

    final public boolean hasManajador() {
        return manejador != null;
    }

    public void updateVistas() {
        for (Vista vista : getManejador().getVistas())
            vista.update();
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "clientes=" + getClientes() +
                '}';
    }
}