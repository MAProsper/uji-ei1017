package app.helpers.clases;

import app.ventanas.vistas.abstractas.Vista;
import clientes.Cliente;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import tarifas.Tarifa;

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
    private final HashMap<String, Cliente> id2cliente;
    private final HashMap<Integer, Cliente> factura2cliente;
    private final List<Cliente> clientes;
    private final List<Factura> facturas;
    private final List<Llamada> llamadas;

    public Modelo() {
        manejador = null;
        id2cliente = new HashMap<>();
        factura2cliente = new HashMap<>();
        clientes = new LinkedList<>();
        facturas = new LinkedList<>();
        llamadas = new LinkedList<>();
    }

    public void clearClientes() {
        for (Cliente cliente : getClientes()) removeCliente(cliente);
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
        validate("Cliente ya existe en el modelo", !id2cliente.containsKey(cliente.getNIF()));
        id2cliente.put(cliente.getNIF(), cliente);
        clientes.add(cliente);
        updateVistas();

        for (final Llamada llamada : cliente.getLlamadas()) controlarLlamada(cliente, llamada);
        for (final Factura factura : cliente.getFacturas()) controlarFactura(cliente, factura);
    }

    public void addLlamada(final Cliente cliente, final Llamada llamada) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Llamada", llamada);
        cliente.addLlamada(llamada);
        controlarLlamada(cliente, llamada);
    }

    private void controlarLlamada(final Cliente cliente, final Llamada llamada) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Llamada", llamada);
        llamadas.add(llamada);
        updateVistas();
    }

    public void addFactura(final Cliente cliente, final Factura factura) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Factura", factura);
        cliente.addFactura(factura);
        controlarFactura(cliente, factura);
    }

    private void controlarFactura(final Cliente cliente, final Factura factura) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Factura", factura);
        factura2cliente.put(factura.getCodigo(), cliente);
        facturas.add(factura);
        updateVistas();
    }

    public void setTarifa(final Cliente cliente, final Tarifa tarifa) {
        referenceNotNull("Cliente", cliente);
        referenceNotNull("Tarifa", tarifa);
        cliente.setTarifa(tarifa);
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