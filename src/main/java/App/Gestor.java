package App;

import App.Ventanas.*;
import Clientes.Cliente;
import Clientes.ClienteEmpresa;
import Clientes.ClientePaticular;
import Helpers.Factura;
import Helpers.Llamada;
import Interfaces.Fecha;
import com.google.common.collect.Range;

import java.util.*;

import static Helpers.ValidatorArguments.referenceNotNull;
import static Helpers.ValidatorArguments.validate;

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

    public <T extends Fecha> List<T> filterRange(List<T> lista, Range<Date> periodo) {
        List<T> filtered = new LinkedList<>();
        for (T item : lista) if (periodo.contains(item.getFecha())) filtered.add(item);
        return filtered;
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
        validate("cliente tiene que ser nuevo", cliente.getLlamadas().isEmpty() && cliente.getFacturas().isEmpty());
        id2cliente.put(cliente.getNIF(), cliente);
        clientes.add(cliente);
    }

    public void addLlamada(final Llamada llamada) {
        referenceNotNull("clienteSelecionado", clienteSelecionado);
        referenceNotNull("llamada", llamada);
        clienteSelecionado.addLlamada(llamada);
        llamadas.add(llamada);
    }

    public void addFactura(final Factura factura) {
        referenceNotNull("clienteSelecionado", clienteSelecionado);
        referenceNotNull("factura", factura);
        clienteSelecionado.addFactura(factura);
        factura2cliente.put(factura.getCodigo(), clienteSelecionado);
        facturas.add(factura);
    }

    public Ventana getVisor(final Cliente cliente) {
        referenceNotNull("cliente", cliente);
        if (cliente instanceof ClientePaticular) return new VentanaClienteParticular();
        else if (cliente instanceof ClienteEmpresa) return new VentanaClienteEmpresa();
        else return new VentanaCliente();
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
        clienteSelecionado = null;
    }

    public void run() {
        validate("gestor ya esta en uso", stack.empty());

        stack.push(new VentanaPrincipal(this));
        while (!stack.empty()) {
            Ventana result = stack.peek().run();
            if (result == null) stack.pop();
            else stack.push(result);
        }
    }

    @Override
    public String toString() {
        return "Gestor{" +
                "clientes=" + getClientes() +
                '}';
    }
}
